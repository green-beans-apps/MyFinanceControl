package com.green_beans_apps.MyFinanceControl.FinanceProcessor.adapters.out;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialMessageInterpreter;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.CommandType;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.FinancialCommand;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.message.Message;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFinancialMessageInterpreter implements FinancialMessageInterpreter {

    private static final Pattern AMOUNT_PATTERN = Pattern.compile("(\\d+[.,]?\\d*)");

    @Override
    public FinancialCommand interpretMessage(Message message) {
        String normalized = message.getText().toLowerCase();

        CommandType type = identifyType(normalized);
        BigDecimal amount = extractAmount(normalized);
        String description = extractDescription(normalized, amount);

        FinancialCommand command = new FinancialCommand(
                type,
                amount,
                description,
                message.getUserId(),
                message.getTimestamp()
        );

        command.validate();
        return command;
    }

    private CommandType identifyType(String text) {
        if (text.contains("gastei") || text.contains("paguei") || text.contains("comprei")) {
            return CommandType.EXPENSE;
        }

        if (text.contains("recebi") || text.contains("ganhei") || text.contains("faturei") || text.contains("receber")) {
            return CommandType.INCOME;
        }

        if (text.contains("saldo") || text.contains("balanço")) {
            return CommandType.BALANCE;
        }

        throw new IllegalArgumentException("Tipo de comando não identificado");
    }

    private BigDecimal extractAmount(String text) {
        Matcher matcher = AMOUNT_PATTERN.matcher(text);

        if (matcher.find()) {
            String value = matcher.group().replace(",", ".");
            return new BigDecimal(value);
        }

        return null;
    }

    private String extractDescription(String text, BigDecimal amount) {
        if (amount == null) return null;

        String amountStr = amount.toString();
        int index = text.indexOf(amountStr);

        if (index == -1) return null;

        return text.substring(index + amountStr.length()).trim();
    }
}

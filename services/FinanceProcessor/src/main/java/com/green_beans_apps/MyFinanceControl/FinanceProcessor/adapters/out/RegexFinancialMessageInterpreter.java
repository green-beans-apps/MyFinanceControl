package com.green_beans_apps.MyFinanceControl.FinanceProcessor.adapters.out;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialMessageInterpreter;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.CommandType;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.FinancialCommand;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.message.Message;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.message.MessageInterpretationException;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFinancialMessageInterpreter implements FinancialMessageInterpreter {

    // Captura o valor da operacao
    private static final Pattern AMOUNT_PATTERN = Pattern.compile(
            "(?<![\\d.,])(?:\\d{1,3}(?:[.,]\\d{3})*|\\d+)(?:[.,]\\d{1,2})?(?![\\d.,])"
    );

    // Definir as palavras chave para cada tipo de operacao
    private static final List<String> EXPENSE_KEYWORDS =
            List.of("gastei", "paguei", "comprei", "desembolsei", "transferi");

    private static final List<String> INCOME_KEYWORDS =
            List.of("recebi", "ganhei", "faturei", "entrou", "receber");

    private static final List<String> BALANCE_KEYWORDS =
            List.of("saldo", "balanço", "balanco", "relatorio", "relatório", "resumo", "quanto tenho");

    @Override
    public FinancialCommand interpretMessage(Message message) {
        String normalized = normalize(message.getText());

        CommandType type = identifyType(normalized);
        BigDecimal amount = extractAmount(normalized);
        String description = extractDescription(normalized, amount);

        validateAmountForType(type, amount, message.getText());

        return new FinancialCommand(
                Integer.valueOf(message.getMessageId()),
                type,
                amount,
                description,
                message.getUserId(),
                message.getTimestamp()
        );
    }

    private String normalize(String text) {
        return text.toLowerCase()
                .trim()
                .replaceAll("\\s+", " ")
                .replace("r$", "")
                .replace("reais", "")
                .trim();
    }

    private CommandType identifyType(String text) {
        if (containsAny(text, BALANCE_KEYWORDS)) return CommandType.BALANCE;
        if (containsAny(text, EXPENSE_KEYWORDS)) return CommandType.EXPENSE;
        if (containsAny(text, INCOME_KEYWORDS))  return CommandType.INCOME;

        throw new MessageInterpretationException(
                "Não entendi o tipo da operação. Use: 'gastei', 'recebi', 'paguei', 'saldo'...",
                text
        );
    }

    private BigDecimal extractAmount(String text) {
        Matcher matcher = AMOUNT_PATTERN.matcher(text);

        BigDecimal largest = null;

        // Busca o maior valor da string
        while (matcher.find()) {
            String raw = matcher.group()
                    .replace(".", "")
                    .replace(",", ".");

            try {
                BigDecimal candidate = new BigDecimal(raw);
                if (largest == null || candidate.compareTo(largest) > 0) {
                    largest = candidate;
                }
            } catch (NumberFormatException ignored) {
                // ignorar numeros esquisitos
            }
        }

        return largest;
    }

    private String extractDescription(String text, BigDecimal amount) {
        if (amount == null) return null;

        // Tenta extrair o que vem depois do valor
        Matcher matcher = AMOUNT_PATTERN.matcher(text);

        if (matcher.find()) {
            String after = text.substring(matcher.end()).trim();

            // Remove palavras iniciais: "no", "na", "em", "de", "com"
            after = after.replaceFirst("^(no|na|em|de|com|pra|para|pelo|pela)\\s+", "");

            return after.isBlank() ? null : capitalize(after);
        }

        return null;
    }

    private void validateAmountForType(CommandType type, BigDecimal amount, String originalText) {
        if (type == CommandType.BALANCE) return;

        if (amount == null) {
            throw new MessageInterpretationException(
                    "Não encontrei um valor na mensagem. Ex: 'gastei 50 no mercado'",
                    originalText
            );
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MessageInterpretationException(
                    "O valor deve ser maior que zero.",
                    originalText
            );
        }
    }

    private boolean containsAny(String text, List<String> keywords) {
        return keywords.stream().anyMatch(text::contains);
    }

    private String capitalize(String text) {
        if (text.isBlank()) return text;
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }
}

package com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class FinancialCommand {

    private Integer id;
    private CommandType type;
    private BigDecimal amount;
    private String description;
    private String userId;
    private Instant timestamp;

    public FinancialCommand() {
    }

    public FinancialCommand(Integer id, CommandType type, BigDecimal amount, String description, String userId, Instant timestamp) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public void validate() {
        Objects.requireNonNull(type, "Command type is required");
        Objects.requireNonNull(userId, "UserId is required");

        if (type == CommandType.EXPENSE || type == CommandType.INCOME) {
            if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero");
            }
        }

        if (type == CommandType.BALANCE && amount != null) {
            throw new IllegalArgumentException("Balance command should not have amount");
        }
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

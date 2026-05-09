package com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.message;

public class MessageInterpretationException extends RuntimeException {

    private final String originalMessage;

    public MessageInterpretationException(String reason, String originalMessage) {
        super(reason);
        this.originalMessage = originalMessage;
    }

    public MessageInterpretationException(String reason, String originalMessage, Throwable cause) {
        super(reason, cause);
        this.originalMessage = originalMessage;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }
}

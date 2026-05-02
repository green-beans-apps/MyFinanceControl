package com.green_beans_apps.MyFinanceControl.TelegramWebHook.adapters.in.telegram;

import com.green_beans_apps.MyFinanceControl.TelegramWebHook.domain.message.Message;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public  class TelegramMapper {

    public static  Message toDomain(TelegramUpdate update) {

        if (update == null || update.getMessage() == null) {
            return null;
        }

        TelegramMessage msg = update.getMessage();

        if (msg.getText() == null) {
            return null;
        }

        return new Message(
                String.valueOf(msg.getMessage_id()),
                String.valueOf(msg.getChat().getId()),
                msg.getText(),
                Instant.ofEpochSecond(msg.getDate()),
                msg.getFrom() != null ? msg.getFrom().getFirst_name() : null,
                msg.getFrom() != null ? msg.getFrom().getLast_name() : null
        );
    }
}

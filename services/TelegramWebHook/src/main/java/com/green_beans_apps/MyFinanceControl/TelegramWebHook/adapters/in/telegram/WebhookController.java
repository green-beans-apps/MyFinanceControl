package com.green_beans_apps.MyFinanceControl.TelegramWebHook.adapters.in.telegram;

import com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message.ProcessIncomingMessageUseCase;
import com.green_beans_apps.MyFinanceControl.TelegramWebHook.domain.message.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private ProcessIncomingMessageUseCase processIncomingMessageUseCase;

     public WebhookController(ProcessIncomingMessageUseCase processIncomingMessageUseCase) {
         this.processIncomingMessageUseCase = processIncomingMessageUseCase;
     }

     @PostMapping
     public ResponseEntity<Void> receiveMessage(@RequestBody TelegramUpdate update) {
         Message message = TelegramMapper.toDomain(update);

         System.out.println("Received message: " + message.getText());

         return ResponseEntity.ok().build();
     }
}

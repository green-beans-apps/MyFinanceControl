package com.green_beans_apps.MyFinanceControl.TelegramWebHook.adapters.in.telegram;

import com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message.ProcessIncomingMessageUseCase;
import com.green_beans_apps.MyFinanceControl.TelegramWebHook.domain.message.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
         try {
             processIncomingMessageUseCase.execute(message);
         } catch (IllegalArgumentException e) {
             return ResponseEntity.unprocessableContent().build();
         }

         return ResponseEntity.ok().build();
     }

     @GetMapping("/health")
     public ResponseEntity<Void> healthcCheck() {
         return ResponseEntity.ok().build();
     }
}

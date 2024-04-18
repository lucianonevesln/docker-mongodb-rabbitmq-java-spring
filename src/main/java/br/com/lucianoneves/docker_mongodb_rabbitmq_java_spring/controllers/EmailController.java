package br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.controllers;

import br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.dtos.EmailDTO;
import br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.entities.EmailEntity;
import br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sending-email")
    public ResponseEntity<EmailEntity> sendingEmailRepository(@RequestBody @Valid EmailDTO emailDto) {
        return emailService.sendEmailService(emailDto);
    }
}

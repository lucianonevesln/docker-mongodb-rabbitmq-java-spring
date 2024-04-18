package br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.messaging;

import br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.dtos.EmailDTO;
import br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.entities.EmailEntity;
import br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "microsservico-email-queue")
    public void listen(@Payload EmailDTO emailDto) {
        ResponseEntity responseEntity = emailService.sendEmailService(emailDto);
        System.out.println("Email Status: " + responseEntity.getBody());
    }
}
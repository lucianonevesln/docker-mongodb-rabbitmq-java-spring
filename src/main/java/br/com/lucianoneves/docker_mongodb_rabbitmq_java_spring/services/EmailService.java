package br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.services;

import br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.dtos.EmailDTO;
import br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.entities.EmailEntity;
import br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.enums.Status;
import br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.repositories.EmailRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    final EmailRepository emailRepository;
    final JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    public ResponseEntity<EmailEntity> sendEmailService(EmailDTO emailDto) {
        EmailEntity emailEntity = new EmailEntity();

        emailEntity.setResponsible(emailDto.getResponsible());
        emailEntity.setEmailFrom(emailDto.getEmailFrom());
        emailEntity.setEmailTo(emailDto.getEmailTo());
        emailEntity.setSubject(emailDto.getSubject());
        emailEntity.setText(emailDto.getText());
        emailEntity.setSendDate(LocalDateTime.now());

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(emailEntity.getEmailFrom());
            simpleMailMessage.setTo(emailEntity.getEmailTo());
            simpleMailMessage.setSubject(emailEntity.getSubject());
            simpleMailMessage.setText(emailEntity.getText());
            emailSender.send(simpleMailMessage);
            emailEntity.setStatus(Status.SENT);
        }
        catch (MailException e) {
            emailEntity.setStatus(Status.ERROR);
        }
        finally {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(emailRepository.save(emailEntity));
        }
    }
}
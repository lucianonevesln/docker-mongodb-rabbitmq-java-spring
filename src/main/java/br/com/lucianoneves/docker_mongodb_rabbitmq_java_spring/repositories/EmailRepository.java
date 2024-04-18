package br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.repositories;

import br.com.lucianoneves.docker_mongodb_rabbitmq_java_spring.entities.EmailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<EmailEntity, Long> {
}

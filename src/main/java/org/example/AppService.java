package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.db.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class AppService implements MessageHandler {
    User user;

    @Autowired
    UserDao userDao;

    @Value("${output-topic}")
    private String outputTopic;

    public void handleMessage(Message<?> message) throws MessagingException {
        JsonParser users = new JsonParser();
        this.user = users.getUser(message.getPayload().toString());
        if (user != null) {
            userDao.insert(user);
        }
    }
}
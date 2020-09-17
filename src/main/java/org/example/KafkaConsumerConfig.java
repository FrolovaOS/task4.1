package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;


@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${usertopic}")
    private String usertopic;

    @Value("${staticstopic}")
    private String staticstopic;

    @Autowired
    private KafkaProperties properties;

    @Autowired
    private AppService service1;

    @Autowired
    private AppService2 service2;

    @Bean
    public DirectChannel fromKafka(){return new DirectChannel();}

    @Bean
    public IntegrationFlow readFromKafka1() {
        return IntegrationFlows
                .from(Kafka.messageDrivenChannelAdapter( new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties()),usertopic))
                .handle(service1)
                 .get();
    }


    @Bean
    public IntegrationFlow readFromKafka2() {
        return IntegrationFlows
                .from(Kafka.messageDrivenChannelAdapter( new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties()),staticstopic))
                .handle(service2)
                .get();
    }
}

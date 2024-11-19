package com.promptoven.reviewReadService.global.config;

import com.promptoven.reviewReadService.dto.in.message.CreateRequestMessageDto;
import com.promptoven.reviewReadService.dto.in.message.DeleteRequestMessageDto;
import com.promptoven.reviewReadService.dto.in.message.UpdateRequestMessageDto;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private <T> ConsumerFactory<String, T> consumerFactory(Class<T> targetType) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-review-read-service");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, targetType.getName());
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    private <T> ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory(Class<T> targetType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(targetType));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateRequestMessageDto> createKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory(CreateRequestMessageDto.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UpdateRequestMessageDto> updateKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory(UpdateRequestMessageDto.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DeleteRequestMessageDto> deleteKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory(DeleteRequestMessageDto.class);
    }

}

package com.walkingtree.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class SenderConfig {

	@Value("${kafka.bootstrap-servers}")
	private String bootStrapServers;
	
	@Bean
	public Map<String,Object> producerConfig(){
		Map<String,Object> producerProperties=new HashMap<String,Object>();
		producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
		producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return producerProperties;
	}
	
	@Bean
	public ProducerFactory<String, Object> producerFactory(){
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
	
}

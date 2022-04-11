package com.raquelmc.springApp.rabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import com.raquelmc.springApp.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class MessagingConfig.
 */
@Configuration
public class MessagingConfig {
		
	/**
	 * Queue.
	 *
	 * @return the queue
	 */
	@Bean
	public Queue queue() {
		return new Queue(Constants.QUEUE, Constants.IS_DURABLE_QUEUE);
	}

	/**
	 * Exchange.
	 *
	 * @return the topic exchange
	 */
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(Constants.EXCHANGE);
	}

	/**
	 * Binding.
	 *
	 * @param queue the queue
	 * @param exchange the exchange
	 * @return the binding
	 */
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(Constants.ROUTING_KEY);
	}

	/**
	 * Json message converter.
	 *
	 * @return the message converter
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	/**
	 * Jackson 2 converter.
	 *
	 * @return the mapping jackson 2 message converter
	 */
	@Bean
	public MappingJackson2MessageConverter jackson2Converter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}

	/**
	 * My handler method factory.
	 *
	 * @return the default message handler method factory
	 */
	@Bean
	public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(jackson2Converter());
		return factory;
	}

}

package com.raquelmc.springApp.rabbitmq;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.raquelmc.springApp.Helper;
import com.raquelmc.springApp.model.AnalyticsInfo;
import com.raquelmc.springApp.model.Request;
import com.raquelmc.springApp.model.Statistic;
import com.raquelmc.springApp.repository.StatisticRepository;
import org.springframework.amqp.support.AmqpHeaders;

@Component
public class ReceiverService {

	@Autowired
	private StatisticRepository repository;
	@Autowired
	private Helper helper;

	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(Request request, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
			throws IOException {
		try {
			ArrayList<AnalyticsInfo> info = request.getDatastreams();
			System.out.println("Mensaje recibido2: " + info);
			Statistic st = helper.createStatistic(info);
			System.out.println("Statistic object" + st);
		} catch (Exception e) {
			channel.basicReject(tag, true);
		}

		channel.basicAck(tag, false);

	}

}

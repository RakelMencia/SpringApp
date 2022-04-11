/*
 * Listener
 */
package com.raquelmc.springApp;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.raquelmc.springApp.model.Request;
import com.raquelmc.springApp.model.Statistic;
import org.springframework.amqp.support.AmqpHeaders;

// TODO: Auto-generated Javadoc
/**
 * The Class ReceiverService.
 */
@Service
public class ReceiverService {

	/** The helper. */
	@Autowired
	private Helper helper;
	
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger(Constants.RECEIVER_LOG);

	/**
	 * Consume message from queue.
	 *
	 * @param request the request
	 * @param channel the channel
	 * @param tag the tag
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@RabbitListener(queues = Constants.QUEUE)
	public void consumeMessageFromQueue(Request request, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
			throws IOException {
		try {
			List<Statistic> stList = helper.createStatistic(request.getDatastreams());
			if (!stList.isEmpty()) {
				helper.saveAll(stList);
			} else {
				LOGGER.log(Level.WARNING, Constants.VALUES_EMPTY);
			}
		} catch (NullPointerException e) {
			LOGGER.log(Level.SEVERE, Constants.NULLP_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {	
			channel.basicAck(tag, false);
		}
	}

}

package com.kuns.queue.producer;

import java.util.Map;
import java.util.Map.Entry;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class MsgProducer {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(final String msg) {

		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}

	public void sendMessage(final Map<String, Object> msg) {

		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(JSON.toJSONString(msg));
			}
		});
	}

	public void sendMapMessage(final Map<String, Object> msg) {

		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				for (Entry<String, Object> entry : msg.entrySet()) {
					mapMessage.setObject(entry.getKey(), entry.getValue());
				}
				return mapMessage;
			}
		});
	}

}

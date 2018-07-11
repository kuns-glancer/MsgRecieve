package com.kuns.queue.handler;

import javax.jms.JMSException;
import javax.jms.Message;

public interface BaseHandler {

	public void handler(Message message) throws JMSException;

}

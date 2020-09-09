package com.botExample.Commands;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.updateshandlers.SentCallback;

///TODO
public class Callback  implements SentCallback<Message>,Serializable {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onResult(BotApiMethod<Message> method, Message response) {
		// TODO Auto-generated method stub
		logger.info("onResult");
	}

	@Override
	public void onError(BotApiMethod<Message> method, TelegramApiRequestException apiException) {
		// TODO Auto-generated method stub
		logger.info("onError");
	}

	@Override
	public void onException(BotApiMethod<Message> method, Exception exception) {
		// TODO Auto-generated method stub
		logger.info("onException");
	}


}

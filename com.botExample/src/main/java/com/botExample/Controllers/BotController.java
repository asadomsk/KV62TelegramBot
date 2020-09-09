package com.botExample.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.botExample.POJO.KV62Bot;
import com.botHandler.Handler;

@RestController
public class BotController {

	@Autowired
	private KV62Bot telegramBot;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public BotApiMethod onUpdateReceived(@RequestBody Update update) {
		if (telegramBot.getCommandsList() != null) {
			telegramBot.onWebhookUpdateReceived(update);
		} else logger.info("bot doesn't have any comand"); //TODO

		return null;

	}
}

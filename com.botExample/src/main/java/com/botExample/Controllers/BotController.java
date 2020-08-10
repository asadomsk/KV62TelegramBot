package com.botExample.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.botExample.POJO.KV62Bot;
import com.botHandler.Handler;
import com.botWeather.Weather;

@RestController
public class BotController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KV62Bot telegramBot;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public BotApiMethod onUpdateReceived(@RequestBody Update update) {
		// return telegramBot.onWebhookUpdateReceived(update);
		if (telegramBot.getCommandsList() != null) {
			logger.info("command exists");
			for (Handler c : telegramBot.getCommandsList()) {
				logger.info(c.getName());
				c.Execute(update, telegramBot);
			}
		}

		return null;

	}
}

package com.botExample.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.botExample.POJO.KV62Bot;



@RestController
public class BotController {
	

@Autowired
private KV62Bot telegramBot;

	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
      	return telegramBot.onWebhookUpdateReceived(update);
	}

}

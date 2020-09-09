package com.botHandler;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.botExample.POJO.KV62Bot;
import com.botTrainTable.TrainTable;
import com.botWeather.Model;
import com.botWeather.Weather;

@Component
public class TrainTableHandlerImpl extends Handler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TrainTable trains;

	@Override
	public String Execute(String message) {

		return trains.getTrains("java", "19.08.2020", "2004000", new Model()); //TODO

	}
	// TODO callback
	// bot.executeAsync(method, callback); ??


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "trains";
	}
}

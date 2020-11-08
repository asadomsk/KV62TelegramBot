package com.botHandler;

import java.io.IOException;

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
import com.botWeather.Model;
import com.botWeather.Weather;

@Component
public class WeatherHandlerImpl implements Handler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Weather weather = new Weather();

	@Override
	public String Execute(Update update) {

		String message = update.getMessage().getText();
		int indexOf = message.indexOf(' ');
		if (indexOf > -1) {
			String queryString = message.substring(indexOf + 1);
			logger.info("Serching weather for: " + queryString);
			return weather.getWeather(queryString, new Model());
		} else {
			logger.info("weather command: city should be entered after bot name and space");
		} // TODO
		return null;
	}

	@Override
	public String getName() {
		return "weather";
	}

}
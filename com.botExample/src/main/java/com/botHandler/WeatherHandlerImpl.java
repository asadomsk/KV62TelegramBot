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
public class WeatherHandlerImpl extends Handler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
  

	private Weather weather=new Weather() ;

	@Override
	public BotApiMethod Execute(Update update, KV62Bot bot) {

		if (update.hasMessage()) {
			// logic of weather bot
			Message message = update.getMessage();
			SendMessage response = new SendMessage();
			Long chatId = message.getChatId();
			response.setChatId(chatId);
			String text = message.getText();
			int indexOf = text.indexOf(" ");
			logger.info("doesnt contain commands");
			if (Contains(text, bot) && indexOf > -1) {
				logger.info("contains commands");
				String queryString = text.substring(indexOf+1);
				new Thread(() -> {
					try {
						response.setText(weather.getWeather(queryString, new Model()));
						bot.execute(response);
						logger.info("started");
						Thread.sleep(30000); // checking async
						logger.info("Sent message \"{}\" to {}", text, chatId);

					} catch (TelegramApiException e) {
						logger.info("TelegramApiException" + e);//todo
					} catch (InterruptedException e) {
						logger.info("InterruptedException" + e);//todo
					} catch (IOException e) {
						logger.info("IOException" + e);//todo
					}

				}).start();
			} //TODO
		} //TODO
		return null;
	}
	// TODO callback
	// bot.executeAsync(method, callback); ??

	@Override
	public Boolean Contains(String command, KV62Bot bot) {
		return command.contains(getName()) && command.contains(bot.getBotUsername());

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Weather";
	}

}
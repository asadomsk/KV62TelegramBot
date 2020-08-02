package com.botExample.Commands;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.botExample.POJO.KV62Bot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HelloCommand extends Command {
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	@Async
	@Override
	public BotApiMethod Execute(Update update, KV62Bot bot) {
		
		   
		if (update.hasMessage()) {
			
			
			////todo bot lgic
			logger.info("> sendAsync");
			Message message = update.getMessage();
			SendMessage response = new SendMessage();
			Long chatId = message.getChatId();
			response.setChatId(chatId);
			String text = message.getText();
			response.setText(text);


            //bot.executeAsync(method, callback); ??
			try {				
				bot.execute(response);
				logger.info("started");
				Thread.sleep(30000);
				logger.info("finished");
				
			} catch (TelegramApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Sent message \"{}\" to {}", text, chatId);
		}

		return null;
	}



	@Override
	public String getName() {
		return "Hello";
	}



	@Override
	public Boolean Contains(String command, KV62Bot bot) {
		return command.contains(getName()) && command.contains(bot.getBotUsername());
		
	}

}

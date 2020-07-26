package com.botExample.POJO;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class KV62Bot extends TelegramWebhookBot {
	private String botUserName="KV62Bot";
	
	private String botToken="1320794276:AAFVXgxTxxWyrLE1jvTEC_QDGvdE0gfF9EM";

    private String botPath ="https://13cecc0baaaa.ngrok.io";

	public BotApiMethod  onWebhookUpdateReceived(Update update) {
		if (update.hasMessage()) {
			Message message = update.getMessage();
			SendMessage response = new SendMessage();
			Long chatId = message.getChatId();
			response.setChatId(chatId);
			String text = message.getText();
			response.setText(text);
			try {
				execute(response);
				//logger.info("Sent message \"{}\" to {}", text, chatId);
			} catch (TelegramApiException e) {
				//logger.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
			}
		}
		return null;
	}

	public String getBotUsername() {
		// TODO Auto-generated method stub
		return botUserName;
	}

	public String getBotPath() {
		// TODO Auto-generated method stub
		return botPath;
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return botToken;
	}
}

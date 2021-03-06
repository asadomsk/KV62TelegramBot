package com.botExample.POJO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.botExample.AppSettings.AppSettings;
import com.botHandler.Handler;
import com.botHandler.TrainTableHandlerImpl;
import com.botHandler.WeatherHandlerImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class KV62Bot extends TelegramWebhookBot {

	@Autowired
	private AppSettings ap;

	@Autowired
	private WeatherHandlerImpl wt;// TODO

	@Autowired
	private TrainTableHandlerImpl tr;// TODO

	@Autowired
	private TelegramFacade telegramFacade;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	Map<String, Handler> commandsList;

	// Just for the record
	// Adding all commands(handlers) for the bot, immediately after the bot was
	// created
	// HashMap for better performance lookup of commands
	// Should not override hashcode and equals, since the key is String and I
	// compare string values (command name)
	@PostConstruct
	public void setCommands() {

		commandsList = new HashMap<String, Handler>();
		commandsList.put(wt.getName(), wt);
		commandsList.put(tr.getName(), tr);

	}

	public BotApiMethod onWebhookUpdateReceived(Update update) {

		if (update.hasMessage() || update.hasCallbackQuery()) {

			new Thread(() -> {
				try {
					SendMessage response = new SendMessage();

					response = telegramFacade.handleUpdate(update, commandsList);// TODO

					// setButton(response);// check

					execute(response);

					logger.info("started");
					Thread.sleep(30000); // checking async
					if(update.hasMessage())
					logger.info("Sent message \"{}\" to {}", update.getMessage(), update.getMessage().getChatId());
					else    logger.info("Sent message \"{}\" to {}", update.getCallbackQuery().getMessage(), update.getCallbackQuery().getMessage().getChatId()); 
				} catch (TelegramApiException e) {
					logger.info("TelegramApiException" + e);// todo
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();

		}

		else
			logger.info("no message has been sent"); // TODO

		return null;
	}

	public String getBotUsername() {
		return ap.getBotUserName();
	}

	// @Override
	public String getBotPath() {
		return ap.getBotPath();
	}

	// @Override
	public String getBotToken() {
		return ap.getBotToken();
	}

	public Map<String, Handler> getCommandsList() {
		return commandsList;
	}

	/// Setting bot's keyboard

	public void setButton(SendMessage sendMessage) {
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
		sendMessage.setReplyMarkup(replyKeyboardMarkup);
		replyKeyboardMarkup.setSelective(true);
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setOneTimeKeyboard(false);

		List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();
		KeyboardRow keyboardFirstRow = new KeyboardRow();
		keyboardFirstRow.add(new KeyboardButton("KV62Bot"));
		keyboardFirstRow.add(new KeyboardButton("Weather"));
		keyboardFirstRow.add(new KeyboardButton("Aaaaaa"));

		keyboardRowList.add(keyboardFirstRow);
		replyKeyboardMarkup.setKeyboard(keyboardRowList);

	}

	// TODO
	// command contains Bot name

}
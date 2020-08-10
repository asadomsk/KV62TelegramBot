package com.botExample.POJO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.botExample.AppSettings.AppSettings;
import com.botExample.Commands.Callback;
import com.botHandler.Handler;
import com.botHandler.WeatherHandlerImpl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class KV62Bot extends TelegramWebhookBot {
	
	private List <Handler> commandsList;
	
	@Autowired
	AppSettings ap;
	
	/**
	 * The Logger for this class.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void setCommands() {
	commandsList =new LinkedList<Handler>();
	commandsList.add(new WeatherHandlerImpl()); //todo
	//todo all commands
	}

	
	public BotApiMethod onWebhookUpdateReceived(Update update) {
		
		return null;
		}
		

	public String getBotUsername() {
		// TODO Auto-generated method stub
		return ap.getBotUserName();
	}

	public String getBotPath() {
		// TODO Auto-generated method stub
		return ap.getBotPath();
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return ap.getBotToken();
	}
	


	public List<Handler> getCommandsList() {
		return commandsList;
	}


	public void setCommandsList(List<Handler> commandsList) {
		this.commandsList = commandsList;
	}
	@Override
	public String toString() {
		String command=null;
		if(commandsList!=null) {
			for(Handler h: commandsList)
		        command+= h.getName();
	        }
		return command;

           }
}
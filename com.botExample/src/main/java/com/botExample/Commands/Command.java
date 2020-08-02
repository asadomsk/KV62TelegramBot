package com.botExample.Commands;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.botExample.AppSettings.AppSettings;
import com.botExample.POJO.KV62Bot;

@Component 
public abstract class Command {

	
	//private  String BotName;

	public abstract String getName();
	
	
	public abstract BotApiMethod Execute(Update update, KV62Bot bot);
	
	public abstract Boolean Contains (String command, KV62Bot bot);
	
//	public MessageType messageType(Object object) {
//        if (object instanceof SendSticker) return MessageType.STICKER;
//        if (object instanceof BotApiMethod) return MessageType.EXECUTE;
//        return MessageType.NOT_DETECTED;
//    }
//
//    public enum MessageType {
//        EXECUTE, STICKER, NOT_DETECTED,
//    }
}

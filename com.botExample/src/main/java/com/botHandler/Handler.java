package com.botHandler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.botExample.POJO.KV62Bot;

public abstract class Handler {
	
		public abstract String getName();
		
		
		public abstract BotApiMethod Execute(Update update, KV62Bot bot);
		
		public abstract Boolean Contains (String command, KV62Bot bot);
		
//		public MessageType messageType(Object object) {
//	        if (object instanceof SendSticker) return MessageType.STICKER;
//	        if (object instanceof BotApiMethod) return MessageType.EXECUTE;
//	        return MessageType.NOT_DETECTED;
//	    }
	//
//	    public enum MessageType {
//	        EXECUTE, STICKER, NOT_DETECTED,
//	    }
}

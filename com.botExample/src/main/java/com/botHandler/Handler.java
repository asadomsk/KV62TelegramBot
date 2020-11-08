package com.botHandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public interface Handler {

     String getName();

     String Execute(Update update);

	// TODO
	// Hand start and help command

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

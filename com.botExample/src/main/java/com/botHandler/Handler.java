package com.botHandler;

import org.springframework.stereotype.Component;


@Component
public abstract class Handler {

	public abstract String getName();

	public abstract String Execute(String message);

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

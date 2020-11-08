package com.botExample.Services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.botExample.Utils.Emojis;

@Service
public class ReplyMessagesService {
	
	
	 public String getWarningReplyMessage(String replyMessage) {
	        return replyMessage + Emojis.NOTIFICATION_MARK_FAILED; //check
	    }


}

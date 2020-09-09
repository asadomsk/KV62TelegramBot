package com.botExample.AppSettings;

import org.springframework.stereotype.Component;

@Component
public class AppSettings {
    private String botUserName="KV62Bot";//TODO
	
	private String botToken="1320794276:AAFVXgxTxxWyrLE1jvTEC_QDGvdE0gfF9EM";//TODO

    private String botPath ="https://fcebb1999ec5.ngrok.io";//TODO
    
	public String getBotUserName() {
		return this.botUserName;
	}

	public String getBotToken() {
		return botToken;
	}

	public String getBotPath() {
		return botPath;
	}

    
    
}

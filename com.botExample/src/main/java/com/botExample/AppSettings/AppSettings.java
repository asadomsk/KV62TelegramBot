package com.botExample.AppSettings;

import org.springframework.stereotype.Component;

@Component
public class AppSettings {
    private String botUserName="KV62Bot";
	
	private String botToken="1320794276:AAFVXgxTxxWyrLE1jvTEC_QDGvdE0gfF9EM";

    private String botPath ="https://7cc107d561ed.ngrok.io";

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

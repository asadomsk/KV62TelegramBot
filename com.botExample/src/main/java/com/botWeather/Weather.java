package com.botWeather;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Weather {
	
	public String getWeather(String message, Model model) throws IOException {
		URL url= new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=9b9506e1ec9a46ab3f68e17b1b74cc9e");
		//TODO
		/**
		 * Returns the content of the resource which is referred by this URL. By
		 * default this returns an {@code InputStream}, or null if the content type
		 * of the response is unknown.
		 */
		
		Scanner in=new Scanner((InputStream) url.getContent());
		
		String result="";
		while(in.hasNext()){
			result+=in.nextLine();
		}
		
		in.close();
		return result;
	}
	
	
}

package com.botTrainTable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainInf {
	
	Train train;
	
	String IsExchange;
	
	@JsonProperty(value = "EstTime")
	String timeInWay;
	
	public String getTimeInWay() {
		return timeInWay;
	}

	public void setTimeInWay(String timeInWay) {
		this.timeInWay = timeInWay;
	}

}

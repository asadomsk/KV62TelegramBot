package com.botTrainTable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TrainStation {
    @JsonProperty(value = "Eng")
    private String stationName;
    @JsonProperty(value = "Id")
    private int stationCode;
    
    
	@Override
	public String toString() {
		return "TrainStation [stationName=" + stationName + ", stationCode=" + stationCode + "]";
	}
    
    
}

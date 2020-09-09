package com.botTrainTable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Train {
	
	
    @JsonProperty(value = "Trainno")
    private String number;

    @JsonProperty(value = "Platform")
    private String brand;

    @JsonProperty(value = "OrignStation")
    private String stationDepart;

    @JsonProperty(value = "DestinationStation")
    private String stationArrival;

    @JsonProperty(value = "DepartureTime")
    private String timeDepart;

    @JsonProperty(value = "ArrivalTime")
    private String timeArrival;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getStationDepart() {
		return stationDepart;
	}

	public void setStationDepart(String stationDepart) {
		this.stationDepart = stationDepart;
	}

	public String getStationArrival() {
		return stationArrival;
	}

	public void setStationArrival(String stationArrival) {
		this.stationArrival = stationArrival;
	}

	public String getTimeDepart() {
		return timeDepart;
	}

	public void setTimeDepart(String timeDepart) {
		this.timeDepart = timeDepart;
	}

	public String getTimeArrival() {
		return timeArrival;
	}

	public void setTimeArrival(String timeArrival) {
		this.timeArrival = timeArrival;
	}


    
    

}
package com.botTrainTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import com.botWeather.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@Component

public class TrainTable {

	@Autowired
	private WebClient client;
	private List<StationInfo> stations = new ArrayList<StationInfo>();
	private List<String> stationsMatches = new ArrayList<String>();

	@Autowired
	private MessageSource messageSource;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ObjectMapper objectMapper = new ObjectMapper();
	private String trainTicketsInfoMessage = "";

	public List<String> getStationsMatches() {
		return stationsMatches;
	}

	public List<StationInfo> getStations() {
		return stations;
	}

	public String getReplyMessage(String replyMessage) {

		return replyMessage;
	}

	@PostConstruct
	public void loadStations() {
		Mono<ClientResponse> result = client.get().uri("/apiinfo/api/Plan/GetRoutes")
				.accept(MediaType.APPLICATION_JSON_UTF8).exchange();

		String resultResponse = result.flatMap(res -> res.bodyToMono(String.class)).block();

		// logger.info(resultResponse);
		try {

			// logger.info("Trying to read tree");
			JsonNode trainsNode = objectMapper.readTree(resultResponse);
			// Get Data
			// logger.info("Trying to get path");
			JsonNode dataNode = trainsNode.path("Data");
			if (!dataNode.isMissingNode()) { // if "Data" node is exist
				// logger.info("Data node is exist");
				JsonNode cp = dataNode.path("CustomPropertys");
				if (cp.isArray()) {
					// logger.info("Routes node is exist and array");
					for (JsonNode node : cp) {
						StationInfo si = new StationInfo();
						si.setId(node.path("Id").asInt());
						JsonNode heb = node.path("Heb");
						si.setHeb(heb.get(0).asText());
						JsonNode eng = node.path("Eng");
						si.setEng(eng.get(0).asText());
						JsonNode rus = node.path("Rus");
						si.setRus(rus.get(0).asText());

						stations.add(si);
					}

				}

			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTrains(String dep, String date, String arr, Model model) {

		logger.info("tring to execute tain table");

		Mono<ClientResponse> result = client.get()
				.uri("/apiinfo/api/Plan/GetRoutes?OId=4900&TId=5000&Date=20200930&Hour=1830")// TODO!!!
				.accept(MediaType.TEXT_PLAIN).exchange();

		String resultResponse = result.flatMap(res -> res.bodyToMono(String.class)).block();
		// logger.info(resultResponse);
		try {

			// logger.info("Trying to read tree");
			JsonNode trainsNode = objectMapper.readTree(resultResponse);
			// Get Data
			// logger.info("Trying to get path");
			JsonNode dataNode = trainsNode.path("Data");
			if (!dataNode.isMissingNode()) { // if "Data" node is exist
				// logger.info("Data node is exist");
				JsonNode routesNode = dataNode.path("Routes");
				if (routesNode.isArray()) {
					// logger.info("Routes node is exist and array");
					// for (JsonNode node : routesNode) {
					JsonNode node = routesNode.get(0);
					TrainInf trainInf = objectMapper.convertValue(node, TrainInf.class);
					JsonNode trainNode = node.path("Train");
					if (trainNode.isArray()) {
						JsonNode trainInfo = trainNode.get(0);
						Train train = objectMapper.convertValue(trainInfo, Train.class);
						// String Trainno = trainInfo.path("Trainno").asText();
						trainTicketsInfoMessage += messageSource.getMessage("reply.trainSearch.trainInfo",
								new Object[] { train.getNumber(), train.getStationDepart(), train.getTimeDepart(),
										train.getStationArrival(), train.getTimeArrival(), trainInf.getTimeInWay() },
								Locale.getDefault());
					}
					// Boolean isExchange = node.path("IsExchange").asBoolean();
					//

					// }
				}

			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//                    String f2Str = jsonNode.get("f2").asText();
//                    double f2Dbl = jsonNode.get("f2").asDouble();
//                    int    f2Int = jsonNode.get("f2").asInt();
//                    long   f2Lng = jsonNode.get("f2").asLong();

		return trainTicketsInfoMessage;

	}

	public void getStationsEng(String station) {

		for (StationInfo st : stations) {
			if (st.eng.toLowerCase().matches(".*" + station.toLowerCase() + ".*")) {
				stationsMatches.add(st.eng);

			}
		}

	}

	public void getStationsHeb(String station) {

		for (StationInfo st : stations) {
			if (st.heb.matches(".*" + station + ".*")) {
				stationsMatches.add(st.heb);

			}
		}

	}

	public void getStationsRus(String station) {

		for (StationInfo st : stations) {
			if (st.rus.matches(".*" + station + ".*")) {
				stationsMatches.add(st.rus);

			}
		}

	}

	public String sendStationSearchRequest() {

		Mono<TrainStation[]> result = client.get().uri("/apiinfo/api/Plan/GetRoutes").exchange()
				.flatMap(res -> res.bodyToMono(TrainStation[].class));

		TrainStation[] stations = result.block();
		String stationsList = "";
		for (TrainStation station : stations) {
			stationsList += (station.toString());
		}

		return stationsList;

	}
}

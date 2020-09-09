package com.botTrainTable;

import java.io.IOException;
import java.util.Locale;
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
	private MessageSource messageSource;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ObjectMapper objectMapper = new ObjectMapper();
	private String trainTicketsInfoMessage = "";

	private WebClient client = WebClient.create("https://www.rail.co.il");

	public String getTrains(String dep, String date, String arr, Model model) {

		logger.info("tring to execute tain table");

		Mono<ClientResponse> result = client.get()
				.uri("/apiinfo/api/Plan/GetRoutes?OId=4900&TId=5000&Date=20200914&Hour=1830")// TODO!!!
				.accept(MediaType.TEXT_PLAIN).exchange();

		String resultResponse = result.flatMap(res -> res.bodyToMono(String.class)).block();
		logger.info(resultResponse);
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
}

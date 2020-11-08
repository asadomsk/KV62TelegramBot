package com.botExample.Cach;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.botExample.POJO.BotStates;
import com.botTrainTable.Train;

@Component
public class TrainSerchChatCache implements ChatCache {

	private Map<Long, BotStates> usersBotStates = new HashMap<Long, BotStates>();
	// private Map<Integer, TrainSearchRequestData> trainSearchUsersData = new
	// HashMap<>();
	// private Map<Long, List<Train>> searchFoundedTrains = new HashMap<>();

	public void setUsersCurrentBotState(Long chatId, BotStates botState) {
		usersBotStates.put(chatId, botState);
	}

	public BotStates getUsersCurrentBotState(Long userId) {
		BotStates botState = usersBotStates.get(userId);
		if (botState == null) {
			botState = BotStates.TRAINS_SEARCH;
		}

		return botState;
	}

//		 public void saveTrainSearchData(int userId, TrainSearchRequestData trainSearchData) {
//		        trainSearchUsersData.put(userId, trainSearchData);
//		    }
	//
//		   
//		    public TrainSearchRequestData getUserTrainSearchData(int userId) {
//		        TrainSearchRequestData trainSearchData = trainSearchUsersData.get(userId);
//		        if (trainSearchData == null) {
//		            trainSearchData = new TrainSearchRequestData();
//		        }
	//
//		        return trainSearchData;
//		    }
	//
//		   
//		    public void saveSearchFoundedTrains(long chatId, List<Train> foundTrains) {
//		        searchFoundedTrains.put(chatId, foundTrains);
//		    }
	//
//		 
//		    public List<Train> getSearchFoundedTrains(long chatId) {
//		        List<Train> foundedTrains = searchFoundedTrains.get(chatId);
	//
//		        return Objects.isNull(foundedTrains) ? Collections.emptyList() : foundedTrains;
//		    }

}

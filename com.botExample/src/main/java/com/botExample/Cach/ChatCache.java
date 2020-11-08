package com.botExample.Cach;

import com.botExample.POJO.BotStates;

public interface ChatCache {

	public void setUsersCurrentBotState(Long chatId, BotStates botState);

	public BotStates getUsersCurrentBotState(Long userId);

	// void saveTrainSearchData(int chatId, TrainSearchRequestData trainSearchData);

	// TrainSearchRequestData getUserTrainSearchData(int userId);

	// void saveSearchFoundedTrains(List<Train> foundTrains);

	// List<Train> getSearchFoundedTrains();

}

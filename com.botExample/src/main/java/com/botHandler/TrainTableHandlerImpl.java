package com.botHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.botExample.Cach.TrainSerchChatCache;
import com.botExample.POJO.BotStates;
import com.botExample.Services.ReplyMessagesService;
import com.botTrainTable.StationInfo;
import com.botTrainTable.TrainTable;

@Component
public class TrainTableHandlerImpl implements Handler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TrainTable trains;

	@Autowired
	private ReplyMessagesService messagesService;

	@Autowired
	private TrainSerchChatCache tc;

	String leng = "";
	int depSt;

	int arrSt;
	
	String call_data="";

	@Override
	public String getName() {
		return "trains";
	}

	@Override
	public String Execute(Update update) {
		Message message = update.getMessage();
		Long chatId = message.getChatId();
		if (tc.getUsersCurrentBotState(chatId).equals(BotStates.TRAINS_SEARCH)) {
			tc.setUsersCurrentBotState(chatId, BotStates.ASK_SEARCH_LANGUAGE);
		}
		return processUsersInput(update);
	}

	private String processUsersInput(Update update) {

		// SendMessage replyToUser = messagesService.getWarningReplyMessage(chatId,
		// "reply.trainSearch.tryAgain");
		// TrainSearchRequestData requestData =
		// userDataCache.getUserTrainSearchData(chat.getChatId());
		Message message = update.getMessage();
		Long chatId = message.getChatId();

		int iend = message.getText().indexOf("@"); // think if we really need it
		String command = message.getText().substring(0, iend);

		BotStates botState = tc.getUsersCurrentBotState(chatId);
		if (botState.equals(BotStates.ASK_SEARCH_LANGUAGE)) {
			// replyToUser = messagesService.getReplyMessage(chatId,
			// "reply.trainSearch.enterStationDepart");

			tc.setUsersCurrentBotState(chatId, BotStates.ASK_STATION_DEPART);

			return "Choose Language For Search: eng/heb/rus";
		}

		if (botState.equals(BotStates.ASK_STATION_DEPART)) {
			// replyToUser = messagesService.getReplyMessage(chatId,
			// "reply.trainSearch.enterStationDepart");

			tc.setUsersCurrentBotState(chatId, BotStates.ASK_STATION_NUMBER);

			leng = command;
			leng = leng.toLowerCase();
			System.out.println("leng " + leng);
			if (leng.equals("eng")) {
				return "Enter beginning of Station Depart: Jer/Hai/Naz";
			} else if (leng.equals("heb")) {
				return "כנס התחלה של תחנת יציאה: ירו";
			} else if (leng.equals("rus")) {
				return "Введи начало названия станции отправки: Иер/Хай/Нац";
			} else {

				tc.setUsersCurrentBotState(chatId, BotStates.ASK_STATION_DEPART);
				return "Choose Language For Search carefully: eng/heb/rus";
			}
		}

		if (botState.equals(BotStates.ASK_STATION_NUMBER)) {

			// int departureStationCode = stationCodeService.getStationCode(usersAnswer);

			tc.setUsersCurrentBotState(chatId, BotStates.ASK_DATE_DEPART);
			String response = "";
			if (leng.equals("eng")) {
				trains.getStationsEng(command);
				System.out.println(trains.getStationsMatches());
				response = "Choose station number:\n";
			} else if (leng.equals("heb")) {
				trains.getStationsHeb(command);
				response = "בחר מספר תחנה:\n";
			} else {
				trains.getStationsRus(command);
				response = "Выбери номер станции:\n";
			}

			if (trains.getStationsMatches() == null) {
				tc.setUsersCurrentBotState(chatId, BotStates.ASK_STATION_NUMBER);
				if (leng.equals("eng"))
					return "Enter beginning of Station Depart: Jer/Hai/Naz";
				else if (leng.equals("heb"))
					return "כנס התחלה של תחנת יציאה: ירו\n";
				else
					return "Введи начало названия станции отправки: Иер/Хай/Нац";
			}

			for (int i = 0; i < trains.getStationsMatches().size(); i++) {

				response += i + 1 + " - " + trains.getStationsMatches().get(i) + "\n";

			}

			return response;
		}

		if (botState.equals(BotStates.ASK_DATE_DEPART)) {
			// replyToUser = messagesService.getReplyMessage(chatId,
			// "reply.trainSearch.enterStationDepart");

			depSt = Integer.parseInt(command);// TODO throws exception
			System.out.println(depSt);
			if (depSt - 1 < 0 || depSt > trains.getStationsMatches().size()) {
				tc.setUsersCurrentBotState(chatId, BotStates.ASK_DATE_DEPART);
				String response = "Choose correct station number:\n";
				for (int i = 0; i < trains.getStationsMatches().size(); i++) {

					response += i + 1 + " - " + trains.getStationsMatches().get(i) + "\n";
				}

				return response;
			}
			tc.setUsersCurrentBotState(chatId, BotStates.ASK_TIME_DEPART);

			String stationName = trains.getStationsMatches().get(depSt - 1);
			System.out.println(stationName);
			for (StationInfo st : trains.getStations()) {
				if (st.getEng().equals(stationName) || st.getHeb().equals(stationName)
						|| st.getRus().equals(stationName)) {
					depSt = st.getId();
					System.out.println(depSt);
				}
			}

			if (leng.equals("eng"))
				return "Choose date depart";
			else if (leng.equals("heb"))
				return "תבחר תאריך יציאה";
			else
				return "Выбери дату отправления";

		}

		return null;
	}

	public String processDate(Update update) {

	    call_data = update.getCallbackQuery().getData();
		Long chat_id = update.getCallbackQuery().getMessage().getChatId();
		BotStates botState = tc.getUsersCurrentBotState(chat_id);
		if (botState.equals(BotStates.ASK_TIME_DEPART)) {

			return call_data;
		}

		return null;

	}

}

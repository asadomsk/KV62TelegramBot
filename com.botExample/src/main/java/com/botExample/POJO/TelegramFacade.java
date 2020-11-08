package com.botExample.POJO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import com.botExample.Cach.TrainSerchChatCache;
import com.botHandler.Handler;
import com.botHandler.TrainTableHandlerImpl;
import com.botHandler.WeatherHandlerImpl;

@Component
public class TelegramFacade {

	@Autowired
	TrainSerchChatCache tc;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public SendMessage handleUpdate(Update update, Map<String, Handler> commandsList) {

		SendMessage response = new SendMessage();
		if (update.hasCallbackQuery()) {
			String command = new TrainTableHandlerImpl().getName();
			String resp = (((TrainTableHandlerImpl) commandsList.get(command)).processDate(update));// sending
			Long chat_id = update.getCallbackQuery().getMessage().getChatId();
			response.setChatId(chat_id);
			response.setText(resp);
			return response;
		}

		Message message = update.getMessage();
		if (message != null && message.hasText()) {
			String inputMsg = message.getText();
			Long chatId = message.getChatId();

			response.setChatId(chatId);

			// getting command
			int iend = inputMsg.indexOf("@"); // think if we really need it
			String command;
			if (iend != -1) // TODO else
			{
				command = inputMsg.substring(0, iend);
				logger.info("contains commands :" + command);
				command = command.toLowerCase();
				// reflection
				if (commandsList.get(command) instanceof WeatherHandlerImpl) { // weather@KV62 Jerusalem
					logger.info("WeatherHandlerImpl using reflection");

					response.setText(commandsList.get(command).Execute(update));
				}
				if ((commandsList.get(command) instanceof TrainTableHandlerImpl // trains@KV62
						&& tc.getUsersCurrentBotState(chatId).equals(BotStates.TRAINS_SEARCH))
						|| !tc.getUsersCurrentBotState(chatId).equals(BotStates.TRAINS_SEARCH)) {
					logger.info("TrainTableHandlerImpl using reflection");

					command = new TrainTableHandlerImpl().getName();

					String resp = (((TrainTableHandlerImpl) commandsList.get(command)).Execute(update));// sending
					response.setText(resp); // //
					if (resp.equals("Choose date depart") || resp.equals("תבחר תאריך יציאה")
							|| resp.equals("Выбери дату отправления")) {
						Calendar cal = Calendar.getInstance();
						generateKeyboardCalendar(response, cal.getTime());
					}

				}
				return response;
			} else
				return response.setText("bot name should be entered with @ befor name tie in command");

		} else
			return response.setText("the message is empty");
	}

	/// Setting bot's keyboard
	public static final String IGNORE = "ignore!@#$%^&";
	public static final String[] WD = { "S", "M", "T", "W", "T", "F", "S" };
	Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

	public void generateKeyboardCalendar(SendMessage sendMessage, Date date) {

		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

		// row - Month and Year
		List<InlineKeyboardButton> headerRow = new ArrayList<>();
		headerRow.add(new InlineKeyboardButton().setCallbackData(IGNORE)
				.setText(new SimpleDateFormat("MMM yyyy").format(date)));
		keyboard.add(headerRow);

		// row - Days of the week
		List<InlineKeyboardButton> daysOfWeekRow = new ArrayList<>();
		for (String day : WD) {
			daysOfWeekRow.add(new InlineKeyboardButton().setCallbackData(IGNORE).setText(day));
		}
		keyboard.add(daysOfWeekRow);

		// set Calendar to the date of interest
		calendar.setTime(date);

		// Get first day of month (1)
		int firstDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);

		// Get last day of month
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		// Get actual date of first day of month
		// Set the day of the month to the first day of the month
		calendar.set(Calendar.DAY_OF_MONTH, firstDay);
		// Extract the Date from the Calendar instance
		Date firstDayOfTheMonth = calendar.getTime();

		// Get day of week of the first day of month
		int shift = calendar.get(Calendar.DAY_OF_WEEK) - 1;

		// build keyboard for specific month as parameter
		int rows = ((daysInMonth + shift) % 7 > 0 ? 1 : 0) + (daysInMonth + shift) / 7;
		for (int i = 0; i < rows; i++) {
			keyboard.add(buildRow(firstDay, daysInMonth, shift, firstDayOfTheMonth));
			firstDay += (7 - shift);
			shift = 0;
		}

		List<InlineKeyboardButton> controlsRow = new ArrayList<>();
		controlsRow.add(new InlineKeyboardButton().setCallbackData("<").setText("<"));
		controlsRow.add(new InlineKeyboardButton().setCallbackData(">").setText(">"));
		keyboard.add(controlsRow);
		markupInline.setKeyboard(keyboard);
		sendMessage.setReplyMarkup(markupInline);
	}

////
////			        List<KeyboardButton> controlsRow = new ArrayList<>();
////			        controlsRow.add(createButton("<", "<"));
////			        controlsRow.add(createButton(">", ">"));
////			        keyboard.add(controlsRow);
////			        return new Gson().toJson(keyboard);
//
//	}
//
	private List<InlineKeyboardButton> buildRow(int fromDay, int dayInMonth, int shift, Date callbackDate) {
		List<InlineKeyboardButton> row = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat("yyyyddMM");

		for (int j = 0; j < shift; j++) {
			row.add(new InlineKeyboardButton().setCallbackData(IGNORE).setText(" "));
		}

		for (int j = shift; j < 7; j++) {
			if (fromDay <= dayInMonth) {
				row.add(new InlineKeyboardButton().setCallbackData(dateFormat.format(callbackDate))
						.setText(Integer.toString(fromDay++)));

				calendar.add(Calendar.DAY_OF_MONTH, 1);
				callbackDate = calendar.getTime();
			} else {
				row.add(new InlineKeyboardButton().setCallbackData(IGNORE).setText(" "));
			}
		}
		return row;
	}

}

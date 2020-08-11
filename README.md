# KV62TelegramBot
(The bot is in the development)
##  KV62TelegramBot
This Telegram bot can be used to get the weather forecast (in Json format) for your desired city from https://openweathermap.org/api

##  Reasons for using this Telegram Bot
The idea is to create combined mobile assistant for quick and convenient fulfillment of the frequently used searches from different internet sites and for different fields of activity such as 
to save the time. 
Such as  weather forecast, train schedule, translator and so on..
Is should be the real strong personal mobile assistant.

## Use the Application
Once the application is running from the IDE you can test it by navigating to http://localhost:8080/, which is used to poll your messages/commands.


WeatherKV62Boot {City name} - Get the current day overview from https://openweathermap.org/api

The Bot will return answer in the next format

City: Saint Petersburg
Temperature: 13.57
Humidity: 67.0
Main: Clouds
http://openweathermap.org/img/wn/04d@2x.png


## Using a Webhook
Usually you’d want to use a webhook, if you’re providing a rest endpoint like this app does.

Since the telegram bot will send updates for the Bot to the external URL, a nice way to set the webhook is by using ngrok servise downloaded to the local machine.
After running the ngrok.exe, you will get https saved url as a gateway from the local machine.
The url should be updated at the Boot apps settings class and https://api.telegram.org/bot{your api token}/setWebhook should be send to Telegram server.

## Asynchronous commands
Since the bot was planned to be multiple function assistance, it has been buit to answer asynchronous to several commands at the same time.
The bot answers only to the commansd that directed only to him, in order to participate in the chats where the same commands can be sent to different bots


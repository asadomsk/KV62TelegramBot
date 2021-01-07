# KV62TelegramBot

![](Images/Tutanchamun.jpg)

Kings' Valley No. 62.  
Tomb of young pharaoh Tutankhamun in the Valley of the Kings.  
I was just thinking how to call the bot when I watched the film about the descovering Tutankhamun's tomb.  
It's really cool. Such an inspiring story! I think people need to believe in themselves more and then the great things will happen.  

## Finding king Tut's tomb
For years Howard Carter and his team scoured the rocky landscape, scarred with the trenches of previous digs.   
Discoveries were thin on the ground. In 1922 a frustrated Lord Carnarvon informed Carter he would not continue to finance the work.   
Carter pleaded with him to reconsider; moved by his passion, Carnarvon agreed to fund one last season.   
On November 1, 1922, Carter resumed digging in the Valley of the Kings. On November 4, they found the stairway that led to the unopened tomb of Tutankhamun - the 20th century's most famous find.  

##  KV62TelegramBot
This Telegram bot can be used:
1. To get the weather forecast for your desired city from https://openweathermap.org/api
2. Get the trains schedule from departure station to arrived station from https://www.rail.co.il/

##  Reasons for using this Telegram Bot
The idea is to create the combined mobile assistant for quick and convenient fulfillment of the frequently used searches from different internet sites and for different fields of activity such as weather forecast and trains schedule, translator and so on.. to save the time. 
Is should be the real strong personal mobile assistant.

## Use the Application
Once the application is running from the IDE you can test it by navigating to http://localhost:8080/, which is used to poll your messages/commands.


weather@KV62Boot {City name} - Get the current day overview from https://openweathermap.org/api
trains@KV62Bot - Get the relevant trains schedule
The Bot will ask the user the next interactive questions and will proceed his answers:
1.Choose Language For Search: eng/heb/rus
answer can be: eng@
2. Enter beginning of Station Depart: Jer/Hai/Naz
answer can be: Jer@
3. Choose station number: (all the matches of user's request)
1 - Jerusalem-Biblical Zoo
2 - Jerusalem-Malha
3 - Jerusalem - Yitzhak Navon
answer can be 2@
4. Choose date depart
After this question the Bot will display interactive keyboard to choose the actial date for search..(InlineKeyboardButton class)
...
After collecting all the parameters for the final request, the Bot will show the closest times of the relevant trains

The Bot will return answer in the next format

City: Saint Petersburg
Temperature: 13.57
Humidity: 67.0
Main: Clouds
http://openweathermap.org/img/wn/04d@2x.png

or 

rainNumber: 501
StationDepart: Station Name
TimeDepart: 14/09/2020 05:56:00
StationArrival: Station Name
TimeArrival: 14/09/2020 06:11:00
TimeInWay: 00:15:00


## Using Webhooks  
Telegram Bot API currently supports two ways of processing bot updates, getUpdates and setWebhook. getUpdates is a pull mechanism, setwebhook is push. 

https://api.telegram.org/bot(yourtoken)/setWebhook?url=https://mywebpagetorespondtobot

https://api.telegram.org/bot(yourtoken)/getUpdates

Setting a webhook means you supplying Telegram with a location in the form of a URL, on which your bot listens for updates. 


Since the telegram bot will send updates for the Bot to the external URL, a nice way to set the webhook is by using ngrok servise downloaded to the local machine.
After running the ngrok.exe, you will get https saved url as a gateway from the local machine.
The url should be updated at the Boot apps settings class and https://api.telegram.org/bot{your api token}/setWebhook should be send to Telegram server.

## Asynchronous commands
Since the bot was planned to be multiple function assistance, it has been buit to answer asynchronous to several commands at the same time.
The bot answers only to the commansd that directed only to him, in order to participate in the chats where the same commands can be sent to different bots


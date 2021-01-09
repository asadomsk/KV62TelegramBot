# KV62TelegramBot

![](Images/Tutanchamun.jpg)

Kings' Valley No. 62.  
The tomb of young pharaoh Tutankhamun in the Valley of the Kings.  
I had been just thinking how to call my bot when I watched the film about discovering Tutankhamun's tomb.  
It's really cool. Such an inspiring story! I think people need to believe in themselves more and then the great things will happen.  

## Finding king Tut's tomb
For years Howard Carter and his team scoured the rocky landscape, scarred with the trenches of previous digs.   
Discoveries were thin on the ground. In 1922 a frustrated Lord Carnarvon informed Carter he would not continue to finance the work.   
Carter pleaded with him to reconsider; moved by his passion, Carnarvon agreed to fund one last season.   
On November 1, 1922, Carter resumed digging in the Valley of the Kings. On November 4, they found the stairway that led to the unopened tomb of Tutankhamun.
It was the 20th century's most famous find.  

##  KV62TelegramBot
This Telegram bot can be used:
1. To get the weather forecast for your desired city from https://openweathermap.org/api
2. Get the trains schedule from departure station to arrived station from https://www.rail.co.il/

##  Reasons for using this Telegram Bot
The idea is to create a combined mobile assistant for quickly and conveniently performing frequently used searches on different internet sites and for various fields of activity, such as weather forecast and train timetables, translator, etc., to save time.
It should be a real strong personal mobile assistant.

## So how should you start to create your own Telegram Bot?

# לֹא יִהְיֶה לְךָ אֱלֹהִים אֲחֵרִים עַל פָּנָי
<img src = "Images/03b57814e13713da37.jfif" width =200> 
BotFather is the one bot to rule them all. It will help you create new bots and change settings for existing ones.    

Use the **/newbot** command to create a new bot. Choose a name and username for your bot. The BotFather will generate an authorization token and give life to your Bot.    
The token is required to authorize the bot and send requests to the Bot API.     
Keep your token secure and store it safely, it can be used by anyone to control your bot.    
I keep it the heart of my app - **AppSettings class.**    


BotFather is the only bot that rules them all. This will help you create new bots and change settings for existing ones.

Use the ** / newbot ** command to create a new bot. Choose a name and login for your bot. BotFather will generate an authorization token and breathe life into your new bot.
The token is required to send requests to the Bot API.
Store your token in a safe place.
I keep it at the center of my application - ** the AppSettings class. **

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
![](Images/how-webhooks-work.png)  
Telegram Bot API currently supports two ways of processing bot updates, getUpdates and setWebhook. getUpdates is a pull mechanism, setwebhook is push. 

https://api.telegram.org/bot(yourtoken)/setWebhook?url=https://mywebpagetorespondtobot

https://api.telegram.org/bot(yourtoken)/getUpdates

Setting a webhook means you supplying Telegram with a location in the form of a URL, on which your bot listens for updates. 

### So, why Webhooks??    
Imagine you’re the owner of a little library but you’re a busy person.   
So you decide to get yourself an assistant, who will keep you regularly updated with the goings-on in the library.  
Two people sign up for the interview; two very different people.  

1. There’s Mr. Andrew P. Isaac (aka API), a very efficient assistant, who gives you answers – when you ask him. 
So if you call him up and ask him “Hey! Has anyone subscribed today? Thanks!”, you’ll get the numbers that you’re looking for.  
2. Then there’s Mr. Webster Hooks (aka Webhooks), who does things a bit differently.  
As soon as someone subscribes to the library membership, he automatically sends you a message saying,  
“Hey! Sam Harris from Florida has signed up for your Platinum plan. Here’s more information about him!  
And oh, you’re welcome!”, without you asking for it.  
### The question: Whom will you hire? :wink: 

Webhooks are HTTP callbacks which are triggered by specific events. Whenever that trigger event occurs in the source site,  
the webhook sees the event, collects the data, and sends it to the URL specified by you in the form of an HTTP request.  

## Ngrok - tunnels
<img src = "Images/1024px-14-46-35-f-mutzig.jpg" width =200>     
Since our application runs on localhost, we need an utility to create secure tunnels to expose it over the web.  
Ngrok provides a publicly accessible web URL to locally hosted application.   
All you need is ngrok executable on your local machine that will initiate a tunnel to the localhost on the desired port.    
Once ngrok sets up the tunnel, it provides a web-accessible URL. And we will share it with the end-users who would hit the locally hosted application.  
i.e Telegram Bot API that will send the webhooks :point_up: :point_up: :point_up: to this URL (a subdomain of ngrok.com).  

## Asynchronous commands
Since the bot was planned to be multiple function assistance, it has been buit to answer asynchronous to several commands at the same time.
The bot answers only to the commansd that directed only to him, in order to participate in the chats where the same commands can be sent to different bots


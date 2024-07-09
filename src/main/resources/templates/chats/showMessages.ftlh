<!doctype html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Show Chats</title>
</head>
<#import "../layout.ftlh" as layout>
<@layout.layout>
    <body>
    <div class="chatAbo">
        <h2>Chat Sendungsnummer: ${chat.id}</h2>
        <button>Chat abonnieren</button>
    </div>
    <div class="chat-container">
        <div id="messages-container"></div>
    </div>
    <div class="chatBoxFooter">
        <input type="text" name="message" placeholder="Nachricht...">
        <button class="message" type="button" onclick="sendMessage()">
            <img src="/images/sendIcon.png" alt="Send" style="width: 25px;">
        </button>
    </div>
    </body>


</@layout.layout>
</html>
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
    <section class="chat-container">
        <div class="received">
            <p>Hallo Nachbar,
                du kannst gerne dein Paket heute um 18 Uhr bei mir abholen. Meine Wohnung ist in der 3. Etage. Einfach
                bei Müller klingeln.
                Viele Grüße, Thomas
            </p>
            <span class="timestamp">18:00 Uhr</span>
        </div>
        <div class="sent">
            <p>Alles klar, bis nachher. LG, Viet</p>
            <span class="timestamp">18:05 Uhr</span>
        </div>
    </section>
    <div class="chatBoxFooter">
        <input type="text" name="message" placeholder="Nachricht...">
        <button class="message" type="button" onclick="sendMessage()">
            <img src="/static/images/sendIcon.png" alt="Send" style="width: 25px;">
        </button>
    </div>
    </body>

    <script>
        const ws = new WebSocket("/chat");
        const echo = document.querySelector('.chat-container');
        ws.onmessage = function (message) {
            console.log("message: " + message.data)
            echo.innerHTML += '<div class="received">' +
                '<p>' + message.data + '</p>' +
                '<span class="timestamp">' + getTimestamp() + '</span>' +
                '</div>';
        }

        function sendMessage() {
            const input = document.querySelector('input[name="message"]');
            const messageText = input.value;

            const messageHtml = '<div class="sent">' +
                '<p>' + messageText + '</p>' +
                '<span class="timestamp">' + getTimestamp() + '</span>' +
                '</div>';

            const chatContainer = document.querySelector('.chat-container');
            chatContainer.innerHTML += messageHtml;

            ws.send(messageText);

            input.value = '';
        }

        function getTimestamp() {
            //get date chat gpt
            const currentTime = new Date();
            const timestamp = currentTime.getHours() + ':' + currentTime.getMinutes().toString().padStart(2, '0');
            return timestamp;
        }

    </script>
</@layout.layout>
</html>
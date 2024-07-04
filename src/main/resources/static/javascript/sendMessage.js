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
    return currentTime.getHours() + ':' + currentTime.getMinutes().toString().padStart(2, '0');
}
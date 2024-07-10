const ws = new WebSocket("/chat");
const echo = document.querySelector('.chat-container');

fetch('/messages/' + document.getElementById('trackingNumber').textContent.split(': ')[1] + '/chat')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(messages => {
        for (let message of messages) {
            let messageDiv = document.createElement('div');
            messageDiv.classList.add(message.sender === 'User' ? 'sent' : 'received');

            let messageText = document.createElement('p');
            messageText.textContent = message.text;
            messageDiv.appendChild(messageText);

            let timestampSpan = document.createElement('span');
            timestampSpan.classList.add('timestamp');
            let date = new Date(message.createdAt);
            timestampSpan.textContent = date.toLocaleDateString('de-DE', {year: 'numeric', month: '2-digit', day: '2-digit'}) + ', ' + date.toLocaleTimeString('de-DE', {hour: '2-digit', minute: '2-digit', second: '2-digit'});
            messageDiv.appendChild(timestampSpan);

            echo.appendChild(messageDiv);
        }
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });

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



/*
document.addEventListener('DOMContentLoaded', function() {
    const urlSegments = window.location.pathname.split('/');
    const trackingNumber = urlSegments[urlSegments.length - 1]; // Chat-ID ist das letzte Segment der URL

    fetch(`/messages/${trackingNumber}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(messages => {
            const messagesContainer = document.getElementById('messages-container');
            messages.forEach(message => {
                const messageElement = document.createElement('div');
                messageElement.classList.add('message'); // Fügen Sie hier zusätzliche Klassen hinzu, um das Styling zu unterstützen
                messageElement.innerHTML = `
                <p>${message.text}</p>
                <span class="timestamp">${new Date(message.createdAt).toLocaleTimeString()}</span>
            `;
                messagesContainer.appendChild(messageElement);
            });
        })
        .catch(error => console.error('Fehler beim Abrufen der Nachrichten:', error));
});*/

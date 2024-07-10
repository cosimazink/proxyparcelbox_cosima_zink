
    const ws = new WebSocket("/messages");
    const echo = document.querySelector('.chat-container');
    const id = document.getElementById('id').textContent.split(': ')[1];

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
        echo.innerHTML += messageHtml;

        const id = document.getElementById('id').textContent.split(': ')[1];

        const message = {
            sender: 'User',
            text: messageText
        };

        fetch('/messages/' + id, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(message)
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        }).catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
        input.value = '';

        /*const chatContainer = document.querySelector('.chat-container');
        chatContainer.innerHTML += messageHtml;*/

        ws.send(messageText);

        input.value = '';
    }

    function getTimestamp() {
        //get date chat gpt
        const currentTime = new Date();
        return currentTime.getHours() + ':' + currentTime.getMinutes().toString().padStart(2, '0');
    }

    document.getElementById('sendMessage').addEventListener('click', sendMessage);


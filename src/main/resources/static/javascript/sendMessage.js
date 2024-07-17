    const ws = new WebSocket("/chat");
    const echo = document.querySelector('.chat-container');

    ws.onmessage = function (message) {
        console.log("message: " + message.data)
        echo.innerHTML += '<div class="received">' +
            '<p>' + message.data + '</p>' +
            '<span class="timestamp">' + getTimestamp() + '</span>' +
            '</div>';
    }

    function notifySubscribers(message, id, emailMessage) {
        fetch(`/chats/subscribers/${id}`)
            .then(response => response.json())
            .then(subscribers => {
                subscribers.forEach(email => {
                    if (email === emailMessage) {
                        console.log("E-Mail wird nicht an " + email + " gesendet")
                    } else {
                        const url = `/emails/notify/${id}`;
                        const params = new URLSearchParams({email, message});
                        fetch(url, {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/x-www-form-urlencoded',
                            },
                            body: params
                        })
                            .then(response => {
                                if (!response.ok) {
                                    throw new Error('E-Mail konnte nicht gesendet werden');
                                }
                                console.log("E-Mail erfolgreich an " + email + " gesendet");
                            })
                            .catch(error => console.error('Error:', error));
                    }
                });
            })
            .catch(error => console.error('Error:', error));
    }

    function sendMessage() {
        const input = document.querySelector('input[name="message"]');
        const inputEmail = document.querySelector('input[name="userEmail"]');
        const messageText = input.value;
        const emailText = inputEmail.value;

        echo.innerHTML += '<div class="sent">' +
            '<p>' + messageText + '</p>' +
            '<span class="timestamp">' + getTimestamp() + '</span>' +
            '</div>';

        const id = document.getElementById('id').textContent.split(': ')[1];

        const message = {
            sender: 'User',
            text: messageText,
            email: emailText
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
            notifySubscribers(messageText, id, emailText);
        }).catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });

        ws.send(messageText);

        input.value = '';
    }

    function getTimestamp() {
        const currentTime = new Date();
        return currentTime.getHours() + ':' + currentTime.getMinutes().toString().padStart(2, '0');
    }

    document.getElementById('sendMessage').addEventListener('click', sendMessage);

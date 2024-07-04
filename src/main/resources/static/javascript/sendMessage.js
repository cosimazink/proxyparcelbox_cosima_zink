// Get the elements
var sendMessageButton = document.querySelector('.chatBoxFooter button.message');
var userMessageInput = document.querySelector('.chatBoxFooter input[name="message"]');
var messagesDiv = document.querySelector('.chatBox');

// Listen for the 'click' event on the send button
sendMessageButton.addEventListener('click', function() {
    // Get the user's message
    var userMessage = userMessageInput.value.trim();

    // Check if the message is not empty
    if (userMessage) {
        // Create a new 'div' element for the message
        var messageDiv = document.createElement('div');
        messageDiv.classList.add('sent'); // Add the 'sent' class to the div

        // Create a new 'p' element for the message text
        var messageText = document.createElement('p');
        messageText.textContent = userMessage; // Set the text

        // Append the message text to the message div
        messageDiv.appendChild(messageText);

        // Create a new 'span' element for the timestamp
        var timestampSpan = document.createElement('span');
        timestampSpan.classList.add('timestamp'); // Add the 'timestamp' class to the span

        // Get the current time
        var currentTime = new Date();
        var hours = currentTime.getHours();
        var minutes = currentTime.getMinutes();
        var formattedTime = (hours < 10 ? '0' : '') + hours + ':' + (minutes < 10 ? '0' : '') + minutes;

        // Set the timestamp text
        timestampSpan.textContent = formattedTime;

        // Append the timestamp to the message div
        messageDiv.appendChild(timestampSpan);

        // Append the message div to the messages div
        messagesDiv.appendChild(messageDiv);

        // Clear the input box
        userMessageInput.value = '';
    }
});
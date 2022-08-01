var stompClient = null;

function setConnected(connected) {
    if (connected) {
        document.getElementById('websocket-connect').style.visibility = 'hidden';
        document.getElementById('websocket-disconnect').style.visibility = 'visible';
    } else {
        document.getElementById('websocket-connect').style.visibility = 'visible';
        document.getElementById('websocket-disconnect').style.visibility = 'hidden';
    }
}

function connect() {

    var socket = new SockJS('/palindrome');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {

        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function(messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body));
        });
    });
}

function disconnect() {

    if(stompClient != null) {
        stompClient.disconnect();
    }

    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    var palindromeWord = document.getElementById("palindromeWord");
    var dateTime = new Date();
    stompClient.send("/app/palindrome", {}, JSON.stringify({'content': palindromeWord.value, 'timestamp': dateTime.toISOString()}));
    palindromeWord.value = "";
}

function showMessageOutput(messageOutput) {

    var historyList = document.getElementById('palindrome-history');
    var history = document.createElement('li');
    history.classList.add('list-group-item');
    var dateTime = new Date(messageOutput.timestamp);
    history.innerHTML = messageOutput.content + " word has Longest Palindrome size of " + messageOutput.longest_timestamp_size
        + " ("+ dateTime + ")";
    historyList.appendChild(history);
    document.getElementById('palindrome-history').style.visibility = "visible";
    document.getElementById('palindrome-history-nil').style.visibility = "hidden";
}
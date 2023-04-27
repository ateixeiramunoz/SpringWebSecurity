var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        $("#connection-status").text("Conectado");
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
           showGreeting(JSON.parse(greeting.body).content);
        });

        stompClient.subscribe('/user/specific', function (message) {
            console.log("RECIBIDO MENSAJE PERSONAL");
            showPrivate(JSON.parse(message.body).text, JSON.parse(message.body).from );
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    $("#connection-status").text("Desconectado");
      $("#greetings").empty();
      $("#connection-status").after("<p>Conecta para consultar o enviar mensajes</p>");
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function sendPrivate() {

    var text = document.getElementById('msgTxt').value;
    var to = document.getElementById('namePrivate').value;
    var from = document.getElementById('userID').value;
    stompClient.send("/app/private", {}, JSON.stringify({'text':text, 'to':to, 'from':from}));

    $("#greetings").append("<tr><td>YO: " + text + "</td></tr>");


}





function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}


function showPrivate(message, from) {

    $("#greetings").append("<tr><td>" + from + " : " + message + "</td></tr>");


}



$(function () {

    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#sendPrivate" ).click(function() { sendPrivate(); });
});

//https://stackoverflow.com/questions/4842590/how-to-run-a-function-when-the-page-is-loaded
window.onload = function () {

 connect();

};
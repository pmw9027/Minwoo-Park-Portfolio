/**
 * Created by Minwoo on 2017. 4. 8..
 */
var MyWebSocket = (function() {
    const SERVER_SOCKET_API = "/websockethandler";
    var stompClient;
    var text=[1,100];
    function connect() {
        var socket = new SockJS(SERVER_SOCKET_API);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
            stompClient.subscribe('/topic/roomId', function (msg) {
                text = JSON.parse(msg.body).content;
            });
        });
    }

    function printMessage(message) {
        textArea.value += message + "\n";
    }

    function sendMessage(text) {
        stompClient.send("/app/hello", {}, JSON.stringify({'content': text}));
        setTimeout(this.sendMessage("test"), 3000);
    }

    function init() {
        connect();
    }
    return {
        init : init
    }
})();

MyWebSocket.sendMessage("test")

console.log("ttttt");
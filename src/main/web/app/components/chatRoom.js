var React = require('react');

var ChatMessage = require('./chatMessage');

var SockJS = require("sockjs-client")
var Stomp = require("stompjs/lib/stomp.js").Stomp

console.log("stomp is: " + JSON.stringify(Stomp))
var stompClient =  null;
window.stompClient = stompClient;
var ChatRoom = React.createClass({
    propTypes : {
      users : React.PropTypes.arrayOf(React.PropTypes.string),
      messages : React.PropTypes.arrayOf(
        React.PropTypes.shape({
          user: React.PropTypes.string,
          message: React.PropTypes.string
        }))
    },

    render: function() {

        //var socket = new SockJS('http://localhost:9876/folieSocket');
        var socket = new SockJS('http://localhost:9876/foliechatt/folieSocket');
        stompClient = Stomp.over(socket);
        console.log("stomp.over..  stompClient is: ", stompClient)
        stompClient.connect({}, function (frame) {
            // setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/greetings', function (greeting) {
                // showGreeting(JSON.parse(greeting.body).content);
                console.log("Received greeting " + greeting)
            });
        });

        setTimeout( function() {
            stompClient.send("/app/hello", {}, JSON.stringify(
                {
                    content:"Hello from client!",
                    sender: { value: "1"},
                    receiver: { value: "2"}

                }))
        }, 5000
        );





        return (
            <div className="chatRoom">
                <ul>
                    <ChatMessage userName={this.props.messages[0].user} messageText={this.props.messages[0].message}/>

                </ul>


            </div>
        );

    }
});

module.exports = ChatRoom;

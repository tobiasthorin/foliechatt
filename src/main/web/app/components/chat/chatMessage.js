var React = require('react');
let baffle = require('baffle');
const CHARACTER_SET = 'abcdeABCDE⏈ᖈᛤḕԽ₮⅃₾';
const baffleOptions = {
    characters: CHARACTER_SET
}

const baffleMessageNode = function(id, text) {
    let node = document.getElementById(id);
    let b = baffle(node, baffleOptions).start();
    b.text(currentText => text).reveal(2500);
}

var ChatMessage = React.createClass({
    propTypes: {
        userName: React.PropTypes.string,
        messageText: React.PropTypes.string,
        shouldBaffle: React.PropTypes.bool,
        nodeId: React.PropTypes.string
    },

    componentDidMount: function() {
        const {nodeId, messageText, shouldBaffle} = this.props;
        if(shouldBaffle) {
            baffleMessageNode(nodeId, messageText)
        }
    },

    render: function() {
        const {nodeId, userName, shouldBaffle, messageText} = this.props;
        let message = shouldBaffle ? '' : messageText;
        return (
            <li>
                <span>{userName}: </span>
                <span id={nodeId}>{message}</span>
            </li>
        );
    }
})

module.exports = ChatMessage;

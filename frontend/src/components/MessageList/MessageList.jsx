import React from "react";
import Message from "../Message/Message";

const MessageList = ({messages}) => {
    return (
        messages.map(message =>
            <div key={message.id}>
                <Message message={message} key={message.id}/>
            </div>
        )
    )
}

export default MessageList;
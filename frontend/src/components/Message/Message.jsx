import React from "react";

const Message = (props) => {
    return (
        <div className={props.message.class}>
            <h2 className="msg">{props.message.nickname}</h2>
            <p className="msg">{props.message.text}</p>
        </div>
    );
};

export default Message;
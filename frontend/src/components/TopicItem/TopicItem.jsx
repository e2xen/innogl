import React from "react";

const TopicItem = (props) => {
    return (
        <button className="lil">{props.topic.name}</button>
    )
}

export default TopicItem;
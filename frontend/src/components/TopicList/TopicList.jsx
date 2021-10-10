import React, {useState} from "react";
import TopicItem from "../TopicItem/TopicItem";

const TopicList = () => {
    const [topics, setTopics] = useState([
        {'name': 'First name'},
        {'name': 'Second name'}
    ]);

    return (
        <ul className="ull">
            <div>
                {topics.map( topic =>
                    <TopicItem topic={topic}/>
                )}
            </div>
        </ul>
    )
}

export default TopicList;
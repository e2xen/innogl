import React, {useEffect, useMemo, useState} from "react";
import './FindStranger.css'
import {useHistory} from 'react-router-dom';
import TopicList from "../TopicList/TopicList";
import Chat from "../Chat/Chat";
import UserService from "../../API/UserService";
import {useFetching} from "../../hooks/useFetching";
import ChatService from "../../API/ChatService";
import BasicHandlerError from "../../services/BasicHandlerError";

function FindStranger() {
    document.title = useMemo(() => {
        return 'Find a stranger...'
    }, []);

    const history = useHistory();
    const [topic, setTopic] = useState('');
    const [activeChattingState, setActiveChattingState] = useState(false);
    const [anonymousRegistration, setAnonymousRegistration] = useState();

    const [fetchRegisterUser, errorRegisterUser] = useFetching(async () => {
        const registration = await UserService.registerUserAnonymously();
        setAnonymousRegistration(registration);
    });
    if (errorRegisterUser) {
        BasicHandlerError.handleError(history, "We are so sorry and fix it so soon! Something went wrong with anonymous registration...");
    }

    useEffect(() => {
        fetchRegisterUser();
    }, []);

    const handleRandomlyFindSomeoneClick = (e) => {
        e.preventDefault();

        // Disable all buttons
        setActiveChattingState(true);
    };

    // Rendering
    if (activeChattingState && anonymousRegistration != null) {
        return (
            <Chat anonymousRegistration={anonymousRegistration} topic={topic} />
        );
    }

    return (
        <section className="firsttopic">
            <div className="left">
                <h2>
                    Choose a stranger!
                </h2>
                <p>
                    Using our services you automatically agree with <a href="/privacy">our Policy</a>
                </p>
                <button className="brown" onClick={handleRandomlyFindSomeoneClick}
                        disabled={activeChattingState}>Randomly Find Someone
                </button>
                <p>Or you can find stranger specially to your topic:</p>
                <form className="find-stranger">
                    <input className="inp" type="text" id="fname" name="topic"
                           onChange={e => setTopic(e.target.value)} placeholder="Your super topic..."/>
                    <button onClick={handleRandomlyFindSomeoneClick} className="but"
                            disabled={activeChattingState}>Find Someone
                    </button>
                </form>
                <p className="choose-stranger">Or choose topic of other strangers:</p>
                <TopicList/>
            </div>
        </section>
    );
}

export default FindStranger;

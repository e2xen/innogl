import React, {useEffect, useMemo, useState} from "react";
import {Stomp} from "@stomp/stompjs";
import * as SockJS from 'sockjs-client';
import {decode as base64_decode, encode as base64_encode} from 'base-64';
import './Chat.css'
import MessageList from "../MessageList/MessageList";
import {v4 as uuidv4} from 'uuid';
import FunnyNamesGeneratorService from "../../services/FunnyNamesGeneratorService";
import {useFetching} from "../../hooks/useFetching";
import ChatService from "../../API/ChatService";
import BasicHandlerError from "../../services/BasicHandlerError";
import {useHistory} from "react-router-dom";
import Loader from "../Loader/Loader";

function Chat({anonymousRegistration, topic}) {
    if (anonymousRegistration == null) throw new Error('Object `anonymousRegistration` cannot be null.');
    document.title = useMemo(() => {
        return 'Chatting with a stranger...'
    }, []);

    const history = useHistory();
    const [isSearchingForStranger, setIsSearchingForStranger] = useState(true);
    const [chatInfo, setChatInfo] = useState();
    const [isChatLoading, setIsChatLoading] = useState(true);
    const [userMessage, setUserMessage] = useState('');
    const [subscription, setSubscription] = useState();
    const [messages, setMessages] = useState([]);
    const [receivedMessages] = useState([]);
    const subscribeDestination = useMemo(() => {
        if (chatInfo == null) return null;
        return `/chat/${chatInfo.chatId}/queue/messages`;
    }, [chatInfo]);
    const sendDestination = useMemo(() => {
        return '/app/send-message';
    }, []);
    const [currentSessionNumber, setCurrentSessionNumber] = useState(0);
    const socket = useMemo(() => {
        return new SockJS(`${process.env.REACT_APP_BASE_URL}/stomp-js`);
    }, [currentSessionNumber]);
    const stompClient = useMemo(() => {
        if (socket !== null) {
            return Stomp.over(socket);
        }
    }, [socket]);
    const [fetchChatSession, errorChatSession] = useFetching(async (userId, topic) => {
        const chatInfo = await ChatService.findChat(userId, topic);
        setChatInfo(chatInfo);
        setIsSearchingForStranger(false);
    });

    if (errorChatSession) {
        BasicHandlerError.handleError(history, "We are so sorry and fix it so soooon! Something went wrong during the initialization of the chat...")
    }

    useEffect(() => {
        if (isSearchingForStranger) {
            let topicInput = (topic.trim() !== "") ? topic : null;
            fetchChatSession(anonymousRegistration.userId, topicInput);
        }
    }, [isSearchingForStranger]);

    const processSystemMessage = (messageObject) => {
        let messageContent = base64_decode(messageObject.content);
        switch (messageContent) {
            case 'START':
                setIsChatLoading(false);
                break;

            case 'END':
                alert('You are disconnected from the chat! But be ready for a new stranger :)');
                setMessages([]);
                setChatInfo(null);
                setUserMessage('');
                receivedMessages.length = 0;
                setIsSearchingForStranger(true);
                setIsChatLoading(true);
                setCurrentSessionNumber(currentSessionNumber + 1);
                break;

            default:
                throw Error(`Unknown system message received with the content: ${messageContent}`)
        }
    }

    const processMessage = (msg) => {
        console.log('Body = ' + msg.body);
        const messageObject = JSON.parse(msg.body);

        switch (messageObject.type) {
            case "SYSTEM":
                processSystemMessage(messageObject);
                break;

            case "USER":
                const newMessage = {
                    'class': (messageObject.senderId.localeCompare(anonymousRegistration.userId) === 0) ? 'right2' : 'left2',
                    'nickname': FunnyNamesGeneratorService.generateRandomName(),
                    'text': base64_decode(messageObject.content),
                    'id': messageObject.id
                };

                receivedMessages.push(newMessage);
                setMessages([...receivedMessages]);
                break;

            default:
                throw Error(`Unexpected type of the message was received: ${messageObject.type}`);
        }

        msg.ack();
    };

    useEffect(() => {
        if (stompClient != null && chatInfo != null) {
            console.log("reconnecting to:")
            console.log(chatInfo);
            stompClient.connect({}, (frame) => {
                console.log('Connected: ' + frame);

                const stompHeaders = {
                    "Authorization": anonymousRegistration.secretToken
                };

                const subscription = stompClient.subscribe(subscribeDestination, processMessage, stompHeaders);
                setSubscription(subscription);
            }, () => {
                alert("Unknown error occurred during connecting to the socket :(");
            });
        }
    }, [stompClient, chatInfo]);

    const sendMessage = (content) => {
        const stompHeaders = {
            'Authorization': anonymousRegistration.secretToken
        };

        let messageObjectToSend = {
            'id': uuidv4(),
            'senderId': anonymousRegistration.userId,
            'type': 'USER',
            'chatId': chatInfo.chatId,
            'content': base64_encode(content)
        };

        stompClient.send(sendDestination, stompHeaders, JSON.stringify(messageObjectToSend));
        setUserMessage('');
    };

    const findAnotherStranger = () => {
        const stompHeaders = {
            'Authorization': anonymousRegistration.secretToken,
            'chatId': chatInfo.chatId
        };

        stompClient.unsubscribe(subscription, stompHeaders);
        stompClient.disconnect();
    };

    const sendMessageForm = (e) => {
        e.preventDefault();
        if (userMessage.trim() === "") {
            alert("Message must NOT be an empty string, invent something! :)");
            return;
        }

        sendMessage(userMessage);
    };

    return (
        <div>
            <section className="first">
                <div className="left">
                    <div className="box">
                        <div className="tit">Video Chat</div>
                        <div className="left2">
                            <p className="space">Under development, wait a few, please :)</p>
                        </div>
                        <button className="khara" onClick={findAnotherStranger} disabled={isChatLoading}>Find Another
                            Stranger
                        </button>
                    </div>
                </div>
                <div className="right">
                    <div className="box">
                        <div className="tit">Chatting</div>
                        <div className="messages-block">
                            {isChatLoading === true
                                ? <Loader text="Looking for a stranger..."/>
                                : <MessageList messages={messages}/>
                            }
                        </div>
                    </div>
                    <form>
                        <div>
                            <input className="text" onChange={e => setUserMessage(e.target.value)} type="text"
                                   id="fname" name="topic" value={userMessage} placeholder="Enter your message here"/>
                        </div>
                        <button className="send" onClick={sendMessageForm} type="submit">Send</button>
                    </form>
                </div>
            </section>
        </div>
    );
}

export default Chat;

import axios from "axios";

export default class ChatService {
    static async findChat(userId, topic) {
        const body = {
            'userId': userId,
            'topic': topic
        };
        const response = await axios.post(`${process.env.REACT_APP_BASE_URL}/api/chat-session`, body);
        return response.data;
    }
};
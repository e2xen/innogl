import axios from "axios";

export default class UserService {
    static async registerUserAnonymously() {
        const response = await axios.post(`${process.env.REACT_APP_BASE_URL}/api/user`);
        return response.data
    }
};
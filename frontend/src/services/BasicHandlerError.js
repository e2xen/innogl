export default class BasicHandlerError {
    static handleError(history, textError) {
        alert(textError);
        history.push("/");
    }
}
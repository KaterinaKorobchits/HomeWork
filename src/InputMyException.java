public class InputMyException extends Exception {

    public InputMyException(String message) {
        super(message);
    }

    public String getRussianMessage(){
        return "Ошибка при вводе";
    }
}

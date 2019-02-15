public class CalculationMyException extends Exception{

    public CalculationMyException(String message) {
        super(message);
    }

    public String getRussianMessage(){
        return "Ошибка при выполнении операции деления на 0";
    }
}

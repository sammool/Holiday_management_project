package sammool.holiday.web.exception;

public class NegativeDayException extends RuntimeException{
    public NegativeDayException(String message) {
        super(message);
    }
}

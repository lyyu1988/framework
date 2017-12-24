package cn.campus.platfrom.exception;

public class AppIdException extends Throwable {
    public AppIdException() {
        super();
    }

    public AppIdException(String message) {
        super(message);
    }

    public AppIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppIdException(Throwable cause) {
        super(cause);
    }

    protected AppIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

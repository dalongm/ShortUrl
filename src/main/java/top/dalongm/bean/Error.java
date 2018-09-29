package top.dalongm.bean;

public class Error {
    private String message;
    private Integer code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Error{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}

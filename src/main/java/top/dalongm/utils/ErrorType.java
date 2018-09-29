package top.dalongm.utils;

public enum ErrorType {
    SUCCESS(200, "成功"),
    ADD_FAIL(201,"创建失败"),
    TIME_INVALID(202,"短链接已过期!"),
    TIMES_INVALID(203,"短链接访问次数达上限!"),
    PASS_ERROR(204,"密码错误!"),
    SHORT_URL_INVALID(205,"短链接失效!"),
    ADD_FAIL_SHORT_REPEAT(206,"短链接重复!"),
    URL_INVALID(207,"链接无效!"),
    FAILED(222,"失败!"),
    ;

    private int code;
    private String message;

    ErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

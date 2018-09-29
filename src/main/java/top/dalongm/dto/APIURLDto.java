package top.dalongm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import top.dalongm.bean.URL;
import top.dalongm.utils.ErrorType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIURLDto extends URL {
    private ErrorType error;

    private String basePath;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public ErrorType getError() {
        return error;
    }

    public void setError(ErrorType error) {
        this.error = error;
    }
}

package top.dalongm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import top.dalongm.bean.URL;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class URLDto extends URL {
    private Boolean upAlphabet;
    private Boolean lowAlphabet;
    private Boolean number;
    private Long sUrlLen;

    public Boolean getUpAlphabet() {
        return upAlphabet;
    }

    public void setUpAlphabet(Boolean upAlphabet) {
        this.upAlphabet = upAlphabet;
    }

    public Boolean getLowAlphabet() {
        return lowAlphabet;
    }

    public void setLowAlphabet(Boolean lowAlphabet) {
        this.lowAlphabet = lowAlphabet;
    }

    public Boolean getNumber() {
        return number;
    }

    public void setNumber(Boolean number) {
        this.number = number;
    }

    public Long getsUrlLen() {
        return sUrlLen;
    }

    public void setsUrlLen(Long sUrlLen) {
        if(sUrlLen>=10){
            this.sUrlLen = 10L;
        }else if(sUrlLen<=5){
            this.sUrlLen = 5L;
        }else{
            this.sUrlLen = sUrlLen;
        }
    }
}

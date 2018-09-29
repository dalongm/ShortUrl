package top.dalongm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import top.dalongm.bean.URL;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class URLDto extends URL {

}

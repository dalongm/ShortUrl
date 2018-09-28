package top.dalongm.service;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.dalongm.bean.URL;
import top.dalongm.dto.URLDto;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-web.xml"})
public class URLServiceTest {
    @Autowired
    private URLService urlService;

    @Test
    public void getList() {
        URL tempUrl = new URL();
        tempUrl.setId(1L);
        List<URLDto> urlDtoList = urlService.getList();
        Gson gson = new Gson();
        System.out.println(gson.toJson(urlDtoList));
    }


    @Test
    public void add() {
        URL tempUrl = new URL();
        tempUrl.setUrl("http://www.baidu.com");
        URLDto urlDto = new URLDto();
        BeanUtils.copyProperties(tempUrl, urlDto);
        if (urlService.add(urlDto)) {
            System.out.println("增加成功！");
        } else {
            System.out.println("增加失败！");
        }
    }

}

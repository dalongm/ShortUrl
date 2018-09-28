package top.dalongm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.dalongm.bean.URL;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class URLDaoTest {
    @Resource
    private URLDao urlDao;

    @Test
    public void selectOne() {
        URL url = urlDao.selectById(1L);
        System.out.println(url);

        URL urlselect = new URL();
        urlselect.setsUrl("1");
        List<URL> urls = urlDao.select(urlselect);
        for (URL u : urls) {
            System.out.println(u);
        }

        url = urlDao.selectBySUrl("1");
        System.out.println(url);

        url = urlDao.selectByUrl("http://dalongm.top");
        System.out.println(url);
    }

    @Test
    public void updateVisited() {
        URL url = urlDao.selectById(1L);
        System.out.println(url);

        url.setVisited(url.getVisited() + 1);
        url.setCreateTime(new Date());
        urlDao.update(url);

        System.out.println(url);
    }
}

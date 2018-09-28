package top.dalongm.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import top.dalongm.bean.URL;
import top.dalongm.dao.URLDao;
import top.dalongm.dto.URLDto;
import top.dalongm.service.URLService;
import top.dalongm.utils.URLs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class URLServiceImpl implements URLService {

    @Autowired
    private URLDao urlDao;

    @Override
    public List<URLDto> getList() {
        List<URL> urlList = urlDao.select(null);
        List<URLDto> urlDtoList = new ArrayList<>(urlList.size());
        for (URL url : urlList) {
            URLDto urlDto = new URLDto();
            BeanUtils.copyProperties(url, urlDto);
            urlDtoList.add(urlDto);
        }
        return urlDtoList;
    }

    @Override
    public boolean add(URLDto urlDto) {
        // 网址不能为空
        if (!URLs.valid(urlDto.getUrl())) {
            return false;
        }

        URL url = new URL();
        BeanUtils.copyProperties(urlDto, url);
        // 已有该短地址
        if (url.getsUrl() != null && urlDao.selectBySUrl(url.getsUrl()) != null) {
            return false;
        }
        if (url.getsUrl() == null||url.getsUrl().trim().equals("")) {
            // 获得有效随机地址
            for (; ; ) {
                String sUrl = URLs.getRandom(5);
                if (urlDao.selectBySUrl(sUrl) == null) {
                    url.setsUrl(sUrl);
                    break;
                }
            }
        }
        url.setCreateTime(new Date());
        url.setVisited(0L);
        return urlDao.insert(url) == 1;
    }

    @Override
    public boolean update(URLDto urlDto) {
        URL url = new URL();
        BeanUtils.copyProperties(urlDto, url);
        return urlDao.update(url) == 1;
    }

    @Override
    public boolean remove(URLDto urlDto) {
        return urlDao.delete(urlDto.getId()) == 1;
    }

    @Override
    public URLDto getById(Long id) {
        URL url = urlDao.selectById(id);
        URLDto urlDto = new URLDto();
        BeanUtils.copyProperties(url, urlDto);
        return urlDto;
    }

    @Override
    public URLDto getBySUrl(String sUrl) {
        URL url = urlDao.selectBySUrl(sUrl);
        URLDto urlDto = new URLDto();
        BeanUtils.copyProperties(url, urlDto);
        return urlDto;
    }

    @Override
    public URLDto getByUrl(String u) {
        URL url = urlDao.selectByUrl(u);
        URLDto urlDto = new URLDto();
        if(url!=null){
            BeanUtils.copyProperties(url, urlDto);
        }
        return urlDto;
    }
}

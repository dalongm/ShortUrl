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
    public URLDto add(URLDto urlDto) {
        // 网址不能为空
        if (!URLs.valid(urlDto.getUrl())) {
            return null;
        }
        setURLDtoDefault(urlDto);

        URL url = new URL();
        BeanUtils.copyProperties(urlDto, url);
        // 已有该短地址
        if (url.getsUrl() != null && urlDao.selectBySUrl(url.getsUrl()) != null) {
            return null;
        }
        if (url.getsUrl() == null || url.getsUrl().trim().equals("")) {
            // 获得有效随机地址
            List<Character> charList = getCharList(urlDto);
            String sUrl = null;
            for (; ; ) {
                if (charList.size() == 0) {
                    sUrl = URLs.getRandom(urlDto.getsUrlLen().intValue());
                } else {
                    sUrl = URLs.getRandom(charList, urlDto.getsUrlLen().intValue());
                }
                if (urlDao.selectBySUrl(sUrl) == null) {
                    url.setsUrl(sUrl);
                    break;
                }
            }
        }
        url.setCreateTime(new Date());
        url.setVisited(0L);
        urlDao.insert(url);
        BeanUtils.copyProperties(url, urlDto);
        return urlDto;
    }

    private static List<Character> getCharList(URLDto urlDto) {
        List<Character> list = new ArrayList<>();
        if (urlDto.getLowAlphabet()) {
            for (Character i = 'a'; i <= 'z'; i++) {
                list.add(i);
            }
        }
        if (urlDto.getUpAlphabet()) {
            for (Character i = 'A'; i <= 'Z'; i++) {
                list.add(i);
            }
        }
        if (urlDto.getNumber()) {
            for (Character i = '0'; i <= '9'; i++) {
                list.add(i);
            }
        }
        return list;
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
        if (url == null) {
            return null;
        }
        URLDto urlDto = new URLDto();
        BeanUtils.copyProperties(url, urlDto);
        return urlDto;
    }

    @Override
    public URLDto getBySUrl(String sUrl) {
        URL url = urlDao.selectBySUrl(sUrl);
        if (url == null) {
            return null;
        }
        URLDto urlDto = new URLDto();
        BeanUtils.copyProperties(url, urlDto);
        return urlDto;
    }

    @Override
    public List<URLDto> getByUrl(String u) {
        List<URL> urls = urlDao.selectByUrl(u);
        URLDto urlDto;
        List<URLDto> urlDtos = new ArrayList<>();
        if (urls != null) {
            for (URL url : urls) {
                urlDto = new URLDto();
                BeanUtils.copyProperties(url, urlDto);
                urlDtos.add(urlDto);
            }
        }
        return urlDtos;
    }

    @Override
    public boolean incVisitedById(Long id) {
        URL url = new URL();
        url.setId(id);
        url.setLastVisitTime(new Date());
        return urlDao.updateVisited(url) == 1;
    }

    private static void setURLDtoDefault(URLDto urlDto) {
        if (urlDto.getValidTime() == null) {
            urlDto.setValidTime(365.0);
        }
        if (urlDto.getValidTimes() == null) {
            urlDto.setValidTimes(100000L);
        }
        if (urlDto.getNumber() == null) {
            urlDto.setNumber(false);
        }
        if (urlDto.getUpAlphabet() == null) {
            urlDto.setUpAlphabet(false);
        }
        if (urlDto.getLowAlphabet() == null) {
            urlDto.setLowAlphabet(false);
        }
        if (urlDto.getsUrlLen() == null) {
            urlDto.setsUrlLen(5L);
        }
    }

}

package top.dalongm.service;

import top.dalongm.bean.URL;
import top.dalongm.dto.URLDto;

import java.util.List;

public interface URLService {

    List<URLDto> getList();

    URLDto getBySUrl(String sUrl);

    List<URLDto> getByUrl(String url);

    URLDto add(URLDto urlDto);

    boolean update(URLDto urlDto);

    boolean incVisitedById(Long id);

    boolean remove(URLDto urlDto);

    URLDto getById(Long id);
}

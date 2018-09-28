package top.dalongm.dao;

import top.dalongm.bean.URL;

import java.util.List;

public interface URLDao {
    List<URL> select(URL url);

    int insert(URL url);

    URL selectById(Long id);

    URL selectByUrl(String url);

    URL selectBySUrl(String url);

    int update(URL url);

    int delete(Long id);
}

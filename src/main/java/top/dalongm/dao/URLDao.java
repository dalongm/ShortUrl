package top.dalongm.dao;

import top.dalongm.bean.URL;

import java.util.List;

public interface URLDao {
    List<URL> select(URL url);

    /**
     * @author dalongm
     * @description 插入新记录
     * @date 2018/9/29 11:48
     * @param url
     * @return 返回url id
     **/
    Long insert(URL url);

    URL selectById(Long id);

    List<URL> selectByUrl(String url);

    URL selectBySUrl(String url);

    int update(URL url);

    int delete(Long id);

    int updateVisited(URL url);

    int updateVisitedById(Long id);
}

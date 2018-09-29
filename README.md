# 短网址服务

## 功能
* 长网址转短网址
* 短网址设置
    * 访问密码
    * 有效时间
    * 访问次数
* api

## 开发环境
* IntelliJ IDEA + JDK1.8 + Maven 3.5.3 + MySql 5.6

## 部署说明
使用IntelliJ IDEA打开pom.xml为Maven项目，并在右下角提示是否允许maven自动import时选择允许。  
数据库SQL转存文件在/sql文件夹下，在命令行或者数据库可视化工具中执行文件url.sql即可。  
项目中需要修改数据库配置文件，即/src/main/resources/jdbc.properties，注意连接端口号与数据库名称。
>db.dirver=com.mysql.jdbc.Driver  
>db.url=jdbc:mysql://127.0.0.1:3306/url?useUnicode=true&characterEncoding=utf8  
>db.username=username  
>db.password=password

## 可访问
http://s.dalongm.top

## API
目前含有两个api分别是
1. **创建短链接**
    * POST请求路径   
    http://s.dalongm.top/api/add
    * 请求数据
    ```json
      {
          "url": "www.baidu.com",
          "sUrl": "qsMeK",
          "validTime": 365,
          "validTimes": 100000,
          "visitPass": 123456
      }
    ```
    * 在请求中
        * url为原始链接，小于255个字符，**必选**
        * sUrl为短链接，5到10位的数字与字母组合，可选，默认随机
        * validTime为有效时长，单位（天），可选，默认365天
        * validTimes为有效次数，单位（次），可选，默认10^5次
        * visitPass为访问密码，4到10位的数字与字母组合，可选，默认无
    * 返回数据
    ```json
      {
          "url": "www.baidu.com",
          "sUrl": "qsMeK",
          "visited": 0,
          "createTime": 1538224413654,
          "validTime": 365,
          "validTimes": 100000,
          "visitPass": 123456,
          "error": "SUCCESS",
          "basePath": "http://m.dalongm.top/"
      }
    ```
    * 返回数据中
        * url为原始链接
        * sUrl为短链接，5到10位的数字与字母组合，默认随机
        * validTime为剩余有效时长，单位（天），浮点型
        * validTimes为剩余有效次数，单位（次），整型
        * visitPass为访问密码，无密码时不返回该字段
        * error为错误消息，返回"SUCCESS"表示成功  
        
    ![添加](https://raw.githubusercontent.com/dalongm/ShortUrl/master/images/add.png)

2. **转换链接**
    * POST请求路径   
    http://s.dalongm.top/api/tran
    * 请求数据
    ```json
      {
          "sUrl": "qsMeK",
          "visitPass": 123456
      }
    ```
    * 在请求中
        * sUrl为短链接，5到10位的数字与字母组合，必选，默认随机
        * visitPass为访问密码，4到10位的数字与字母组合，若无密码则无需请求该字段
    * 返回数据，见创建短链接的返回数据  
    
    ![转换](https://raw.githubusercontent.com/dalongm/ShortUrl/master/images/tran.png)
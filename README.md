# 短网址服务

## 开发环境
* IntelliJ IDEA + JDK1.8 + Maven 3.5.3 + MySql 5.6

## 说明
使用IntelliJ IDEA打开pom.xml为Maven项目，并在右下角提示是否允许maven自动import时选择允许。  
数据库SQL转存文件在/sql文件夹下，在命令行或者数据库可视化工具中执行文件url.sql即可。  
项目中需要修改数据库配置文件，即/src/main/resources/jdbc.properties，注意连接端口号与数据库名称。
>db.dirver=com.mysql.jdbc.Driver  
>db.url=jdbc:mysql://127.0.0.1:3306/url?useUnicode=true&characterEncoding=utf8  
>db.username=username  
>db.password=password

## 部署链接
www.dalongm.top
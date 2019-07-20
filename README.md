# 秒杀业务模块-Seckill

## 开发环境
![JAVA](https://img.shields.io/badge/Java-1.8-green.svg) 
![Maven](https://img.shields.io/badge/Maven-3.5-orange.svg)
![Spring](https://img.shields.io/badge/Spring-5.1-green.svg)
![Spring Security](https://img.shields.io/badge/SpringMVC-5.1-color.svg)
![MyBatis](https://img.shields.io/badge/MyBatis-3.5-red.svg)  

![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![c3p0](https://img.shields.io/badge/c3p0-8.0-orange.svg)
![jQuery](https://img.shields.io/badge/jQuery-3.3-brown.svg)
![bootstrap](https://img.shields.io/badge/Bootstrap-3.3-purple.svg)

## 效果预览
发布地址:  [https:]
![秒杀列表](https://i.loli.net/2019/07/20/5d32caf7d2cf624757.png)

## 核心实现

## 问题故障
1. *Java.lang.NoClassDefFoundError: com/fasterxml/jackson/databind/exc/InvalidDefinitionException*
即便引入了该依赖，类都存在
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.5.4</version>
</dependency>
```
>[解决方法](https://stackoverflow.com/questions/44718345/java-lang-noclassdeffounderror-com-fasterxml-jackson-databind-exc-invaliddefini)
改为高版本的，如2.9.9

2. *org.apache.jasper.JasperException: Unable to convert string "${st.startTime}" to class "java.util.Date" for attribute "value": Property Editor not registered with the PropertyEditorManager*
实际代码没错。因为web.xml的<webapp>使用遗弃的命名空间问题。改为[解决方法](http://ykushch.net/jstl-fmtformatdate/)
```xml
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://java.sun.com/xml/ns/javaee"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
        http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
        id="WebApp_ID"
        version="2.5">
</web-app>
```
## 相关参考
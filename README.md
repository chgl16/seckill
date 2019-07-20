# 秒杀业务模块-Seckill

## 开发环境
![Java](https://img.shields.io/badge/Java-1.8-green.svg) 
![Maven](https://img.shields.io/badge/Maven-3.5-orange.svg)
![Spring](https://img.shields.io/badge/Spring-5.1-green.svg)
![Spring Security](https://img.shields.io/badge/SpringMVC-5.1-color.svg)
![MyBatis](https://img.shields.io/badge/MyBatis-3.5-red.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![jQuery](https://img.shields.io/badge/jQuery-3.3-brown.svg)
![bootstrap](https://img.shields.io/badge/Bootstrap-3.3-purple.svg)

## 效果预览
发布地址:  [https:]
![前端页面流程](https://i.loli.net/2019/07/20/5d33174b8598e44118.png)
![详情页面流程](https://i.loli.net/2019/07/20/5d33174b92e6928405.png)
![秒杀列表](https://i.loli.net/2019/07/20/5d33174b9b1a857153.png)
![用户标识存cookie](https://i.loli.net/2019/07/20/5d33174b4524e59471.png)
![秒杀等待](https://i.loli.net/2019/07/20/5d3318424464658160.png)
![暴露秒杀接口](https://i.loli.net/2019/07/20/5d33174b57e6d99135.png)
![秒杀成功](https://i.loli.net/2019/07/20/5d33174b6eefc24457.png)
![重复秒杀](https://i.loli.net/2019/07/20/5d33174b7874972399.png)


## 核心实现

## 问题故障
#### 1. *Java.lang.NoClassDefFoundError: com/fasterxml/jackson/databind/exc/InvalidDefinitionException*
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

#### 2. *org.apache.jasper.JasperException: Unable to convert string "${st.startTime}" to class "java.util.Date" for attribute "value": Property Editor not registered with the PropertyEditorManager*
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
#### 3. 静态资源拦截问题  
项目中在spring-web.xml为静态资源做了默认不拦截配置  
```xml
<mvc:default-servlet-handler/>
```
但是静态资源还是不要放在WEB-INF目录下，这个是默认安全目录，资源访问会被限制。
可以放到上层webapp目录下

#### 4. slf4j整合logback，记得把scope从test删去
```xml
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.25</version>
    </dependency>
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>1.2.3</version>
      <scope>test</scope>
    </dependency>
    <!-- 实现slf4j接口并且整合 -->
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-core</artifactId>
      <version>1.2.3</version>
    </dependency>
```
>总是忽略了这个，依赖都在了就是没有发现为啥绑定失败。
## 相关参考
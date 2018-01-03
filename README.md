# email-starter
spring boot 邮件快速配置

---

## 1. 下载email-service

```shell
    git clone https://github.com/yangyangxinxin/email-service.git
```
    
## 2. 安装
    
```shell
    mvn clean install -Dmaven.test.skip=true
```

## 3. 依赖配置：

```xml
    <dependency>
        <groupId>com.luckysweetheart</groupId>
        <artifactId>email-starter</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
```

## 4. application.properties :

```properties

    # 是否开启debug
    spring.email.config.debug=false
    
    # 模板文件放在 resources/templates 目录下
    spring.email.config.freemarker-path=classpath:/templates/
    spring.email.config.username=your username
    spring.email.config.password=your password
    spring.email.config.host=your host
    spring.email.config.port=your port
    spring.email.config.protocol=your protocol
```

## 5. 开始

1. 模板代码：(test.ftl)

```injectedfreemarker
    <h1>${a!}</h1>
```

2. Java 代码：
```java

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class ApplicationTests {
    
        @Resource
        private EmailSender emailSender;
    
        @Test
        public void contextLoads() {
            Map<String,Object> map = new HashMap<>();
            map.put("a","hello world!");
            emailSender.send(EmailTemplateMessage.create().template(new FreemarkerTemplate("test.ftl",map)).to("981987024@qq.com").subject("spring-boot 集成测试"));
        }
    }

```




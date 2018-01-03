package com.luckysweetheart.emailstarter;

import com.luckysweetheart.email.client.EmailClient;
import com.luckysweetheart.email.parser.executor.FreemarkerTemplateParser;
import com.luckysweetheart.email.sender.EmailSender;
import com.luckysweetheart.email.sender.EmailSenderExecutor;
import com.luckysweetheart.email.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by yangxin on 2018/1/2.
 */
@Configuration
@ConditionalOnClass(EmailClient.class) // 只有在ClassPath中存在OSSClient的时候会加载本类下的bean
@EnableConfigurationProperties(EmailProperties.class) // 配置类
public class EmailAutoconfigure {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EmailProperties emailProperties;

    @Bean
    @ConditionalOnMissingBean(EmailClient.class)
    public EmailClient emailClient() {
        return new EmailClient(emailProperties.getUsername(), emailProperties.getPassword(), emailProperties.getHost(), emailProperties.getPort(), emailProperties.getProtocol(), emailProperties.isDebug());
    }

    @Bean
    @ConditionalOnMissingBean(EmailSender.class)
    public EmailSender emailSender(EmailClient emailClient) {
        EmailSenderExecutor executor = new EmailSenderExecutor();
        executor.setEmailClient(emailClient);
        return executor;
    }

    @Bean
    @ConditionalOnMissingBean(FreeMarkerConfigurer.class)
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath(emailProperties.getFreemarkerPath());
        Properties properties = new Properties();
        properties.setProperty("template_update_delay", "1000");
        properties.setProperty("default_encoding", "UTF-8");
        properties.setProperty("locale", "zh_CN");
        configurer.setFreemarkerSettings(properties);
        return configurer;
    }

    @Bean
    @ConditionalOnMissingBean(SpringUtil.class)
    public SpringUtil springUtil() {
        return new SpringUtil();
    }

    @Bean
    @ConditionalOnMissingBean(FreemarkerTemplateParser.class)
    public FreemarkerTemplateParser freemarkerTemplateParser(FreeMarkerConfigurer freeMarkerConfigurer) {
        FreemarkerTemplateParser parser = new FreemarkerTemplateParser();
        parser.setFreeMarkerConfigurer(freeMarkerConfigurer);
        return parser;
    }
}

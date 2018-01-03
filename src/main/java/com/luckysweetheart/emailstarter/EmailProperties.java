package com.luckysweetheart.emailstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yangxin on 2018/1/2.
 */
@ConfigurationProperties(prefix = "spring.email.config")
public class EmailProperties {

    /**
     * 登录用户名,发件人
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 服务器地址
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 是否测试环境
     */
    private boolean debug;

    private String freemarkerPath;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getFreemarkerPath() {
        return freemarkerPath;
    }

    public void setFreemarkerPath(String freemarkerPath) {
        this.freemarkerPath = freemarkerPath;
    }
}

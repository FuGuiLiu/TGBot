package com.sky.idea.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @packageName:com.sky.idea
 * @ClassName:Bot
 * @Description:机器人对象
 * @Author:
 * @Data:2021/9/14@Time:14:02
 */
@Component
@ConfigurationProperties(prefix = "bot")
public class Ryder37Bot implements Serializable {

	private static final long serialVersionUID = -2884863407845298434L;

	@Value("token")
	private String token;
	@Value("socksProxyHost")
	private String socksProxyHost;
	@Value("socksProxyPort")
	private String socksProxyPort;

	public Ryder37Bot() {

	}

	public Ryder37Bot(String token, String socksProxyHost, String socksProxyPort) {
		this.token = token;
		this.socksProxyHost = socksProxyHost;
		this.socksProxyPort = socksProxyPort;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSocksProxyHost() {
		return socksProxyHost;
	}

	public void setSocksProxyHost(String socksProxyHost) {
		this.socksProxyHost = socksProxyHost;
	}

	public String getSocksProxyPort() {
		return socksProxyPort;
	}

	public void setSocksProxyPort(String socksProxyPort) {
		this.socksProxyPort = socksProxyPort;
	}
}


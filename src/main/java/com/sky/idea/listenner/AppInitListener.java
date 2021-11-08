package com.sky.idea.listenner;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.impl.UpdatesHandler;
import com.pengrad.telegrambot.model.Update;
import com.sky.idea.bot.RequestHandler;
import com.sky.idea.entity.Ryder37Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Administrator
 * @Auther: JinXu
 * @Date: 2020/10/20/17:02
 */
@Service
public class AppInitListener implements ApplicationListener<ApplicationReadyEvent> {
	public static final Logger logger = LoggerFactory.getLogger(AppInitListener.class);
	@Resource
	private Ryder37Bot ryder37Bot;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// 设置代理
		Properties prop = System.getProperties();
		prop.put("proxySet", true);

		prop.setProperty("socksProxyHost", ryder37Bot.getSocksProxyHost());
		prop.setProperty("socksProxyPort", ryder37Bot.getSocksProxyPort());

		logger.error("配置Socket代理,代理Ip:" + ryder37Bot.getSocksProxyHost() + "\t" + "代理端口:" + ryder37Bot.getSocksProxyPort());

		try {
			new URL("https://www.youtube.com/").openStream();
			logger.error("链接链接成功--->");

			// Create your bot passing the token received from @BotFather
			String token = ryder37Bot.getToken();

			TelegramBot telegramBot = new TelegramBot(token);

			// 消息接收监听
			telegramBot.setUpdatesListener(updates -> {
				for (Update update : updates) {
					RequestHandler.forwarder(telegramBot, update);
				}
				return UpdatesListener.CONFIRMED_UPDATES_ALL;
			});
		} catch (IOException e) {
			logger.error("链接链接,发生异常--->" + e.getMessage());
		}
	}
}

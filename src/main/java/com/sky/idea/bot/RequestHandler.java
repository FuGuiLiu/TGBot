package com.sky.idea.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

import java.io.Serializable;

/**
 * @packageName:com.sky.idea.bot
 * @ClassName:RequestController
 * @Description: 请求转发器
 * @Author: lfg
 * @Data:2021/9/14@Time:11:09
 */
public class RequestHandler implements Serializable {

	private static final String regex = "(^/)";

	private static final long serialVersionUID = 6701573189222515388L;

	public static void forwarder(TelegramBot telegramBot, Update update) {
		// 以 / 开头的命令格式
		if (regex.matches(update.message().text())) {
			MessageController.commandMessageHandler(telegramBot, update);
		} else {
			MessageController.otherMessageHandler(telegramBot, update);
		}
	}

}

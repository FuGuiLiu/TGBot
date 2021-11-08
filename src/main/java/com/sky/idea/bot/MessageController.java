package com.sky.idea.bot;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;

/**
 * @packageName:com.sky.idea.bot
 * @ClassName:MessageSender
 * @Description: 消息处理器
 * @Author: lfg
 * @Data:2021/9/14@Time:10:44
 */
public class MessageController implements Serializable {
	public static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	private static final long serialVersionUID = -3003968668628721895L;


	public MessageController() {

	}

	/**
	 * 命令消息处理
	 *
	 * @param update
	 * @return
	 */
	public static void commandMessageHandler(TelegramBot telegramBot, Update update) {
		SendMessage request = new SendMessage(update.message().chat().id(), "text")
				.parseMode(ParseMode.HTML)
				.disableWebPagePreview(false)
				.disableNotification(true)
				.replyToMessageId(3);

		// async

		telegramBot.execute(request, new Callback<SendMessage, SendResponse>() {
			@Override
			public void onResponse(SendMessage request, SendResponse response) {

			}

			@Override
			public void onFailure(SendMessage request, IOException e) {

			}
		});
	}

	/**
	 * 非命令消息处理
	 *
	 * @param update
	 */
	public static void otherMessageHandler(TelegramBot telegramBot, Update update) {
		if ("/keyboardmarkup".equals(update.message().text())) {
			String[][] strings = {{"白", "嫖"}, {"嫖", "白"}};

			ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(strings, true, true, false);

			SendMessage message = new SendMessage(update.message().chat().id(), "123").replyMarkup(replyKeyboardMarkup).disableWebPagePreview(false).disableNotification(true);
			telegramBot.execute(message, new Callback<SendMessage, SendResponse>() {

				@Override
				public void onResponse(SendMessage request, SendResponse response) {
					logger.error("加载成功--->" + response.description());
				}

				@Override
				public void onFailure(SendMessage request, IOException e) {
					logger.error("加载失败--->" + e.getMessage());
				}
			});
		} else {
			SendMessage request = new SendMessage(update.message().chat().id(), "123")
					.disableWebPagePreview(false)
					.disableNotification(true);

			telegramBot.execute(request, new Callback<SendMessage, SendResponse>() {
				@Override
				public void onResponse(SendMessage request, SendResponse response) {
					logger.error("加载成功--->" + response.description());
				}

				@Override
				public void onFailure(SendMessage request, IOException e) {
					logger.error("加载失败--->" + e.getMessage());
				}
			});
		}
	}
}

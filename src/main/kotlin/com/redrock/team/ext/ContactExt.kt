package com.redrock.team.ext

import com.redrock.team.config.TARGET_QQ_GROUP_NUMBER
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.isBotMuted
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import java.io.File

/**
 *@author ZhiQiang Tu
 *@time 2021/10/3  20:45
 *@signature 我将追寻并获取我想要的答案
 */
suspend fun Contact.toImage(path: String): Image {
    File(path).toExternalResource().use {
        return uploadImage(it)
    }
}

/**
 * 发送消息前判断是否是目标群聊，否者直接取消。
 */
suspend fun Group.safeSend(message: Message) {
    if (id in TARGET_QQ_GROUP_NUMBER &&
        //是否被禁言
        !isBotMuted &&
        //发送消息是否被限制
        !isLimitSend
    ) {
        sendMessage(message)
        bot.mute()
    }
}

suspend fun Group.safeSend(message: String) {
    if (
        id in TARGET_QQ_GROUP_NUMBER &&
        //是否被禁言
        !isBotMuted &&
        //发送消息是否被限制
        !isLimitSend
    ) {
        sendMessage(message)
        bot.mute()
    }
}
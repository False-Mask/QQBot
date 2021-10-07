package com.redrock.team.ext

import com.redrock.team.config.DEFAULT_BOT_MUTE_TIME
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.EventChannel
import net.mamoe.mirai.event.events.BotEvent

/**
 *@author ZhiQiang Tu
 *@time 2021/10/3  21:08
 *@signature 我将追寻并获取我想要的答案
 */
var isLimitSend = false
fun Bot.safe(): EventChannel<BotEvent> {
    return eventChannel.exceptionHandler {
        it.printStackTrace()
    }
}

fun Bot.mute(time: Long = DEFAULT_BOT_MUTE_TIME) {
    GlobalScope.launch {
        isLimitSend = true
        delay(time)
        isLimitSend = false
    }
}
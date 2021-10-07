package com.redrock.team

import com.redrock.team.event.*
import com.redrock.team.ext.safe
import com.redrock.team.utils.getBot
import net.mamoe.mirai.event.*
import net.mamoe.mirai.event.events.MemberJoinEvent
import net.mamoe.mirai.event.events.NudgeEvent
import net.mamoe.mirai.message.nextMessageAsync

/**
 *@author ZhiQiang Tu
 *@time 2021/9/16  11:52
 *@signature 我将追寻并获取我想要的答案
 */

suspend fun main2() {
    //初始化bot
    val bot = getBot()
    //登陆
    bot.login()

    bot.safe().subscribeAlways<NudgeEvent> {
        botNudge()
    }

    //加群通知
    bot.safe().subscribeAlways<MemberJoinEvent> {
        botMemberJoin()
    }

    bot.safe().subscribeGroupMessages {
        groupMessageDslBuild(this)
    }

}






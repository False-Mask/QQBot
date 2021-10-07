package com.redrock.team.event

import com.redrock.team.ext.mute
import net.mamoe.mirai.event.events.NudgeEvent

/**
 *@author ZhiQiang Tu
 *@time 2021/10/6  17:53
 *@signature 我将追寻并获取我想要的答案
 */
suspend fun NudgeEvent.botNudge(){
    if (target == bot) {
        from.nudge().sendTo(subject)
        bot.mute()
    }
}
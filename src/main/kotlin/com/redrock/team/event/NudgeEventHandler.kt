package com.redrock.team.event

import com.redrock.team.ext.mute
import net.mamoe.mirai.event.events.NudgeEvent

/**
 *@author ZhiQiang Tu
 *@time 2021/10/6  17:53
 *@signature 我将追寻并获取我想要的答案
 */
suspend fun NudgeEvent.botNudge() {
    if (
        target == bot
        && from.id != 1749322259L
    ) {
        from.nudge().sendTo(subject)
        bot.mute()
    }
    if (
        target.id == 2623036785L
        && from.id != 1749322259L
        && from.id != 2623036785L
    ) {
        from.nudge().sendTo(subject)
    }
}
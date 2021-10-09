package script

import com.redrock.team.config.DEFAULT_DEVICE_JSON_PATH
import com.redrock.team.config.WELCOME_PATH
import com.redrock.team.event.botNudge
import com.redrock.team.ext.safe
import com.redrock.team.ext.toImage
import com.redrock.team.utils.getBot
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.event.events.NudgeEvent
import net.mamoe.mirai.utils.BotConfiguration

/**
 *@author ZhiQiang Tu
 *@time 2021/10/8  21:01
 *@signature 我将追寻并获取我想要的答案
 */
suspend fun main() {
    val mBot = getBot(
        loginDevice = BotConfiguration.MiraiProtocol.ANDROID_WATCH,
        deviceJsonPath = DEFAULT_DEVICE_JSON_PATH
    )
    mBot.login()
    val group = mBot.getGroup(961577258L)
    group?.apply {
        sendMessage(toImage(WELCOME_PATH))
    }

    mBot.safe().subscribeAlways<NudgeEvent> {
        botNudge()
    }


}
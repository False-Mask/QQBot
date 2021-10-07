package script

import com.redrock.team.ext.safe
import com.redrock.team.ext.safeSend
import com.redrock.team.utils.getBot
import net.mamoe.mirai.contact.MemberPermission
import net.mamoe.mirai.event.events.BotOnlineEvent
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.utils.BotConfiguration
import java.io.File

/**
 *@author ZhiQiang Tu
 *@time 2021/10/5  14:16
 *@signature 我将追寻并获取我想要的答案
 */

suspend fun main() {
    val mBot = getBot(
        loginDevice = BotConfiguration.MiraiProtocol.ANDROID_PAD
    )
    mBot.login()
    val group = mBot.getGroup(719363584L)

    File("./unMatch.txt").bufferedWriter().use { writer ->
        group?.members?.forEach {
            if (
                it.permission == MemberPermission.MEMBER &&
                !it.nameCard.matches(Regex("(2[01]){1}-(Android|iOS){1}-.[^- \\s]+"))) {
                writer.write(it.nameCard + "\t:" + it.id + "\n")
            }
        }
    }


}

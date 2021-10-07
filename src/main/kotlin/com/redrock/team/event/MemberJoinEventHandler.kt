package com.redrock.team.event

import com.redrock.team.config.WELCOME_PATH
import com.redrock.team.ext.safeSend
import com.redrock.team.ext.toImage
import net.mamoe.mirai.event.events.MemberJoinEvent
import net.mamoe.mirai.message.data.At

/**
 *@author ZhiQiang Tu
 *@time 2021/10/6  17:50
 *@signature 我将追寻并获取我想要的答案
 */
suspend fun MemberJoinEvent.botMemberJoin() {
    val content = """
        1.进群之后，请先修改群昵称，如:21-Android-弓长火日立 , 21-iOS-XX , (年级-方向-姓名)

        2.仔细阅读群文件相关，可以开始自学

        3.善用 百度 必应 谷歌 CSDN 掘金 简书 等，自己解决问题才能体验解决问题的过程

        4.提问前请务必阅读(电脑内容不要拍照，请截图，快捷键ctrl+alt+a)  ->https://zhuanlan.zhihu.com/p/19779979
    """.trimIndent()


    val event = At(this.user) + "欢迎加入移动开发部！\n" + group.toImage(WELCOME_PATH) + "以下为本群须知:\n" +
            content
    group.safeSend(event)
}
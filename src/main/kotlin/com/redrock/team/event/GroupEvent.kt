package com.redrock.team.event

import com.redrock.team.config.*
import com.redrock.team.ext.safeSend
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.contact.MemberPermission
import net.mamoe.mirai.event.GroupMessageSubscribersBuilder
import net.mamoe.mirai.event.MessageDsl
import net.mamoe.mirai.event.MessageSubscribersBuilder
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.event.syncFromEvent
import net.mamoe.mirai.message.data.*
import java.lang.Exception

/**
 *@author ZhiQiang Tu
 *@time 2021/10/6  17:42
 *@signature 我将追寻并获取我想要的答案
 */
private val reReadList: MutableList<Member> = mutableListOf()
private var lastMessage: MessageChain? = null
private var job: Job? = null


fun groupMessageDslBuild(messageSubscribersBuilder: GroupMessageSubscribersBuilder) {
    messageSubscribersBuilder.apply {
        atBot { botAt() }
        always {
            botReReadMute()
            botFixReply()
        }
    }
}

fun GroupMessageEvent.botReReadMute() {
    if (group.id != MUTE_GROUP) return

    if (message.serializeToMiraiCode() == lastMessage?.serializeToMiraiCode()) {
        reReadList.add(sender)
    } else {
        reReadList.clear()
    }

    if (reReadList.size >= BEGIN_MUTE_COUNT) {
        job?.cancel()
        job = GlobalScope.launch {
            delay(BOT_MUTE_WAITE_TIME)
            val filterList = reReadList.filter {
                it.id != BOT_QQ && it.id !in REREAD_WHITE_LIST
            }
            val mutePeople = filterList.random()
            val muteTime = when (mutePeople.permission) {
                MemberPermission.ADMINISTRATOR -> {
                    114514
                }
                MemberPermission.OWNER -> {
                    Int.MAX_VALUE
                }
                else -> {
                    (BOT_REREAD_FROM..BOT_REREAD_T).random()
                }
            }

            try {
                mutePeople.mute(muteTime)
            } catch (e: Exception) {

            }
            group.safeSend(
                "恭喜".toPlainText()
                    .followedBy(At(mutePeople.id))
                    .followedBy(("抽中禁言大礼包" + muteTime + "秒").toPlainText())
            )
            reReadList.clear()
        }
    }
    lastMessage = message
}


suspend fun GroupMessageEvent.botAt() {
    //分类型处理一下群聊信息
    message.forEach {
        when (it) {
            //如果在该群聊中被At了
            At(BOT_QQ) -> {
                group.safeSend("艾特我干嘛(超凶")
            }
        }
    }
}

suspend fun GroupMessageEvent.botFixReply() {
    fixedMatches.forEach {
        if (it.key == message.content) {
            group.safeSend(it.value)
        }
    }
}
package com.redrock.team.config

import net.mamoe.mirai.message.data.Face
import net.mamoe.mirai.message.data.buildMessageChain

/**
 *@author ZhiQiang Tu
 *@time 2021/10/8  22:39
 *@signature 我将追寻并获取我想要的答案
 */
val botAtMessage = mutableListOf(
    buildMessageChain {
        add("我这锭子紧了又松,想了半天也找不到不打你的理由")
        add(Face(Face.YOU_HENG_HENG))
        add(Face(Face.ZUO_HENG_HENG))
    },
    buildMessageChain {
        add("别艾特我!!我不知道,我不明白,我不了解,我不懂,我只是一个摸鱼的")
        add(Face(Face.MIAO_MIAO))
    },
    buildMessageChain {
        add("干嘛 ")
        add(Face(Face.BAO_JIN))
        add(Face(Face.BAO_JIN))
    },
    buildMessageChain {
        add("哥咱停一下行不")
        add(Face(Face.WEI_QU))
    },
    buildMessageChain {
        add("＞﹏＜憋艾特了")
    },
    buildMessageChain {
        add("蚌埠住了")
    }
)
const val AT_PIC_DIRECTORY = "./resources/at"
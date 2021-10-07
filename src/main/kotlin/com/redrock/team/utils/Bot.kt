package com.redrock.team.utils

import com.redrock.team.config.*
import net.mamoe.mirai.Bot
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.utils.BotConfiguration

/**
 *@author ZhiQiang Tu
 *@time 2021/9/16  11:56
 *@signature 我将追寻并获取我想要的答案
 */

fun getBot(
    qqNumber: Long = BOT_QQ,
    pwd: String = BOT_PASSWORD,
    deviceJsonPath: String = DEFAULT_DEVICE_JSON_PATH,
    loginDevice: BotConfiguration.MiraiProtocol = LOGIN_DEVICE,
    beatStrategy: BotConfiguration.HeartbeatStrategy = HEART_BEAT_STRATEGY,
    enableCache: Boolean = DEFAULT_ENABLE_CONTACT_CACHE
): Bot = BotFactory.newBot(qqNumber, pwd) {
    //device.json路径
    fileBasedDeviceInfo(deviceJsonPath)
    //登陆设备
    protocol = loginDevice
    //心跳策略
    heartbeatStrategy = beatStrategy
    //缓存
    if (enableCache) {
        enableContactCache()
    }
}
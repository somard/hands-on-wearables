package com.example.pocketbrief.wear

import com.example.pocketbrief.shared.WristBrief

// Book: Chapter 9, p.100. All ambient surfaces consume the same cached WristBrief.
fun tileText(brief: WristBrief): String =
    if (brief.isSensitiveHidden) "Open PocketBrief" else brief.statusText

fun complicationText(brief: WristBrief): String =
    if (brief.isSensitiveHidden) "--" else brief.alertCount.toString()

fun notificationText(brief: WristBrief): String =
    if (brief.isSensitiveHidden) "Open PocketBrief for details" else "${brief.alertCount} alert(s)"

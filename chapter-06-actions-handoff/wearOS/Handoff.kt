package com.example.handsonwearables.chapter06

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.wear.remote.interactions.RemoteActivityHelper
import java.util.concurrent.Executor

// Book: Chapter 6, p.68.
fun openDetailsOnPhone(
    context: Context,
    executor: Executor,
    nodeId: String?
) {
    val helper = RemoteActivityHelper(context, executor)
    val intent = Intent(Intent.ACTION_VIEW)
        .setData(Uri.parse("https://example.com/items/42"))
        .addCategory(Intent.CATEGORY_BROWSABLE)

    helper.startRemoteActivity(intent, nodeId)
}

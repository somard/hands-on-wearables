package com.example.handsonwearables.chapter05

import android.content.Context
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.NodeClient
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable

// Book: Chapter 5, pp.56, 58, 60.
class CompanionSyncRepository(private val context: Context) : DataClient.OnDataChangedListener {
    val dataClient = Wearable.getDataClient(context)
    val messageClient = Wearable.getMessageClient(context)
    val nodeClient = Wearable.getNodeClient(context)

    fun publishStatus(status: String) {
        val request = PutDataMapRequest.create("/today/status").apply {
            dataMap.putString("value", status)
            dataMap.putLong("updatedAt", System.currentTimeMillis())
        }.asPutDataRequest().setUrgent()

        Wearable.getDataClient(context).putDataItem(request)
    }

    fun requestRefresh(nodeId: String) {
        messageClient.sendMessage(
            nodeId,
            "/today/refresh",
            byteArrayOf()
        )
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        for (event in dataEvents) {
            if (event.dataItem.uri.path == "/today/status") {
                // Decode DataMap here and publish the latest status to UI state.
            }
        }
    }
}

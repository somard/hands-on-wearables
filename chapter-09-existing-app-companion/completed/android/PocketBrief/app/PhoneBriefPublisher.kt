package com.example.pocketbrief

import android.content.Context
import com.example.pocketbrief.shared.WristBrief
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable

class PhoneBriefPublisher(private val context: Context) {
    fun publish(brief: WristBrief) {
        val request = PutDataMapRequest.create("/pocketbrief/current").apply {
            dataMap.putString("displayName", brief.displayName)
            dataMap.putString("statusText", brief.statusText)
            brief.nextAppointmentEpochMs?.let { dataMap.putLong("nextAppointmentEpochMs", it) }
            dataMap.putInt("alertCount", brief.alertCount)
            dataMap.putLong("updatedAtEpochMs", brief.updatedAtEpochMs)
            dataMap.putBoolean("isSensitiveHidden", brief.isSensitiveHidden)
        }.asPutDataRequest().setUrgent()

        Wearable.getDataClient(context).putDataItem(request)
    }

    fun revoke() {
        publish(
            WristBrief(
                displayName = "",
                statusText = "Sign in on phone",
                nextAppointmentEpochMs = null,
                alertCount = 0,
                updatedAtEpochMs = System.currentTimeMillis(),
                isSensitiveHidden = true
            )
        )
    }
}

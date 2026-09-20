package com.example.handsonwearables.chapter07

import androidx.wear.watchface.complications.data.ComplicationData
import androidx.wear.watchface.complications.data.PlainComplicationText
import androidx.wear.watchface.complications.data.ShortTextComplicationData
import androidx.wear.watchface.complications.datasource.ComplicationRequest
import androidx.wear.watchface.complications.datasource.SuspendingComplicationDataSourceService

// Book: Chapter 7, p.80.
class NextEventDataSource :
    SuspendingComplicationDataSourceService() {
    override suspend fun onComplicationRequest(
        request: ComplicationRequest
    ): ComplicationData {
        return ShortTextComplicationData.Builder(
            text = PlainComplicationText.Builder("10:30").build(),
            contentDescription = PlainComplicationText.Builder("Next event").build()
        ).build()
    }

    override fun getPreviewData(type: androidx.wear.watchface.complications.data.ComplicationType): ComplicationData? = null
}

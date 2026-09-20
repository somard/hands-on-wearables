package com.example.handsonwearables.chapter07

import androidx.wear.protolayout.ResourceBuilders.Resources
import androidx.wear.protolayout.ResourceBuilders.ResourcesRequest
import androidx.wear.protolayout.TimelineBuilders.Timeline
import androidx.wear.protolayout.material3.layoutString
import androidx.wear.protolayout.material3.materialScope
import androidx.wear.protolayout.material3.primaryLayout
import androidx.wear.protolayout.material3.text
import androidx.wear.tiles.RequestBuilders
import androidx.wear.tiles.TileBuilders.Tile
import androidx.wear.tiles.TileService
import com.google.common.util.concurrent.Futures

// Book: Chapter 7, p.76. This file deliberately keeps the printed Material 3
// ProtoLayout shape rather than replacing it with an older low-level layout.
private const val RESOURCES_VERSION = "1"

class NextEventTileService : TileService() {
    override fun onTileRequest(requestParams: RequestBuilders.TileRequest) =
        Futures.immediateFuture(
            Tile.Builder()
                .setResourcesVersion(RESOURCES_VERSION)
                .setTileTimeline(
                    Timeline.fromLayoutElement(
                        materialScope(this, requestParams.deviceConfiguration) {
                            primaryLayout(
                                mainSlot = {
                                    text("10:30 Design review".layoutString)
                                }
                            )
                        }
                    )
                )
                .build()
        )

    override fun onTileResourcesRequest(requestParams: ResourcesRequest) =
        Futures.immediateFuture(
            Resources.Builder().setVersion(RESOURCES_VERSION).build()
        )
}

package com.example.pocketbrief.wear

import com.example.pocketbrief.shared.WristBrief

// Minimal last-safe-cache holder used by the app/Tile/complication adapters.
class WristBriefRepository {
    private var cached: WristBrief? = null

    fun update(brief: WristBrief) { cached = brief }
    fun current(): WristBrief? = cached
    fun clearForRevocation(now: Long) {
        cached = WristBrief("", "Sign in on phone", null, 0, now, true)
    }
}

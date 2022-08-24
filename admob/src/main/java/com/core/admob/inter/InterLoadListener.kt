package com.core.admob.inter

interface InterLoadListener {
    fun onAdLoaded(interManager: InterManager)
    fun onAdLoadFail(message: String)
}
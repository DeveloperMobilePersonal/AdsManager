package com.core.admob.inter

interface InterManagerListener {
    fun onAdInterLoaded(interManager: InterManager){}
    fun onAdInterLoadFail(message: String){}
    fun onAdInterClose(failToShowAd:Boolean){}
    fun onAdInterFailShow(){}
    fun onAdInterShow(){}
}
package com.core.admob.appopen

import com.google.android.gms.ads.appopen.AppOpenAd

interface AppOpenManagerListener {
    fun onAdAppOpenLoaded(appOpenManagerListener: AppOpenManager) {}
    fun onAdAppOpenLoadFail(message: String) {}
    fun onAdShowAppOpen() {}
    fun onAdFailToShowAppOpen() {}
    fun onAdCloseAppOpen() {}
}
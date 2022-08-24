package com.core.admob.appopen

interface AdAppOpenApplicationListener {
    fun onAdStartAppOpen(appOpenManager: AppOpenManager)
    fun onAdShowAppOpen()
    fun onAdCloseAppOpen()
}
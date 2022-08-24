package com.core.admob.appopen

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner

class AdAppOpenApplication(private val appOpenManager: AppOpenManager, private val adId: String) :
    LifecycleObserver, AppOpenManagerListener {

    private var listener: AdAppOpenApplicationListener? = null

    fun addListener(listener: AdAppOpenApplicationListener) {
        this.listener = listener
    }

    fun registerLifecycleOwner() {
        appOpenManager.setAutoReLoadAd(true)
        appOpenManager.setListener(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleEventObserver)
    }

    fun unregisterLifecycleOwner() {
        ProcessLifecycleOwner.get().lifecycle.removeObserver(lifecycleEventObserver)
    }

    private val lifecycleEventObserver = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_START) {
            if (appOpenManager.isLoadAd() && appOpenManager.isReadyAd()) {
                if (!appOpenManager.isShowing() && !LockAd.lockAd) {
                    listener?.onAdStartAppOpen(appOpenManager)
                }
            } else {
                appOpenManager.loadAd()
            }
        }
    }

    override fun onAdShowAppOpen() {
        listener?.onAdShowAppOpen()
    }

    override fun onAdCloseAppOpen() {
        listener?.onAdCloseAppOpen()
    }

    override fun onAdFailToShowAppOpen() {
        listener?.onAdCloseAppOpen()
    }
}
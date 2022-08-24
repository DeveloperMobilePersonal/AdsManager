package com.core.admob.inter

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

/**
<!-- Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713 -->
<meta-data
android:name="com.google.android.gms.ads.APPLICATION_ID"
android:value="ca-app-pub-xxxxxxxxxxxxxxxx~yyyyyyyyyy"/>
 * */
class InterManager(private val context: Context, private val adId: String) {

    companion object {
        const val DEBUG_ID_INTERSTITIAL_AD = "ca-app-pub-3940256099942544/1033173712"
    }

    private var mInterstitialAd: InterstitialAd? = null
    private var interLoadListener: InterLoadListener? = null
    private var timeAdLoaded = 0L
    private var isShowing = false
    private var isError = false
    private var timeOutAd = 2 * 60 * 60 * 1000
    private var isAutoLoadAd = false
    private var startLoadAd = false
    private var debug = false

    fun setDebugAd(debug: Boolean) {
        this.debug = debug
    }

    fun setTimeOutAd(hours: Int) {
        timeOutAd = hours * 60 * 60 * 1000
    }

    fun setAutoLoadAd(autoLoadAd: Boolean) {
        isAutoLoadAd = autoLoadAd
    }

    fun setLoadListenerAd(listener: InterLoadListener?) {
        this.interLoadListener = listener
    }

    fun isLoadAd(): Boolean {
        return mInterstitialAd != null
    }

    fun isReadyAd(): Boolean {
        return if (timeAdLoaded == 0L) false else (System.currentTimeMillis() - timeAdLoaded) < timeOutAd
    }

    fun isError(): Boolean {
        return isError
    }

    fun loadAd() {
        if (startLoadAd) return
        if (isLoadAd() && isReadyAd()) return
        clear()
        startLoadAd = true
        InterstitialAd.load(
            context,
            if (debug) DEBUG_ID_INTERSTITIAL_AD else adId,
            AdRequest.Builder().build(), callback
        )
    }

    fun showAd(activity: Activity, listener: InterShowListener? = null) {
        if (isShowing) return
        if (isError()) {
            listener?.onAdClose(true)
            return
        }
        Log.i("TinhNv", "showAd: ${isLoadAd()} --- ${isReadyAd()}")
        if (isLoadAd() && isReadyAd()) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    clear()
                    if (isAutoLoadAd) loadAd()
                    listener?.onAdClose(false)
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    isShowing = false
                    listener?.onAdFailShow()
                }

                override fun onAdShowedFullScreenContent() {
                    isShowing = true
                    listener?.onAdShow()
                }
            }
            mInterstitialAd?.show(activity)
        } else {
            listener?.onAdClose(true)
        }
    }

    fun clear() {
        mInterstitialAd = null
        timeAdLoaded = 0L
        isShowing = false
        startLoadAd = false
        isError = false
    }

    private val callback = object : InterstitialAdLoadCallback() {
        override fun onAdLoaded(ad: InterstitialAd) {
            mInterstitialAd = ad
            isShowing = false
            isError = false
            startLoadAd = false
            timeAdLoaded = System.currentTimeMillis()
            interLoadListener?.onAdLoaded(this@InterManager)
        }

        override fun onAdFailedToLoad(p0: LoadAdError) {
            mInterstitialAd = null
            isShowing = false
            isError = true
            startLoadAd = false
            interLoadListener?.onAdLoadFail(p0.message)
        }
    }
}
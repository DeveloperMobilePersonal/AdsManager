package video.photo.instagram.downloader.adsmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.core.admob.inter.InterLoadListener
import com.core.admob.inter.InterManager
import com.core.admob.inter.InterShowListener

class MainActivity : AppCompatActivity(), InterLoadListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val interManager = InterManager(this, "")
        interManager.setDebugAd(true)
        interManager.setLoadListenerAd(this)
        interManager.setAutoLoadAd(true)
        interManager.loadAd()
    }

    override fun onAdLoaded(interManager: InterManager) {
        interManager.showAd(this, object : InterShowListener {
            override fun onAdClose(failToShowAd: Boolean) {

            }

            override fun onAdFailShow() {

            }

            override fun onAdShow() {

            }

        })
    }

    override fun onAdLoadFail(message: String) {

    }
}
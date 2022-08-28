package video.photo.instagram.downloader.adsmanager

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.core.admob.appopen.AdAppOpenApplication
import com.core.admob.appopen.AdAppOpenApplicationListener
import com.core.admob.appopen.AppOpenManager
import com.core.admob.appopen.LockAd

class MainActivity : AppCompatActivity(), AdAppOpenApplicationListener {

    private val progressBar by lazy {
        findViewById<ProgressBar>(R.id.progress)
    }

    private val button by lazy {
        findViewById<AppCompatButton>(R.id.appCompatButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appOpenManager = AppOpenManager(this, "")
        appOpenManager.setDebugAd(true)
        val adAppOpenApplication = AdAppOpenApplication(appOpenManager, "")
        adAppOpenApplication.registerLifecycleOwner()
        adAppOpenApplication.addListener(this)
        button.setOnClickListener {
            LockAd.lockAd = true
        }
    }

    override fun onAdStartAppOpen(appOpenManager: AppOpenManager) {
        appOpenManager.showAd(this)
    }

    override fun onAdShowAppOpen() {

    }

    override fun onAdCloseAppOpen() {

    }

}
package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.gle

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.MainActivity

@AndroidEntryPoint
class GLES3JNIActivity : AppCompatActivity() {
    var mView: GLES3JNIView? = null

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        mView = GLES3JNIView(application)
        setContentView(mView)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this@GLES3JNIActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onPause() {
        super.onPause()
        mView!!.onPause()
    }

    override fun onResume() {
        super.onResume()
        mView!!.onResume()
    }
}
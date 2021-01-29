package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R


@AndroidEntryPoint
class AddActActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_act)
    }
}
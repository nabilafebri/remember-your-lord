package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R;

@AndroidEntryPoint
public class AddActActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_act);
    }
}

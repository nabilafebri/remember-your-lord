package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity.ActListViewModel;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity.AddActActivity;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActListViewModel actListViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAddAct = findViewById(R.id.btn_add_activity);
        btnAddAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActActivity.class));
            }
        });

        actListViewModel.getSelectedActivity().observe(this, item -> {
        });
    }
}
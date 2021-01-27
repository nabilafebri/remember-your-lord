package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.read;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.QuranText;

public class ReadFragment extends Fragment {

    private QuranTextViewModel quranTextViewModel;
    private ReadViewModel readViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        quranTextViewModel = new ViewModelProvider(this).get(QuranTextViewModel.class);
        readViewModel = new ViewModelProvider(this).get(ReadViewModel.class);
        View root = inflater.inflate(R.layout.fragment_read, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);

//        readViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        quranTextViewModel.getQuranText(10).observe(getViewLifecycleOwner(), new Observer<QuranText>() {
            @Override
            public void onChanged(@Nullable QuranText quranText) {
                textView.setText(quranText.getText());
            }
        });

        return root;
    }
}
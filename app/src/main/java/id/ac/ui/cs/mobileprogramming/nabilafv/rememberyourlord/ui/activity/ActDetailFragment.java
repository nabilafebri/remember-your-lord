package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Date;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R;

public class ActDetailFragment extends Fragment {
    private ActListViewModel actListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_act_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actListViewModel = ViewModelProviders.of(this).get(ActListViewModel.class);
        actListViewModel.getSelectedActivity().observe(getViewLifecycleOwner(), activity -> {
            TextView textViewTitle = view.findViewById(R.id.act_detail_title);
            textViewTitle.setText(activity.getTitle());

            TextView textViewDesc = view.findViewById(R.id.act_detail_desc);
            textViewDesc.setText(activity.getDescription());

            TextView textViewDate = view.findViewById(R.id.act_detail_date);
            textViewDate.setText(SimpleDateFormat.getDateTimeInstance().format(
                    new Date(activity.getDate())));
        });
    }
}

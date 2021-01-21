package id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.ui.home.activity;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.R;

public class ActivityDetailFragment extends Fragment {

    private Activity mViewModel;

    public static ActivityDetailFragment newInstance() {
        return new ActivityDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Activity.class);
        // TODO: Use the ViewModel
    }

}
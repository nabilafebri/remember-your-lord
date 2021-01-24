package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.RecyclerViewOnItemDone;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.RecyclerViewOnItemSelected;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.State;

@AndroidEntryPoint
public class ActListFragment extends Fragment implements
        RecyclerViewOnItemDone, RecyclerViewOnItemSelected {

    private ActListViewModel actListViewModel;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_act_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actListViewModel = ViewModelProviders.of(this).get(ActListViewModel.class);

        ActListAdapter adapter = new ActListAdapter(this, this);
        actListViewModel.getAllUndoneActivity().observe(getViewLifecycleOwner(), activities -> {
            if (activities instanceof State.Success) {
                hideLoading();
                if (((State.Success) activities).data != null) {
                    adapter.setActivities((List<Activity>) ((State.Success) activities).data);
                }
            } else if (activities instanceof State.Loading) {
                showLoading();
            } else if (activities instanceof State.Failed) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();
            } else if (activities instanceof State.Initialize) {
                hideLoading();
            };
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_act);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.act_list_loading);
    }

    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemSelected(Activity activity) {
        actListViewModel.selectActivity(activity);
    }

    @Override
    public void onItemDone(Activity activity) {
        actListViewModel.checkActivity(activity);
    }
}

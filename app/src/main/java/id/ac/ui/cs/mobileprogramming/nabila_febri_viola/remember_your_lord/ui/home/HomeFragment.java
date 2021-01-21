package id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.ui.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.R;
import id.ac.ui.cs.mobileprogramming.nabila_febri_viola.remember_your_lord.ui.home.activity.ActivityFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ViewGroup container;
    private LinearLayout linearLayout;
    private View view;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        this.container = container;

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
           view = inflater.inflate(R.layout.fragment_home_landscape, container, false);
        }
        else {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }

        final CardView cardSholat = this.view.findViewById(R.id.card_jadwal_sholat);

        final CardView cardAktivitas = this.view.findViewById(R.id.card_aktivitas_harian);
        cardAktivitas.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, new ActivityFragment(), "activity-list")
                        .addToBackStack(null)
                        .commit();
            }
        });

        final CardView cardKajian = this.view.findViewById(R.id.card_jadwal_kajian);
        final CardView cardCatatan = this.view.findViewById(R.id.card_catatan_kajian);

        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int currentOrientation = getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.v("TAG","Landscape");
            final LayoutInflater inflater = LayoutInflater.from(getActivity());
            final ViewGroup viewGroup = (ViewGroup) getView();
            viewGroup.post(new Runnable() {
                public void run() {
//                    viewGroup.removeAllViews();
//                    tidak bisa diganti, mungkin karena di fragment tidak bisa inflate lagi
                    inflater.inflate(R.layout.fragment_home_landscape, viewGroup, false);
                }
            });
//            view = inflater.inflate(R.layout.fragment_home_landscape, container, false);
        } else if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.v("TAG","Portrait");
            final LayoutInflater inflater = LayoutInflater.from(getActivity());
            final ViewGroup viewGroup = (ViewGroup) getView();
                    viewGroup.post(new Runnable() {
            public void run() {
//                viewGroup.removeAllViews();
                inflater.inflate(R.layout.fragment_home, viewGroup, false);
            }
        });
//            view = inflater.inflate(R.layout.fragment_home, container, false);
        };
    }
}
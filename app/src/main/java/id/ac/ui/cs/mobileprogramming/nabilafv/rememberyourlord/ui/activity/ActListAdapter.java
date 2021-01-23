package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.RecyclerViewOnItemDone;
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.RecyclerViewOnItemSelected;

public class ActListAdapter extends RecyclerView.Adapter<ActListAdapter.ActListViewHolder> {
    private RecyclerViewOnItemSelected onItemSelected;
    private RecyclerViewOnItemDone onItemDone;
    private List<Activity> activities;

    public ActListAdapter(RecyclerViewOnItemSelected onItemSelected,
                          RecyclerViewOnItemDone onItemDone) {
        this.onItemSelected = onItemSelected;
        this.onItemDone = onItemDone;
    }

    @NonNull
    @Override
    public ActListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActListViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_act, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ActListViewHolder holder, int position) {
        holder.bindActivity(activities.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemSelected.onItemSelected(activities.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ActListViewHolder extends RecyclerView.ViewHolder {
        private View view;

        public ActListViewHolder(View view) {
            super(view);
            this.view = view;
        }

        public void bindActivity(Activity activity) {
            TextView textViewTitle = (TextView) view.findViewById(R.id.viewholder_act_title);
            textViewTitle.setText(activity.getTitle());

            TextView textViewDate = (TextView) view.findViewById(R.id.viewholder_act_date);
            textViewDate.setText(SimpleDateFormat.getDateTimeInstance().format(new Date(activity.getDate())));

            CheckBox checkBox = (CheckBox) view.findViewById(R.id.viewholder_act_checkbox);
            checkBox.setChecked(activity.getIsDone());
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    onItemDone.onItemDone(activity);}
            });
        }
    }

    public void setActivities(List<Activity> newActivities) {
        activities = newActivities;
        notifyDataSetChanged();
    }
}


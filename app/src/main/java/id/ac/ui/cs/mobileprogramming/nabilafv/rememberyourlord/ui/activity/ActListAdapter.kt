package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.databinding.ViewholderActBinding
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.RecyclerViewOnItemDone
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.RecyclerViewOnItemSelected
import java.text.SimpleDateFormat
import java.util.*


class ActListAdapter(
    private val onItemSelected: RecyclerViewOnItemSelected,
    private val onItemDone: RecyclerViewOnItemDone
) : RecyclerView.Adapter<ActListAdapter.ActListViewHolder>() {
    private var activities = emptyList<Activity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActListViewHolder {
        val binding = ViewholderActBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ActListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActListViewHolder, position: Int) {
        holder.bindActivity(activities[position])
        holder.itemView.setOnClickListener { onItemSelected.onItemSelected(activities[position]) }
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    inner class ActListViewHolder(private val binding: ViewholderActBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindActivity(activity: Activity) {
            binding.viewholderActTitle.text = activity.title
            binding.viewholderActDate.text = SimpleDateFormat.getDateTimeInstance().format(
                Date(activity.date)
            )
            binding.viewholderActCheckbox.isChecked = activity.isDone
            binding.viewholderActCheckbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    onItemDone.onItemDone(activity)
                }
            }
        }
    }

    fun setActivities(newActivities: List<Activity>) {
        activities = newActivities
        notifyDataSetChanged()
    }
}
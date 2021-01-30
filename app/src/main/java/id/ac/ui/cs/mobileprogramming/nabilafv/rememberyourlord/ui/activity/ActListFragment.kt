package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.databinding.FragmentActListBinding
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.RecyclerViewOnItemDone
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.RecyclerViewOnItemSelected
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils.State


@AndroidEntryPoint
class ActListFragment : Fragment(), RecyclerViewOnItemDone, RecyclerViewOnItemSelected {
    private lateinit var actListViewModel: ActListViewModel
    private var _binding: FragmentActListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actListViewModel = ViewModelProvider(requireActivity()).get(ActListViewModel::class.java)
        val adapter = ActListAdapter(this, this)
        actListViewModel.getAllUndoneActivity().observe(viewLifecycleOwner) { activities ->
            if (activities is State.Success) {
                hideLoading()
                if (activities.data != null) {
                    adapter.setActivities(activities.data as List<Activity>)
                }
            } else if (activities is State.Loading) {
                showLoading()
            } else if (activities is State.Failed) {
                Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
            } else if (activities is State.Initialize) {
                hideLoading()
            }
        }

        binding.recyclerviewAct.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewAct.setHasFixedSize(true)
        binding.recyclerviewAct.adapter = adapter
    }

    fun hideLoading() {
        binding.actListLoading.visibility = View.GONE
    }

    fun showLoading() {
        binding.actListLoading.visibility = View.VISIBLE
    }

    override fun onItemSelected(activity: Activity) {
        actListViewModel.selectActivity(activity)
    }

    override fun onItemDone(activity: Activity) {
        actListViewModel.checkActivity(activity)
    }
}
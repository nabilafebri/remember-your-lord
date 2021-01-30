package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.databinding.FragmentActDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class ActDetailFragment : Fragment() {
    private lateinit var actListViewModel: ActListViewModel
    private var _binding: FragmentActDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actListViewModel = ViewModelProvider(requireActivity()).get(ActListViewModel::class.java)
        actListViewModel.getSelectedActivity().observe(viewLifecycleOwner) { activity ->
            binding.actDetailTitle.text = activity.title
            binding.actDetailDesc.text = activity.description
            binding.actDetailDate.text = SimpleDateFormat.getDateTimeInstance().format(
                Date(activity.date)
            )
        }
    }
}
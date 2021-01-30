package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.quran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.databinding.FragmentQuranBinding

import java.util.*

@AndroidEntryPoint
class QuranFragment : Fragment() {
    private lateinit var quranTextViewModel: QuranTextViewModel
    private lateinit var enTransViewModel: EnTransViewModel
    private lateinit var idTransViewModel: IdTransViewModel
    private var _binding: FragmentQuranBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quranTextViewModel = ViewModelProvider(requireActivity()).get(QuranTextViewModel::class.java)
        enTransViewModel = ViewModelProvider(requireActivity()).get(EnTransViewModel::class.java)
        idTransViewModel = ViewModelProvider(requireActivity()).get(IdTransViewModel::class.java)

        val ayahNum = (0..6235).random()

        quranTextViewModel.getQuranText(ayahNum).observe(viewLifecycleOwner) { quranText ->
            binding.quranText.text = quranText.text
        }

        val lang = Locale.getDefault().getLanguage()
        if (lang == "in") {
            idTransViewModel.getIdTrans(ayahNum).observe(viewLifecycleOwner) { idTrans ->
                binding.quranTrans.text = idTrans.text
            }
        } else {
            enTransViewModel.getEnTrans(ayahNum).observe(viewLifecycleOwner) { enTrans ->
                binding.quranTrans.text = enTrans.text
            }
        }
    }
}
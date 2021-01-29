package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.quran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.R
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.QuranText

@AndroidEntryPoint
class QuranFragment : Fragment() {
    private lateinit var quranTextViewModel: QuranTextViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        quranTextViewModel = ViewModelProvider(this).get(QuranTextViewModel::class.java)
        val root: View = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)

//        readViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        quranTextViewModel!!.getQuranText(10)
//            .observe(getViewLifecycleOwner(), object : Observer<QuranText?>() {
//                fun onChanged(@Nullable quranText: QuranText) {
//                    textView.text = quranText.text
//                }
//            })
        return root
    }
}
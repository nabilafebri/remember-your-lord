package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.quran

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.QuranText
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.QuranTextRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuranTextViewModel @Inject constructor(private val quranTextRepository: QuranTextRepository) :
    ViewModel() {
    private val _selectedQuranText = MutableLiveData<QuranText>()
    private val _allQuranTexts = MutableLiveData<List<QuranText>>()

    val allQuranTexts: LiveData<List<QuranText>> get() = _allQuranTexts;

    fun getQuranText(id: Int): LiveData<QuranText> {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedQuranText.postValue(quranTextRepository.getQuranText(id))
        }
        return _selectedQuranText
    }

    private fun getAllQuranTexts() {
        viewModelScope.launch(Dispatchers.IO) {
            _allQuranTexts.postValue(quranTextRepository.getAllQuranTexts())
        }
    }

    init {
        getAllQuranTexts()
    }
}
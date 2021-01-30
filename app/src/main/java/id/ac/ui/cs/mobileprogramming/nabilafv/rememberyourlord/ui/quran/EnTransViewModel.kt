package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.quran

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.EnTrans
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.EnTransRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnTransViewModel @Inject constructor(private val enTransRepository: EnTransRepository) :
    ViewModel() {
    private val _selectedEnTrans = MutableLiveData<EnTrans>()
    private val _allEnTrans = MutableLiveData<List<EnTrans>>()

    val allEnTrans: LiveData<List<EnTrans>> get() = _allEnTrans;

    fun getEnTrans(id: Int): LiveData<EnTrans> {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedEnTrans.postValue(enTransRepository.getEnTrans(id))
        }
        return _selectedEnTrans
    }

    private fun getAllEnTrans() {
        viewModelScope.launch(Dispatchers.IO) {
            _allEnTrans.postValue(enTransRepository.getAllEnTrans())
        }
    }

    init {
        getAllEnTrans()
    }
}
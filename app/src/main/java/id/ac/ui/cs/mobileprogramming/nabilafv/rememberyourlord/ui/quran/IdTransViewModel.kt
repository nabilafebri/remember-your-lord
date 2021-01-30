package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.ui.quran

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.EnTrans
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.IdTrans
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.EnTransRepository
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository.IdTransRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdTransViewModel @Inject constructor(private val idTransRepository: IdTransRepository) :
    ViewModel() {
    private val _selectedIdTrans = MutableLiveData<IdTrans>()
    private val _allIdTrans = MutableLiveData<List<IdTrans>>()

    val allIdTrans: LiveData<List<IdTrans>> get() = _allIdTrans;

    fun getIdTrans(id: Int): LiveData<IdTrans> {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedIdTrans.postValue(idTransRepository.getIdTrans(id))
        }
        return _selectedIdTrans
    }

    private fun getAllIdTrans() {
        viewModelScope.launch(Dispatchers.IO) {
            _allIdTrans.postValue(idTransRepository.getAllIdTrans())
        }
    }

    init {
        getAllIdTrans()
    }
}
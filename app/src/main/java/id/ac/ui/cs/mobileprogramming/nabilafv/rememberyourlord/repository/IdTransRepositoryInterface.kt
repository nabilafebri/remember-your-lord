package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.IdTrans

interface IdTransRepositoryInterface {
    fun getIdTrans(id: Int): LiveData<IdTrans>
    fun getAllIdTrans(): LiveData<List<IdTrans>>
}
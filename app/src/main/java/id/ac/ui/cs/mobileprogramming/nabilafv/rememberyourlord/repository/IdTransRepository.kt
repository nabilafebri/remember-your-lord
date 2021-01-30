package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.IdTransDao
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.IdTrans
import javax.inject.Inject

class IdTransRepository @Inject constructor(private val idTransDao: IdTransDao) :
    IdTransRepositoryInterface {
    override fun getIdTrans(id: Int): IdTrans {
        return idTransDao.getIdTrans(id)
    }

    override fun getAllIdTrans(): List<IdTrans> {
        return idTransDao.fetchAllIdTrans()
    }
}
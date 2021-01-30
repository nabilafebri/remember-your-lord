package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.EnTransDao
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.EnTrans
import javax.inject.Inject

class EnTransRepository @Inject constructor(private val enTransDao: EnTransDao) :
    EnTransRepositoryInterface {
    override fun getEnTrans(id: Int): EnTrans {
        return enTransDao.getEnTrans(id)
    }

    override fun getAllEnTrans(): List<EnTrans> {
        return enTransDao.fetchAllEnTrans()
    }
}
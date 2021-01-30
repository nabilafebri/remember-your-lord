package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.dao.QuranTextDao
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.QuranText
import javax.inject.Inject

class QuranTextRepository @Inject constructor(private val quranTextDao: QuranTextDao) :
    QuranTextRepositoryInterface {
    override fun getQuranText(id: Int): QuranText {
        return quranTextDao.getQuranText(id)
    }

    override fun getAllQuranTexts(): List<QuranText> {
        return quranTextDao.fetchAllQuranTexts()
    }
}
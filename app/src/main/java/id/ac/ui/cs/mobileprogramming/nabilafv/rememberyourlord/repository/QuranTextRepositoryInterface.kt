package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.QuranText

interface QuranTextRepositoryInterface {
    fun getQuranText(id: Int): QuranText
    fun getAllQuranTexts(): List<QuranText>
}
package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.EnTrans

interface EnTransRepositoryInterface {
    fun getEnTrans(id: Int): EnTrans
    fun getAllEnTrans(): List<EnTrans>
}
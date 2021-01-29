package id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.utils

import id.ac.ui.cs.mobileprogramming.nabilafv.rememberyourlord.model.Activity

interface RecyclerViewOnItemSelected {
    fun onItemSelected(activity: Activity)
}

interface RecyclerViewOnItemDone {
    fun onItemDone(activity: Activity)
}
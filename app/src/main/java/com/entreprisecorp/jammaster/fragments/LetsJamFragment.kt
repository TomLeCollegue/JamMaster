package com.entreprisecorp.jammaster.fragments

import com.entreprisecorp.jammaster.fastitems.KeyItem
import com.mikepenz.fastadapter.GenericItem

class LetsJamFragment : RecyclerFragment() {

    override fun refreshScreen() {
        val items = arrayListOf<GenericItem>()
        items += KeyItem("A#")
        fillAdapter(items)
    }
}
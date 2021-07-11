package com.entreprisecorp.jammaster.fragments

import android.view.View
import androidx.navigation.fragment.findNavController
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.fastitems.jamCardItem
import com.mikepenz.fastadapter.GenericItem

class HomeFragment : RecyclerFragment() {

    override fun refreshScreen() {
        val items = arrayListOf<GenericItem>()

        items += jamCardItem {
            title = getString(R.string.lets_jam_title)
            description = getString(R.string.lets_jam_desc)
            iconRes = R.drawable.ic_jam
            onClick = View.OnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLetsJamFragment())
            }
        }

        items += jamCardItem {
            title = getString(R.string.train_your_skills_title)
            description = getString(R.string.train_your_skills_desc)
            iconRes = R.drawable.ic_guitar
        }

        fillAdapter(items)
    }
}
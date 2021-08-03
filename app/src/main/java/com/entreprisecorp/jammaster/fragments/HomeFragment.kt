package com.entreprisecorp.jammaster.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.fastitems.cardItem
import com.google.android.material.transition.MaterialElevationScale
import com.mikepenz.fastadapter.GenericItem

class HomeFragment : RecyclerFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supportActionBar?.show()
    }


    override fun refreshScreen() {
        val items = arrayListOf<GenericItem>()

        items += cardItem {
            title = getString(R.string.lets_jam_title)
            description = getString(R.string.lets_jam_desc)
            imageRes = R.drawable.jam_image
            onClickRoot = {
                exitTransition = MaterialElevationScale(false).apply {
                    duration = 300
                }
                reenterTransition = MaterialElevationScale(true).apply {
                    duration = 300
                }
                val extras = FragmentNavigatorExtras(it to "CardDetailTransitionName")
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLetsJamFragment())
            }
        }

        items += cardItem {
            title = getString(R.string.train_your_skills_title)
            description = getString(R.string.train_your_skills_desc)
            imageRes = R.drawable.train_image
        }

        fillAdapter(items)
    }
}
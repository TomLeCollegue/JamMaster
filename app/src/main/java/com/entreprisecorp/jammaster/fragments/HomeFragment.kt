package com.entreprisecorp.jammaster.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.fastitems.cardItem
import com.google.android.material.transition.MaterialElevationScale
import com.mikepenz.fastadapter.GenericItem
import java.util.UUID

class HomeFragment : RecyclerFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supportActionBar?.show()
        binding.recyclerView.isTransitionGroup = true
    }

    override fun refreshScreen() {
        val items = arrayListOf<GenericItem>()

        items += cardItem {
            title = getString(R.string.lets_jam_title)
            description = getString(R.string.lets_jam_desc)
            imageRes = R.drawable.train_image
            transitionName = getString(R.string.email_card_transition_name) + "1233"

            onClickRoot = {
                val transitionName = getString(R.string.email_card_detail_transition_name)
                exitTransition = MaterialElevationScale(false).apply {
                    duration = resources.getInteger(R.integer.duration_transition).toLong()
                }
                reenterTransition = MaterialElevationScale(true).apply {
                    duration = resources.getInteger(R.integer.duration_transition).toLong()
                }
                val extras = FragmentNavigatorExtras(it to transitionName)
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLetsJamFragment(), extras)
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
package com.entreprisecorp.jammaster.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.fastitems.KeyItem
import com.google.android.material.transition.MaterialContainerTransform
import com.mikepenz.fastadapter.GenericItem

class LetsJamFragment : RecyclerFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.transitionName = getString(R.string.email_card_detail_transition_name)
        supportActionBar?.hide()
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a view in the hierarchy so we know it will be added under
            // the bottom app bar but over the elevation scale of the exiting HomeFragment.
            drawingViewId = R.id.navHostFragment
            duration = resources.getInteger(R.integer.duration_transition).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun refreshScreen() {
        val items = arrayListOf<GenericItem>()
        items += KeyItem("A#")
        fillAdapter(items)
    }
}

@ColorInt
@SuppressLint("Recycle")
fun Context.themeColor(
    @AttrRes themeAttrId: Int
): Int {
    return obtainStyledAttributes(
        intArrayOf(themeAttrId)
    ).use {
        it.getColor(0, Color.MAGENTA)
    }
}
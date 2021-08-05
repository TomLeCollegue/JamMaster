package com.entreprisecorp.jammaster.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use
import androidx.fragment.app.Fragment
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.databinding.FragmentJamBinding
import com.entreprisecorp.jammaster.fastitems.KeyItem
import com.google.android.material.transition.MaterialContainerTransform
import com.mikepenz.fastadapter.GenericItem

class LetsJamFragment : Fragment(R.layout.fragment_jam) {

    private lateinit var binding: FragmentJamBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentJamBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.transitionName = getString(R.string.email_card_detail_transition_name)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a view in the hierarchy so we know it will be added under
            // the bottom app bar but over the elevation scale of the exiting HomeFragment.
            drawingViewId = R.id.navHostFragment
            duration = resources.getInteger(R.integer.duration_transition).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
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
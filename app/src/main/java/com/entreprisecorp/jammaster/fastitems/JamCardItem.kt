package com.entreprisecorp.jammaster.fastitems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.databinding.JamCardItemBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class JamCardItem : AbstractBindingItem<JamCardItemBinding>() {

    override val type: Int = R.id.item_jam_card

    var title: String? = null
    var description: String? = null
    var onClick: View.OnClickListener? = null

    @DrawableRes
    var iconRes: Int? = null

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): JamCardItemBinding {
        return JamCardItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: JamCardItemBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.apply {
            titleTextView.text = title
            descTextView.text = description
            root.setOnClickListener(onClick)
            iconRes?.let { iconImageView.setImageResource(it) }
        }
    }

    override fun unbindView(binding: JamCardItemBinding) {
        super.unbindView(binding)
        binding.apply {
            titleTextView.text = null
            descTextView.text = null
            root.setOnClickListener(null)
        }
    }

}

fun jamCardItem(block: JamCardItem.() -> Unit): JamCardItem = JamCardItem().apply(block)
package com.entreprisecorp.jammaster.fastitems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.databinding.CardItemBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class CardItem : AbstractBindingItem<CardItemBinding>() {

    override val type: Int = R.id.item_card

    var title: String? = null
    var description: String? = null
    var onClickRoot: ((View) -> Unit)? = null
    var onClickButton: ((View) -> Unit)? = null
    var labelButton: String? = null

    @DrawableRes
    var imageRes: Int? = null

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): CardItemBinding {
        return CardItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: CardItemBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.apply {
            titleTextView.text = title
            descTextView.text = description
            root.setOnClickListener {
                onClickRoot?.let { it1 -> it1(root) }
            }

            if(labelButton != null) {
                buttonCard.text = title
                buttonCard.setOnClickListener {
                    onClickButton?.let { it1 -> it1(root) }
                }
            } else {
                buttonCard.visibility = View.GONE
            }
            imageRes?.let { imageView.setImageResource(it) }
        }
    }

    override fun unbindView(binding: CardItemBinding) {
        super.unbindView(binding)
        binding.apply {
            titleTextView.text = null
            descTextView.text = null
            root.setOnClickListener(null)
        }
    }

}

fun cardItem(block: CardItem.() -> Unit): CardItem = CardItem().apply(block)
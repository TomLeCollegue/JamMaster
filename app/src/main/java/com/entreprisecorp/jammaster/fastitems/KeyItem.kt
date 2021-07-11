package com.entreprisecorp.jammaster.fastitems

import android.view.LayoutInflater
import android.view.ViewGroup
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.databinding.KeyItemBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class KeyItem(val key: String) : AbstractBindingItem<KeyItemBinding>() {

    override val type: Int  = R.id.item_key

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): KeyItemBinding {
        return KeyItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: KeyItemBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.textView.text = key
    }
}
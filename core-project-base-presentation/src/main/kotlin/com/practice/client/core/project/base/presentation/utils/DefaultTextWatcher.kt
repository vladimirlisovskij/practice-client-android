package com.practice.client.core.project.base.presentation.utils

import android.text.Editable
import android.text.TextWatcher

open class DefaultTextWatcher : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(text: Editable) {}

    companion object {
        fun afterTextChanged(block: (Editable) -> Unit) = object : DefaultTextWatcher() {
            override fun afterTextChanged(text: Editable) {
                block.invoke(text)
            }
        }
    }
}




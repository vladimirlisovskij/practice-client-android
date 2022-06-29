package com.practice.client.core.project.base.presentation.activity

import androidx.annotation.LayoutRes

abstract class AuthActivity(
    @LayoutRes layout: Int
): BaseActivity(layout) {
    abstract fun onAnAuthorize()
}
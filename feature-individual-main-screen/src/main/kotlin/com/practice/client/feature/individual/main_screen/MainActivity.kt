package com.practice.client.feature.individual.main_screen

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.practice.client.core.project.base.presentation.AuthApplication
import com.practice.client.core.project.base.presentation.activity.AuthActivity
import com.practice.client.feature.individual.main_screen.databinding.MainBinding

class MainActivity : AuthActivity(R.layout.main) {
    private val binding by viewBinding(MainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val navController = findNavController(binding.navContainer.id)
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onAnAuthorize() {
        (applicationContext as AuthApplication).onUnAuthorize()
        Intent()
            .setClassName(
                this,
                "com.practice.client.feature.individual.auth.IndividualAuthFlowActivity"
            ).let(::startActivity)

        finish()
    }
}
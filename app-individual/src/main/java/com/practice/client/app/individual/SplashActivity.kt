package com.practice.client.app.individual

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.practice.client.core.project.base.presentation.AuthApplication
import com.practice.client.feature.individual.auth.IndividualAuthFlowActivity
import com.practice.client.feature.individual.main_screen.MainActivity
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val userRepository by lazy { (this.applicationContext as App).appComponent.getUserRepository() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            if (userRepository.getDefaultHeaders().jwt == null) {
                showLoginScreen()
            } else {
                showAppScreen()
            }
        }
    }

    private fun showLoginScreen() {
        startActivity(Intent(this, IndividualAuthFlowActivity::class.java))
        finish()
    }

    private fun showAppScreen() {
        (applicationContext as AuthApplication).onAuthorize()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

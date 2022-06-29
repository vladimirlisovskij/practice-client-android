package com.practice.client.feature.individual.auth.auth_types

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.practice.client.core.project.base.presentation.AuthApplication
import com.practice.client.core.project.base.presentation.fragment.BaseFragment
import com.practice.client.feature.individual.auth.R
import com.practice.client.feature.individual.auth.databinding.IndividualAuthorizationBinding
import com.practice.client.feature.project.login.LoginFragment

class IndividualAuthTypesFragment : BaseFragment(R.layout.individual_authorization) {
    private val binding by viewBinding(IndividualAuthorizationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        setFragmentResultListener(LoginFragment.KEY_AUTH_REQUEST) { _, _ ->
            (requireContext().applicationContext as AuthApplication).onAuthorize()
            val intent = Intent()
                .setClassName(
                    requireContext(),
                    "com.practice.client.feature.individual.main_screen.MainActivity"
                )

            requireActivity().startActivity(intent)
            requireActivity().finish()
        }

        with(binding) {
            mbLogin.setOnClickListener {
                findNavController().navigate(IndividualAuthTypesFragmentDirections.actionIndividualAuthTypesFragmentToIndividualLogin())
            }

            mbRegistration.setOnClickListener {
                findNavController().navigate(IndividualAuthTypesFragmentDirections.actionIndividualAuthTypesFragmentToIndividualRegistration())
            }

        }
    }
}


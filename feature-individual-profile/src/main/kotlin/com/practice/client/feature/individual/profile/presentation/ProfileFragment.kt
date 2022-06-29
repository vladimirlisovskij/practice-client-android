package com.practice.client.feature.individual.profile.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.practice.client.core.project.base.presentation.activity.AuthActivity
import com.practice.client.core.project.base.presentation.fragment.BaseFragment
import com.practice.client.core.project.base.presentation.mvvm.action.RefreshScreenAction
import com.practice.client.core.project.base.presentation.mvvm.event.LogOutEvent
import com.practice.client.core.project.base.presentation.utils.PresentationExt.createDialog
import com.practice.client.core.project.base.presentation.utils.PresentationExt.providedViewModels
import com.practice.client.feature.individual.profile.R
import com.practice.client.feature.individual.profile.databinding.ProfileBinding
import com.practice.client.feature.individual.profile.presentation.mvvm.LogOutAction
import com.practice.client.feature.individual.profile.presentation.mvvm.ProfileInfoState
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment(R.layout.profile) {
    private val binding by viewBinding(ProfileBinding::bind)
    private val viewModel by providedViewModels { ProfileDependencies.creator.invoke().getProfileViewModel() }
    private var dialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.mbRefresh.setOnClickListener { viewModel.submitAction(RefreshScreenAction) }
        binding.mbLogout.setOnClickListener { viewModel.submitAction(LogOutAction) }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.screenState.collect { state ->
                        dialog = if (state.dialogState != null) {
                            state.dialogState.createDialog(requireContext(), viewModel::submitAction)
                        } else {
                            dialog?.dismiss()
                            null
                        }

                        if (state.profileState is ProfileInfoState.Success) {
                            binding.tvFullName.text = requireContext().getString(R.string.fullName)
                                .format(state.profileState.data.fullName)
                        }

                        with(binding) {
                            llProfileInfo.isVisible = state.profileState is ProfileInfoState.Success
                            llErrorContainer.isVisible = state.profileState is ProfileInfoState.Error
                            progress.isVisible = state.profileState is ProfileInfoState.Loading
                        }
                    }
                }

                launch {
                    viewModel.screenEvents.collect { event ->
                        when (event) {
                            LogOutEvent -> (requireActivity() as AuthActivity).onAnAuthorize()
                        }
                    }
                }
            }
        }
    }
}
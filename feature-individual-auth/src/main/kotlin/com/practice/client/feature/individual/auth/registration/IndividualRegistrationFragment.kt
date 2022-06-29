package com.practice.client.feature.individual.auth.registration

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.practice.client.core.project.base.presentation.fragment.BaseFragment
import com.practice.client.core.project.base.presentation.mvvm.action.PrimaryButtonPressed
import com.practice.client.core.project.base.presentation.mvvm.event.FinishActivityEvent
import com.practice.client.core.project.base.presentation.utils.DefaultTextWatcher
import com.practice.client.core.project.base.presentation.utils.PresentationExt.createDialog
import com.practice.client.core.project.base.presentation.utils.PresentationExt.providedViewModels
import com.practice.client.core.project.base.presentation.utils.PresentationExt.setTextWithSelection
import com.practice.client.core.project.base.presentation.utils.PresentationExt.withoutTextWatcher
import com.practice.client.feature.individual.auth.R
import com.practice.client.feature.individual.auth.databinding.IndividualRegistrationBinding
import com.practice.client.feature.individual.auth.registration.mvvm.IndividualRegistrationScreenAction
import com.practice.client.feature.project.login.LoginFragment
import com.practice.client.feature.project.login.OnAuthorizeEvent
import kotlinx.coroutines.launch

class IndividualRegistrationFragment : BaseFragment(R.layout.individual_registration) {
    private val viewModel by providedViewModels {
        IndividualRegistrationDependencies.creator.invoke().getIndividualRegistrationViewModel()
    }

    private val binding by viewBinding(IndividualRegistrationBinding::bind)

    private val nickNameWatcher = DefaultTextWatcher.afterTextChanged { text ->
        viewModel.submitAction(IndividualRegistrationScreenAction.NickNameChanged(text.toString()))
    }

    private val fullNameWatcher = DefaultTextWatcher.afterTextChanged { text ->
        viewModel.submitAction(IndividualRegistrationScreenAction.FullNameChanged(text.toString()))
    }

    private val passwordWatcher = DefaultTextWatcher.afterTextChanged { text ->
        viewModel.submitAction(IndividualRegistrationScreenAction.PasswordChanged(text.toString()))
    }

    private var dialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            tietNickName.addTextChangedListener(nickNameWatcher)
            tietFullName.addTextChangedListener(fullNameWatcher)
            tietPassword.addTextChangedListener(passwordWatcher)
            mbContinue.setOnClickListener { viewModel.submitAction(PrimaryButtonPressed) }
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.screenState.collect { state ->
                        binding.tietNickName.withoutTextWatcher(nickNameWatcher) {
                            setTextWithSelection(state.nickName)
                        }

                        binding.tietFullName.withoutTextWatcher(fullNameWatcher) {
                            setTextWithSelection(state.fullName)
                        }

                        binding.tietPassword.withoutTextWatcher(passwordWatcher) {
                            setTextWithSelection(state.password)
                        }

                        val dialogState = state.dialogState
                        dialog = if (dialogState != null) {
                            dialogState.createDialog(requireContext(), viewModel::submitAction)
                        } else {
                            dialog?.dismiss()
                            null
                        }

                        binding.lProgress.isVisible = state.isShowProgress
                    }
                }

                launch {
                    viewModel.screenEvents.collect { event ->
                        when (event) {
                            OnAuthorizeEvent -> {
                                parentFragmentManager.setFragmentResult(
                                    LoginFragment.KEY_AUTH_REQUEST,
                                    Bundle.EMPTY
                                )

                                findNavController().navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}
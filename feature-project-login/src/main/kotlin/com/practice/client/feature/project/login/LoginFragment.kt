package com.practice.client.feature.project.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.practice.client.core.project.base.presentation.fragment.BaseFragment
import com.practice.client.core.project.base.presentation.mvvm.action.PrimaryButtonPressed
import com.practice.client.core.project.base.presentation.utils.DefaultTextWatcher
import com.practice.client.core.project.base.presentation.utils.PresentationExt.createDialog
import com.practice.client.core.project.base.presentation.utils.PresentationExt.providedViewModels
import com.practice.client.core.project.base.presentation.utils.PresentationExt.setTextWithSelection
import com.practice.client.core.project.base.presentation.utils.PresentationExt.withoutTextWatcher
import com.practice.client.feature.project.login.databinding.LoginBinding
import com.practice.client.feature.project.login.mvvm.LoginProgressScreenState
import com.practice.client.feature.project.login.mvvm.LoginScreenAction
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment(R.layout.login) {
    private val viewModel by providedViewModels { LoginDependencies.creator.invoke().getLoginViewModel() }
    private val binding by viewBinding(LoginBinding::bind)
    private var dialog: AlertDialog? = null

    private val nicknameListener = DefaultTextWatcher.afterTextChanged { text ->
        viewModel.submitAction(LoginScreenAction.NicknameChanged(text.toString()))
    }

    private val passwordListener = DefaultTextWatcher.afterTextChanged { text ->
        viewModel.submitAction(LoginScreenAction.PasswordChanged(text.toString()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            tietNickName.addTextChangedListener(nicknameListener)
            tietPassword.addTextChangedListener(passwordListener)
            mbContinue.setOnClickListener { viewModel.submitAction(PrimaryButtonPressed) }
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.screenState.collect { state: LoginProgressScreenState ->
                        binding.tietNickName.withoutTextWatcher(nicknameListener) {
                            setTextWithSelection(state.nickname)
                        }

                        binding.tietPassword.withoutTextWatcher(passwordListener) {
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
                                    KEY_AUTH_REQUEST,
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

    companion object {
        const val KEY_AUTH_REQUEST = "LoginFragment.KEY_AUTH_REQUEST"
    }
}

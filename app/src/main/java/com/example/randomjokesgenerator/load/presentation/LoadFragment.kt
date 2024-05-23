package com.example.randomjokesgenerator.load.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.randomjokesgenerator.core.di.ManageViewModels
import com.example.randomjokesgenerator.databinding.FragmentLoadBinding

class LoadFragment : Fragment(), AsyncUpdateLoad { // (LoadUiState) -> Unit

    private var _binding: FragmentLoadBinding? = null
    private val binding: FragmentLoadBinding
        get() = _binding ?: throw RuntimeException("FragmentLoadBinding == null")

    private lateinit var viewModel: LoadViewModel
    private lateinit var showUi: (LoadUiState) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manageViewModels = activity as ManageViewModels
        viewModel = manageViewModels.viewModel(LoadViewModel::class.java)

        binding.retryButton.setOnClickListener {
            viewModel.retry()
        }

        viewModel.init(firstRun = savedInstanceState == null)
    }

    override fun asyncUpdateLoad(loadUiState: LoadUiState) {
        loadUiState.update(
            binding.progressBar,
            binding.errorTextView,
            binding.retryButton,
        )
        loadUiState.navigate {
            val manageViewModels = requireActivity() as ManageViewModels
            manageViewModels.clear(LoadViewModel::class.java)
            (requireActivity() as LoadNavigation).navigateFromLoad()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopGettingUpdates()
    }

    override fun onResume() {
        super.onResume()
        viewModel.startGettingUpdates(observer = showUi)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface AsyncUpdateLoad{
    fun asyncUpdateLoad(loadUiState: LoadUiState)
}

interface LoadNavigation {
    fun navigateFromLoad()
}
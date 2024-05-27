package com.example.randomjokesgenerator.joke.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.randomjokesgenerator.core.di.ManageViewModels
import com.example.randomjokesgenerator.databinding.FragmentJokeBinding

class JokeFragment : Fragment() {

    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: JokeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var uiState: JokeUiState

        val showUi: () -> Unit = {
            uiState.update(
                categoryView = binding.categoryTextView,
                jokeView = binding.jokeTextView,
                nextView = binding.nextButton,
                downloadView = binding.downloadButton,
            )
        }

        val manageViewModels = (activity as ManageViewModels)
        viewModel = manageViewModels.viewModel(JokeViewModel::class.java)

        binding.nextButton.setOnClickListener {
            uiState = viewModel.nextJoke()
            showUi.invoke()
        }

        binding.downloadButton.setOnClickListener {
            viewModel.clear()
            manageViewModels.clear(JokeViewModel::class.java)
            (activity as JokeNavigation).navigateFromJoke()
        }

        uiState = viewModel.init(savedInstanceState == null)
        showUi.invoke()
    }
}

interface JokeNavigation {
    fun navigateFromJoke()
}
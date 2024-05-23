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
    private lateinit var uiState: JokeUiState

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

       val manageViewModels = (activity as ManageViewModels)
       viewModel = manageViewModels.viewModel(JokeViewModel::class.java)

        binding.nextButton.setOnClickListener {
            viewModel.nextJoke()
            uiState.update( // dry 3 times
                counterView = binding.categoryTextView,
                wordView = binding.jokeTextView,
                scoreView = binding.nextButton,
                submitView = binding.downloadButton,
            )
        }

        binding.downloadButton.setOnClickListener {
            viewModel.clear() // reset repository
            manageViewModels.clear(JokeViewModel::class.java) // delete VM instance
            (activity as JokeNavigation).navigateFromJoke() // activity or requireActivity()
        }

        if (savedInstanceState == null) {
            uiState = viewModel.init()
            uiState.update(
                counterView = binding.categoryTextView,
                wordView = binding.jokeTextView,
                scoreView = binding.nextButton,
                submitView = binding.downloadButton,
            )
        }
    }

    override fun onResume() {
        super.onResume()
        uiState.update(counterView = binding.categoryTextView,
            wordView = binding.jokeTextView,
            scoreView = binding.nextButton,
            submitView = binding.downloadButton,)
        }
    }
}

interface JokeNavigation {
    fun navigateFromJoke()
}
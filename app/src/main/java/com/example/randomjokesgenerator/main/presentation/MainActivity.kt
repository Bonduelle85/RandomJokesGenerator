package com.example.randomjokesgenerator.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomjokesgenerator.R
import com.example.randomjokesgenerator.core.di.ManageViewModels
import com.example.randomjokesgenerator.databinding.ActivityMainBinding
import com.example.randomjokesgenerator.joke.presentation.JokeNavigation
import com.example.randomjokesgenerator.joke.presentation.JokeScreen
import com.example.randomjokesgenerator.load.presentation.LoadNavigation
import com.example.randomjokesgenerator.load.presentation.LoadScreen

class MainActivity : AppCompatActivity(), Navigation, ManageViewModels {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel = viewModel(MainViewModel::class.java)

        val lastScreen =  viewModel.init(savedInstanceState == null)
        navigate(lastScreen)
    }


    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }

    override fun clear(clazz: Class<out MyViewModel>) {
        (application as ManageViewModels).clear(clazz)
    }

    override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
        return (application as ManageViewModels).viewModel(clazz)
    }
}

interface Navigation : LoadNavigation, JokeNavigation {
    fun navigate(screen: Screen)

    override fun navigateFromLoad() {
        navigate(JokeScreen)
    }

    override fun navigateFromJoke() {
        navigate(LoadScreen)
    }
}
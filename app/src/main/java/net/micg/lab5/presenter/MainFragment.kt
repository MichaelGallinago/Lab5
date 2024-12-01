package net.micg.lab5.presenter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import javax.inject.Inject
import kotlin.getValue
import net.micg.lab5.R
import net.micg.lab5.databinding.FragmentMainBinding
import net.micg.lab5.di.appComponent
import net.micg.lab5.di.viewModel.ViewModelFactory

class MainFragment : Fragment(R.layout.fragment_main) {
    @Inject
    lateinit var factory: ViewModelFactory

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
}
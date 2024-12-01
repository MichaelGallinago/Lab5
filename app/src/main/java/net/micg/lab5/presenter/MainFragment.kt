package net.micg.lab5.presenter

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import javax.inject.Inject
import kotlin.getValue
import net.micg.lab5.R
import net.micg.lab5.databinding.FragmentMainBinding
import net.micg.lab5.di.appComponent
import net.micg.lab5.di.viewModel.ViewModelFactory
import net.micg.lab5.presenter.MainViewModel.Companion.EMPTY_COLOR
import java.util.Locale

class MainFragment : Fragment(R.layout.fragment_main) {
    @Inject
    lateinit var factory: ViewModelFactory

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels { factory }

    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable = object : Runnable {
        override fun run() {
            viewModel.syncData()
            handler.postDelayed(this, 5000)
        }
    }

    private var precision = 1
    private lateinit var colorsAdapter: ArrayAdapter<String>

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
        handler.post(runnable)
    }

    override fun onDetach() {
        super.onDetach()
        handler.removeCallbacks(runnable)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpObservers()
        setUpFragment()
        viewModel.initData()
    }

    private fun setUpAdapter() {
        colorsAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mutableListOf<String>()
        )
    }

    private fun setUpObservers() = with(viewModel) {
        with(binding) {
            lampState.observe(viewLifecycleOwner) { state ->
                switchButton.isChecked = state
            }

            brightness.observe(viewLifecycleOwner) { brightness ->
                brightnessBar.progress = brightness
            }

            brightnessLevels.observe(viewLifecycleOwner) { brightnessLevels ->
                with(brightnessBar) {
                    precision = brightnessLevels.precision
                    min = brightnessLevels.min
                    max = brightnessLevels.max
                }
            }

            colorName.observe(viewLifecycleOwner) { color ->
                colorSpinner.setSelection(colorsAdapter.getPosition(color))
            }

            colorNames.observe(viewLifecycleOwner) { colors ->
                with(colorsAdapter) {
                    clear()
                    add(EMPTY_COLOR)
                    addAll(colors)
                }
            }
        }
    }

    private fun setUpFragment() = with(binding) {
        brightnessBar.setOnSeekBarChangeListener(BrightnessBarChangeListener())

        switchButton.setOnCheckedChangeListener { _, isChecked ->
            viewModel.switchLampState(isChecked)
        }

        setUpColorSpinner()
    }

    private fun setUpColorSpinner() = with(colorsAdapter) {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.colorSpinner.adapter = this
        binding.colorSpinner.onItemSelectedListener = ColorSpinnerItemSelectedListener()
    }

    private fun setBrightnessValue(value: Int) {
        binding.brightnessValue.text = String.format(Locale.getDefault(), "%d", value)
    }

    private inner class BrightnessBarChangeListener : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            setBrightnessValue(progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            seekBar.progress = kotlin.math.round(
                seekBar.progress.toDouble() / precision.toDouble()
            ).toInt() * precision

            viewModel.setBrightness(seekBar.progress)
        }
    }

    private inner class ColorSpinnerItemSelectedListener() : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parentView: AdapterView<*>, selectedView: View?, position: Int, id: Long,
        ) {
            viewModel.setColor(parentView.getItemAtPosition(position) as String)
        }

        override fun onNothingSelected(parentView: AdapterView<*>) {}
    }
}

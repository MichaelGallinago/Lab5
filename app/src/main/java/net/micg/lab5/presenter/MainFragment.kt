package net.micg.lab5.presenter

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import javax.inject.Inject
import kotlin.getValue
import net.micg.lab5.R
import net.micg.lab5.data.Color
import net.micg.lab5.databinding.FragmentMainBinding
import net.micg.lab5.di.appComponent
import net.micg.lab5.di.viewModel.ViewModelFactory
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
        setUpSynchronization()
        setUpFragment()
        viewModel.syncData()
    }

    private fun setUpSynchronization() = with(viewModel) {
        with(binding) {
            lampState.observe(viewLifecycleOwner) { state ->
                switchButton.isChecked = state
            }

            brightness.observe(viewLifecycleOwner) { brightness ->
                brightnessBar.progress = brightness
            }

            color.observe(viewLifecycleOwner) { color ->
                colorSelector.setSelection(color.id)
            }

            colors.observe(viewLifecycleOwner) { colors ->
                setUpSpinner(colors)
            }
        }
    }

    private fun setUpFragment() = with(binding) {
        brightnessBar.setOnSeekBarChangeListener(BrightnessBarChangeListener())

        switchButton.setOnCheckedChangeListener { _, isChecked ->
            viewModel.switchLampState(isChecked)
        }

        colorSelector.onItemSelectedListener = ColorSpinnerItemSelectedListener()
    }

    private fun setUpSpinner(colors: List<Color>) = with(
        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, colors)
    ) {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.colorSelector.adapter = this
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
            viewModel.setBrightness(seekBar.progress)
        }
    }

    private inner class ColorSpinnerItemSelectedListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parentView: AdapterView<*>,
            selectedView: View?,
            position: Int,
            id: Long,
        ) {
            // Получаем выбранный элемент
            val selectedItem = parentView.getItemAtPosition(position) as String
            Toast.makeText(context, "Выбрано: $selectedItem", Toast.LENGTH_SHORT).show()
        }

        override fun onNothingSelected(parentView: AdapterView<*>) {
            // Действия, когда ничего не выбрано
            Toast.makeText(context, "Ничего не выбрано", Toast.LENGTH_SHORT).show()
        }
    }
}

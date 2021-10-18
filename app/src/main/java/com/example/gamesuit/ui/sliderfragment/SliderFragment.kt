package com.example.gamesuit.ui.sliderfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamesuit.R
import com.example.gamesuit.databinding.FragmentSliderBinding

class SliderFragment (  private val desc: String,
                        private val imgSrc: Int
) : Fragment() {

    private lateinit var binding: FragmentSliderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSliderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataSlider()
    }

    private fun setDataSlider() {
        binding.tvDesc.text=desc
        binding.ivIntro.setImageResource(imgSrc);
    }


}
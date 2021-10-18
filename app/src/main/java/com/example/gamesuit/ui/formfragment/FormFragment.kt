package com.example.gamesuit.ui.formfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gamesuit.databinding.FragmentFormBinding
import com.example.gamesuit.ui.menu.Menu

class FormFragment : Fragment() {

    private lateinit var binding:FragmentFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentFormBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setName()
    }

    private fun setName() {
        binding.btnSetName.setOnClickListener {
            navigateToMenu()
        }
    }

    private fun navigateToMenu() {
        val intent = Intent (this.context, Menu::class.java)
        intent.putExtra(Menu.name,"${binding.etName.text}")
        startActivity(intent)
    }
}

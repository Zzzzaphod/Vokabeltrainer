package com.appzzzz.vokabeltrainer.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.appzzzz.vokabeltrainer.R
import com.appzzzz.vokabeltrainer.databinding.FragmentMultipleChoiceBinding
import com.appzzzz.vokabeltrainer.databinding.FragmentStartBinding

class StartFragment :Fragment() {
    private var _binding: FragmentStartBinding?= null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.buttonStart.setOnClickListener {
            it.findNavController().navigate(R.id.navigation_multiple_choice)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
package com.appzzzz.vokabeltrainer.ui.spelling

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appzzzz.vokabeltrainer.databinding.FragmentSpellingBinding
import com.appzzzz.vokabeltrainer.ui.BaseFragment

class SpellingFragment : BaseFragment() {

    private var _binding: FragmentSpellingBinding? = null

    private val binding get() = _binding!!

    private var points = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val dataInputViewModel =
        //    ViewModelProvider(this).get(DataInputViewModel::class.java)

        _binding = FragmentSpellingBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
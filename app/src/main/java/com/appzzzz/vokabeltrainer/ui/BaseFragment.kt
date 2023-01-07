package com.appzzzz.vokabeltrainer.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appzzzz.vokabeltrainer.MainActivity
import com.appzzzz.vokabeltrainer.R


open class BaseFragment : Fragment() {

    protected val mainActivity get() = (activity as MainActivity)

    protected val vocabularyDict get() = mainActivity!!.vocabularyDict

    protected val sharedPreferences get() = mainActivity!!.sharedPreferences

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_base, container, false)
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//    }


}
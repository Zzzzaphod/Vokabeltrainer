package com.appzzzz.vokabeltrainer.ui.multipleChoice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.appzzzz.vokabeltrainer.MainActivity
import com.appzzzz.vokabeltrainer.data.Vocabulary
import com.appzzzz.vokabeltrainer.databinding.FragmentMultipleChoiceBinding
import kotlin.random.Random

class MultipleChoiceFragment : Fragment() {

    private var _binding: FragmentMultipleChoiceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var points = 0

    private val mainActivity get() = (activity as MainActivity)

    private val vocabularyDict get() = mainActivity!!.vocabularyDict

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMultipleChoiceBinding.inflate(inflater, container, false)
        val root: View = binding.root

        startMultipleChoiceGame()
        return root
    }

    fun startMultipleChoiceGame() {
        points = 0
        setNewQuestion()
    }

    fun setNewQuestion() {
        if(vocabularyDict!!.selectRandomVocabulary()==null){
            Log.e("startMultipleChoiceGame", "No vocabulary selected! (selectedVocabulary == null)")
            return
        }

        val allPossibleAnswerStrings = vocabularyDict!!.getAllPossibleAnswers(4)

        binding.textviewMultipleChoiceQuestion.text = vocabularyDict!!.selectedVocabulary!!.germanVocabulary
        binding.buttonMultipleChoiceAnswer1.text = allPossibleAnswerStrings!![0]
        binding.buttonMultipleChoiceAnswer2.text = allPossibleAnswerStrings!![1]
        binding.buttonMultipleChoiceAnswer3.text = allPossibleAnswerStrings!![2]
        binding.buttonMultipleChoiceAnswer4.text = allPossibleAnswerStrings!![3]
        binding.buttonMultipleChoiceAnswer0.text = "Keine der Antwortmöglichkeiten"




    }

    fun stopMultipleChoiceGame() {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.appzzzz.vokabeltrainer.ui.multipleChoice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.appzzzz.vokabeltrainer.MainActivity
import com.appzzzz.vokabeltrainer.R
import com.appzzzz.vokabeltrainer.data.Vocabulary
import com.appzzzz.vokabeltrainer.databinding.FragmentMultipleChoiceBinding
import kotlin.random.Random

class MultipleChoiceFragment : Fragment(), OnClickListener {

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

        binding.buttonMultipleChoiceAnswer0.setOnClickListener(this)
        binding.buttonMultipleChoiceAnswer1.setOnClickListener(this)
        binding.buttonMultipleChoiceAnswer2.setOnClickListener(this)
        binding.buttonMultipleChoiceAnswer3.setOnClickListener(this)
        binding.buttonMultipleChoiceAnswer4.setOnClickListener(this)
        showPoints()
        startMultipleChoiceGame()
        return root
    }

    fun startMultipleChoiceGame() {
        points = 0
        setNewQuestion()
    }

    fun setNewQuestion() {

        if(vocabularyDict?.selectRandomVocabulary()==null){
            Log.e("setNewQuestion", "No vocabulary selected! (selectedVocabulary == null)")
            return
        }

        val allPossibleAnswerStrings = vocabularyDict?.getAllPossibleAnswers(4)

        binding.textviewMultipleChoiceQuestion.text = vocabularyDict!!.selectedVocabulary!!.germanVocabulary
        binding.buttonMultipleChoiceAnswer1.text = allPossibleAnswerStrings!![0]
        binding.buttonMultipleChoiceAnswer2.text = allPossibleAnswerStrings!![1]
        binding.buttonMultipleChoiceAnswer3.text = allPossibleAnswerStrings!![2]
        binding.buttonMultipleChoiceAnswer4.text = allPossibleAnswerStrings!![3]
        binding.buttonMultipleChoiceAnswer0.text = "Keine der Antwortmöglichkeiten"

    }

    fun stopMultipleChoiceGame() {

    }

    fun addPoints(points: Int) {
        this.points += points
        showPoints()
    }

    fun showPoints() {
        binding.textviewPoints.text = "$points Punkte"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        if(v is Button){
            val answerString = (v as Button).text
            if(answerString == vocabularyDict?.selectedVocabulary?.englishVocabulary){
                addPoints(1)
            }
            setNewQuestion()
        }
    }
}
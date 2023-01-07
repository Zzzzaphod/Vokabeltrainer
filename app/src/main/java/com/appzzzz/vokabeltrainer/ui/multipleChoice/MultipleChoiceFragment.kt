package com.appzzzz.vokabeltrainer.ui.multipleChoice

import android.content.Context
import android.content.SharedPreferences
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
import com.appzzzz.vokabeltrainer.ui.BaseFragment
import kotlin.random.Random

class MultipleChoiceFragment : BaseFragment(), OnClickListener {

    private var _binding: FragmentMultipleChoiceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var points = 0

    private var correctVocsSinceLastFault = 0

    private var highScore: Int = 0

    //private lateinit var sharedPreferencesMultipleChoice: SharedPreferences

    private var startTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMultipleChoiceBinding.inflate(inflater, container, false)
        val root: View = binding.root


        highScore = sharedPreferences.getInt(getString(R.string.shared_prefs_high_score),0)

        //binding.buttonMultipleChoiceAnswer0.setOnClickListener(this)
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
        correctVocsSinceLastFault = 0
        setNewQuestion()
    }

    fun setNewQuestion() {

        if(vocabularyDict?.selectRandomLearnVocabulary()==null){
            Log.e("setNewQuestion", "No vocabulary selected! (selectedVocabulary == null)")
            return
        }

        val allPossibleAnswerStrings = vocabularyDict?.getAllPossibleAnswers(4)

        binding.textviewMultipleChoiceQuestion.text = vocabularyDict!!.selectedVocabulary!!.germanVocabulary
        binding.buttonMultipleChoiceAnswer1.text = allPossibleAnswerStrings!![0]
        binding.buttonMultipleChoiceAnswer2.text = allPossibleAnswerStrings!![1]
        binding.buttonMultipleChoiceAnswer3.text = allPossibleAnswerStrings!![2]
        binding.buttonMultipleChoiceAnswer4.text = allPossibleAnswerStrings!![3]
        //binding.buttonMultipleChoiceAnswer0.text = "Keine der Antwortmöglichkeiten"

        startTime = System.currentTimeMillis()

    }

    fun stopMultipleChoiceGame() {

    }

    fun calculatePoints() : Int {
        var secondsToAnswer = 5
        var timeDiff = System.currentTimeMillis()-startTime
        timeDiff /= 1000
        timeDiff = Math.min(timeDiff, secondsToAnswer.toLong())

        return (secondsToAnswer+1-timeDiff).toInt()*correctVocsSinceLastFault

    }
    fun addPoints(points: Int) {
        this.points += points
        if(this.points>this.highScore)
            setHighScore(this.points)
        showPoints()
    }

    fun setPointsToZero() {
        this.points = 0
        showPoints()
    }

    fun setHighScore(highScore: Int) {
        this.highScore = highScore
        val editor = sharedPreferences.edit()
        editor.putInt(getString(R.string.shared_prefs_high_score),highScore)
        editor.commit()
    }

    fun showPoints() {
        binding.textviewPoints.text = "$points Punkte (Highscore: $highScore)"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        if(v is Button){
            val answerString = (v as Button).text
            if(answerString == vocabularyDict?.selectedVocabulary?.englishVocabulary){
                correctVocsSinceLastFault += 1
                var points = calculatePoints()
                addPoints(points)
            }
            else{
                correctVocsSinceLastFault = 0
                setPointsToZero()
            }
            setNewQuestion()
        }
    }
}
package com.appzzzz.vokabeltrainer.ui.dataInput

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.appzzzz.vokabeltrainer.data.Vocabulary
import com.appzzzz.vokabeltrainer.databinding.FragmentDataInputBinding
import com.appzzzz.vokabeltrainer.sql.DbHelper
import com.google.gson.Gson
import java.io.File
import java.io.FileWriter

class DataInputFragment : Fragment() {

    private var _binding: FragmentDataInputBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val dataInputViewModel =
        //    ViewModelProvider(this).get(DataInputViewModel::class.java)

        _binding = FragmentDataInputBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val editTextVocabularyGerman: EditText = binding.edittextVocabularyGerman
        val editTextVocabularyEnglish: EditText = binding.edittextVocabularyEnglish

        val firstVoc = Vocabulary("Katze", "cat")
        val secondVoc = Vocabulary("Hund", "dog")
        val thirdVoc = Vocabulary("Vogel", "bird")
        val fourthVoc = Vocabulary("Huhn", "chicken")

        val dbHelper = DbHelper(requireContext(), "Vocabularies")
        dbHelper.insertVoc(firstVoc)
        dbHelper.insertVoc(secondVoc)
        dbHelper.insertVoc(thirdVoc)
        dbHelper.insertVoc(fourthVoc)

        Log.i("DataDirectory:", Environment.getDataDirectory().toString() + "/Vocabularies")
//        val vocDatabase : SQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile,null)
//        vocDatabase.execSQL("create table if not exists vocabulary (germanVoc VARCHAR, englishVoc VARCHAR)")
//        vocDatabase.execSQL("insert into vocabulary (germanVoc, englishVoc) values ('Hund', 'dog')")
//        vocDatabase.execSQL("insert into vocabulary (germanVoc, englishVoc) values ('Katze', 'cat')")
//        vocDatabase.execSQL("insert into vocabulary (germanVoc, englishVoc) values ('Vogel', 'bird')")
//        vocDatabase.execSQL("insert into vocabulary (germanVoc, englishVoc) values ('Huhn', 'chicken')")
//
//        val c = vocDatabase.rawQuery("select * from vocabulary", null)
//
//        val germanIndex = c.getColumnIndex("germanVoc")
//        val englishIndex = c.getColumnIndex("englishVoc")
//
//        c.moveToFirst()
//
//        while(c!= null){
//            Log.i("germanVoc", c.getString(germanIndex))
//            Log.i("englishVoc", c.getString(englishIndex))
//            c.moveToNext()
//        }
        //dataInputViewModel.textGermanVocabulary.observe(viewLifecycleOwner) {
        //    editTextVocabularyGerman.text = it
        //}
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
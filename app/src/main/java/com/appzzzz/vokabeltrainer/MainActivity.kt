package com.appzzzz.vokabeltrainer

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.appzzzz.vokabeltrainer.data.Vocabulary
import com.appzzzz.vokabeltrainer.data.VocabularyDict
import com.appzzzz.vokabeltrainer.databinding.ActivityMainBinding
import com.appzzzz.vokabeltrainer.sql.DbHelper
import com.appzzzz.vokabeltrainer.stream.CsvImport

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    public lateinit var sharedPreferences: SharedPreferences

    var vocabularyDict: VocabularyDict? = null
    var vocabularyList : MutableList<Vocabulary>? = null
    //var dbHelper : DbHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()

        vocabularyList = CsvImport.CsvImport.getVocabularyList(assets)
        vocabularyDict = VocabularyDict(vocabularyList!!)

        sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_title), Context.MODE_PRIVATE)

        Log.i("vocabularyList", "$vocabularyList")
    }


    fun setupActionBar() {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_multiple_choice,
                R.id.navigation_spelling,
//                R.id.navigation_voc_list
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
}
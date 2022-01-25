package com.example.dota

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.dota.databinding.ActivityMainBinding
import com.example.dota.fragments.BottomMenuFragment
import com.example.dota.fragments.UpcomingMatches
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var bindingClass: ActivityMainBinding
    private lateinit var matchInfo: MatchInfo
    private val fragmentViewModel: FragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        initMenuFragment(bindingClass.bottomMenuPlaceHolder.id, BottomMenuFragment.newInstance())
        initMenuFragment(bindingClass.mainPagePlaceHolder.id, UpcomingMatches.newInstance())
        bindingClass.button2.setOnClickListener {
            getMatchData()
        }
    }

    private fun getMatchData() {
        CoroutineScope(Dispatchers.Main).launch {
            bindingClass.progressBar.visibility = View.VISIBLE
            try {
                val matchTask = async(Dispatchers.IO) {
                    PageParsing().creatingDataClass()
                }
                matchInfo = matchTask.await()
                bindingClass.progressBar.visibility = View.GONE
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Parsing exception", Toast.LENGTH_SHORT).show()
                bindingClass.progressBar.visibility = View.GONE
            }
        }
    }

    private fun initMenuFragment(placeHolder: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(placeHolder, fragment)
            .commit()
    }
}
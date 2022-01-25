package com.example.dota.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dota.R
import com.example.dota.databinding.FragmentUpcomingMatchesBinding

class UpcomingMatches : Fragment() {

    private lateinit var bindingClass: FragmentUpcomingMatchesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = FragmentUpcomingMatchesBinding.inflate(inflater)
        return bindingClass.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = UpcomingMatches()
    }
}
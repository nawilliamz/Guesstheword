package com.example.android.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.android.navigation.databinding.FragmentTitleBinding


/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentTitleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title,
            container, false)
        //This lambda returns an instance of the navigation controller
        //Thus, when playButton is clicked,
        binding.playButton.setOnClickListener {

                view:View -> Navigation.findNavController(view).navigate(R.id.action_titleFragment_to_gameFragment)
        }
        //this will root the root view of the binding layout
        return binding.root


    }


}
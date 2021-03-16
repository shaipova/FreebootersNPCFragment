package com.example.freebootersnpcfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.freebootersnpcfragment.databinding.FragmentAlingmentBinding

//опечатка в имени класса
class AlingmentFragment : Fragment() {

lateinit var binding : FragmentAlingmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alingment, container, false)

        val npc = AlingmentFragmentArgs.fromBundle(requireArguments()).`object`

        fun radioChecked(): Any {
            val checkedId = binding.radioGroupAlignment.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            return if (-1 != checkedId) {
                when (checkedId) {
                    R.id.radioButtonGood -> npc.prevailingAlignment = "good"
                    R.id.radioButtonLawful -> npc.prevailingAlignment = "lawful"
                    R.id.radioButtonNeutral -> npc.prevailingAlignment = "neutral"
                    R.id.radioButtonChaotic -> npc.prevailingAlignment = "chaotic"
                    R.id.radioButtonEvil -> npc.prevailingAlignment = "evil"
                    else -> npc.prevailingAlignment = "something wrong"
                }
            } else "something wrong"
        }

        binding.buttonSubmit.setOnClickListener { view: View ->
            if (radioChecked() != "something wrong") {
                view.findNavController().navigate(
                        AlingmentFragmentDirections.actionAlingmentFragmentToFinalFragment(npc))
            }
            else Toast.makeText(activity,"choose alignment", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }


}
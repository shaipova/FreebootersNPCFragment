package com.example.freebootersnpcfragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.freebootersnpcfragment.databinding.FragmentFinalBinding

class FinalFragment : Fragment() {

    lateinit var binding: FragmentFinalBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_final, container, false)

        val npc = FinalFragmentArgs.fromBundle(requireArguments()).`object`

        fun addName(view: View) {
            binding.apply {
                npc.NPCname = editTextName.text.toString()
                invalidateAll()
                editTextName.visibility = View.GONE
                textViewName.text = npc.NPCname
                textViewName.visibility = View.VISIBLE
            }

            // hide keybord
                val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0)

        }

        binding.editTextName.setOnClickListener { addName(it) }

        val info = """
            Heritage: ${npc.heritage}
            
            Gender: ${npc.gender}
            
            Alignment: ${npc.alignment}
            
            Occupation: ${npc.occupation}
            
            Motivation: ${npc.motivation}
            
            Virtue: ${npc.virtue}
            
            Vice: ${npc.vice}
        """.trimIndent()

        binding.infobox.text = info

        setHasOptionsMenu(true)

        val sharedPref = this.activity?.getSharedPreferences("myPrefs", 0)
        val editor = sharedPref?.edit()


        binding.createNewButton.setOnClickListener { view: View ->
            if (npc.NPCname == "some name") {
                Toast.makeText(activity,"... and Name?", Toast.LENGTH_SHORT).show()
            } else {
                view.findNavController().navigate(
                        FinalFragmentDirections.actionFinalFragmentToHeritageFragment())
            }
        }

        binding.saveButton.setOnClickListener { view: View ->
            if (npc.NPCname == "some name") {
                Toast.makeText(activity,"... and Name?", Toast.LENGTH_SHORT).show()
            } else {
                // SharedPreferences: key - hashCode, value - info
                editor?.putString(npc.hashCode().toString(), "${npc.NPCname} \n\n$info")
                editor?.apply()
                Toast.makeText(context, "Save", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

}
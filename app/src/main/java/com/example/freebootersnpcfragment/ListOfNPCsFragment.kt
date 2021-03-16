package com.example.freebootersnpcfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.freebootersnpcfragment.databinding.FragmentListOfNPCsBinding

class ListOfNPCsFragment : Fragment() {

    lateinit var binding: FragmentListOfNPCsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_of_n_p_cs, container, false)

        val sharedPref = this.activity?.getSharedPreferences("myPrefs", 0)
        val mapShared: Map<String, *> = sharedPref!!.getAll()

        fun mapToArray(map: Map<String, *>) : ArrayList<String>{
            val array: ArrayList<String> = ArrayList()
            for ((_, value) in map) {
                array.add(value.toString())
            }
            return array;
        }
        val arrayNPC: ArrayList<String> = mapToArray(mapShared)

        val adapter = this.activity?.let { NPCListAdapter(arrayNPC, it) }
        val layoutManager = LinearLayoutManager(this.activity)

        binding.recycleViewId.layoutManager = layoutManager
        binding.recycleViewId.adapter = adapter
        adapter?.notifyDataSetChanged()

        return binding.root
    }


}
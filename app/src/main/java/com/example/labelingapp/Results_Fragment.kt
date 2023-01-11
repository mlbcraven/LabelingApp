package com.example.labelingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labelingapp.databinding.FragmentResultsBinding
import com.example.labelingapp.db.Entry
import com.example.labelingapp.db.EntryDao


class Results_Fragment : Fragment(R.layout.fragment_results) {
    private var _binding: FragmentResultsBinding? = null
    private val binding get() =  _binding!!

    private var adapter: EntryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultsBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        adapter?.notifyDataSetChanged()
        //adapter?.onClickDeleteItem { deleteEntry(it.ID) }
    }




    private fun initRecyclerView() {
        binding.recyclerviewID.layoutManager = LinearLayoutManager(requireActivity() as MainActivity)
        adapter = EntryAdapter()
        binding.recyclerviewID.adapter = adapter
    }


}
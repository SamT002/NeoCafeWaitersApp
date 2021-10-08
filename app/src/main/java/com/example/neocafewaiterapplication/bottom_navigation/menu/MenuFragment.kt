package com.example.neocafewaiterapplication.bottom_navigation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.neocafewaiterapplication.databinding.FragmentMenuBinding
import com.example.neocafewaiterapplication.tools.BaseFragment
import com.example.neocafewaiterapplication.tools.delegates.RecyclerItemClick
import com.example.neocafewaiterapplication.tools.recycler_adapter.MainRecyclerViewAdapter
import com.example.neocafewaiterapplication.tools.sealed_classes.AllModels
import com.example.neocafewaiterapplication.tools.showToast


class   MenuFragment : BaseFragment<FragmentMenuBinding>(), RecyclerItemClick {

    private lateinit var recyclerAdapter: MainRecyclerViewAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(MenuViewModel::class.java) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = MainRecyclerViewAdapter(this)

        binding.recycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recyclerAdapter
        }
        recyclerAdapter.setList(viewModel.getList())

        binding.include.notification.setOnClickListener { findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToNotificationFragment()) }

    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(inflater)
    }

    override fun clickListener(model: AllModels) {
        model as AllModels.Menu
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToAllProductFragment(model.category))

    }
}
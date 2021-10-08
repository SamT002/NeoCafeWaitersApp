package com.example.neocafewaiterapplication.bottom_navigation.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.neocafewaiterapplication.R
import com.example.neocafewaiterapplication.databinding.FragmentOrderBinding
import com.example.neocafewaiterapplication.tools.BaseFragment

class OrderFragment : BaseFragment<FragmentOrderBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user.setOnClickListener { findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToUserFragment()) }
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrderBinding {
        return FragmentOrderBinding.inflate(inflater)
    }

}
package com.example.neocafewaiterapplication.bottom_navigation.menu.all_product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafewaiterapplication.R
import com.example.neocafewaiterapplication.databinding.FragmentAllProductBinding
import com.example.neocafewaiterapplication.tools.BaseFragment
import com.example.neocafewaiterapplication.tools.recycler_adapter.AllProductsRecyclerAdapter
import com.example.neocafewaiterapplication.tools.sealed_classes.AllModels

class AllProductFragment : BaseFragment<FragmentAllProductBinding>() {

    private val recyclerAdapter by lazy {AllProductsRecyclerAdapter()}
    private val args:AllProductFragmentArgs by navArgs()
    private val viewModel by lazy {ViewModelProvider(this).get(AllProductViewModel::class.java)}

    private val mapOfCategory = mutableMapOf<String, Int>(
        "Выпечка" to R.id.bakery, "Кофе" to R.id.coffee, "Чай" to R.id.tea,
        "Напитки" to R.id.drinks, "Десерты" to R.id.desserts
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            recycler.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = recyclerAdapter
            }

            include.notification.setOnClickListener { findNavController().navigate(AllProductFragmentDirections.actionAllProductFragmentToNotificationFragment()) }

            val viewId = mapOfCategory[args.category] // срабатывает при передачи категории
            if (viewId != null) {
                binding.chipGroup.check(viewId) // enable данной категории
                recyclerSetList(viewId)
            }

            binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
                recyclerSetList(checkedId) //слушатель изменений chip
            }
        }


    }

    private fun recyclerSetList(checkedId: Int) {
        val checkedText = when (checkedId) { // по его id я достаю его имя
            R.id.bakery -> "Выпечка"
            R.id.coffee -> "Кофе"
            R.id.tea -> "Чай"
            R.id.drinks -> "Напитки"
            R.id.desserts -> "Десерты"
            else -> ""
        }
        viewModel.getList().observe(viewLifecycleOwner, {
            viewModel.sort(checkedText, it as MutableList<AllModels.Product>)
            recyclerAdapter.setList(viewModel.getSortedList(), checkedText)
        })
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAllProductBinding {
        return FragmentAllProductBinding.inflate(inflater)
    }


}
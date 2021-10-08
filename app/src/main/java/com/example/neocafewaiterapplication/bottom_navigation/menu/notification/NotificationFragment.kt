package com.example.neocafewaiterapplication.bottom_navigation.menu.notification

import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neocafewaiterapplication.R
import com.example.neocafewaiterapplication.databinding.FragmentNotificationBinding
import com.example.neocafewaiterapplication.tools.BaseFragment
import com.example.neocafewaiterapplication.tools.Consts
import com.example.neocafewaiterapplication.tools.recycler_adapter.MainRecyclerViewAdapter
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class NotificationFragment : BaseFragment<FragmentNotificationBinding>() {

    private val recyclerAdapter by lazy {MainRecyclerViewAdapter(null)}
    private val viewModel by lazy {ViewModelProvider(this).get(NotificationViewModel::class.java)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            backIcon.setOnClickListener { findNavController().navigateUp() }
            setUpRecycler()
            setUpSwipeCallback()
        }


    }

    private fun setUpRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recyclerAdapter
        }

        recyclerAdapter.setList(viewModel.getList())
    }

    private fun setUpSwipeCallback() {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean,
            ) {
                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(Color.parseColor(Consts.RED))
                    .addActionIcon(R.drawable.ic_trash)
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )

            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        Toast.makeText(requireContext(), "$position", Toast.LENGTH_LONG).show()
                        viewModel.getList().removeAt(position)
                        recyclerAdapter.notifyItemRemoved(position)

                    }
                }
            }


        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recycler)
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotificationBinding {
        return FragmentNotificationBinding.inflate(inflater)
    }

}
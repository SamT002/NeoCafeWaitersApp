package com.example.neocafewaiterapplication.bottom_navigation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.neocafewaiterapplication.databinding.FragmentMenuBinding
import com.example.neocafewaiterapplication.tools.BaseFragment
import com.example.neocafewaiterapplication.tools.delegates.RecyclerItemClick
import com.example.neocafewaiterapplication.tools.recycler_adapter.MainRecyclerViewAdapter
import com.example.neocafewaiterapplication.tools.sealed_classes.AllModels


class MenuFragment : BaseFragment<FragmentMenuBinding>(), RecyclerItemClick {
    //делай объект наллабл , состояние null в объекто это норма
    //название адаптера давай по тому какой это список
    private lateinit var recyclerAdapter: MainRecyclerViewAdapter
    //зачем тебе  этот делегат , он нужен когда тебе нужна вьюмодель родительской актвивити
    private val viewModel: MenuViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAppBar()
        //ленивая инициализация
        recyclerAdapter = MainRecyclerViewAdapter(this)

        binding.recycler.apply {
            //layoutManager можно задать в прямо в xml , уменьшай код по возможности
            //магическое число 2
            //используй констансты и именновые аргументы
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recyclerAdapter
        }
        recyclerAdapter.setList(viewModel.getList())


    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(inflater)
    }

    override fun clickListener(model: AllModels) {
        //используй смарткаст , явный каст череват ошибками в рантайме
        model as AllModels.Menu
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToAllProductFragment(model.category)
        )

    }

    override fun setUpAppBar() {
        //про скопы:with возвращает результат> apply - объект
        with(binding.include){
            notification.setOnClickListener { findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToNotificationFragment()) }
            binding.include.title.text = "Меню" //ресурсники
            user.setOnClickListener { findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToUserFragment3()) } //нормальныйе айдишники задавай ресурникам
        }
    }
}
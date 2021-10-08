package com.example.neocafewaiterapplication.splash

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.neocafewaiterapplication.databinding.FragmentSplashBinding
import com.example.neocafewaiterapplication.tools.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater)
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            nextFragment()
        }, 1500)
    }

    private fun nextFragment(){
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToRegisterNumberFragment())
//        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToBottomNavigationFragment())
    }
}
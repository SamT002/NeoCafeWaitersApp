package com.example.neocafewaiterapplication.registration

import `in`.aabhasjindal.otptextview.OTPListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.neocafewaiterapplication.R
import com.example.neocafewaiterapplication.databinding.FragmentOTPBinding
import com.example.neocafewaiterapplication.tools.BaseFragment

class OTPFragment : BaseFragment<FragmentOTPBinding>() {

    private val args:OTPFragmentArgs by navArgs()

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentOTPBinding {
        return FragmentOTPBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi()

        binding.otp.otpListener = object : OTPListener{
            override fun onInteractionListener() {
                binding.confirm.apply {
                    isEnabled = false
                    background = ContextCompat.getDrawable(requireContext(), R.drawable.button_disable_custom_item)
                }
                binding.typeCode.apply {
                    text = resources.getText(R.string.input_code)
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }
            }

            override fun onOTPComplete(otp: String) {
                if (otp == "4545"){
                    binding.confirm.apply {
                        isEnabled = true
                        background = ContextCompat.getDrawable(requireContext(), R.drawable.button_enable_custom_style)
                    }

                }else{
                    binding.typeCode.apply{
                        text = resources.getText(R.string.wrong_code)
                        setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                    }

                }
            }

        }

    }

    @SuppressLint("SetTextI18n")
    private fun setUpUi() {
        with(binding){
            alertMessage.text = "Введите код который дал администратор по номеру ${args.phoneNumber}"
            backIcon.setOnClickListener { findNavController().navigateUp() }
            confirm.setOnClickListener { findNavController().navigate(OTPFragmentDirections.actionOTPFragmentToRegisterUserFragment()) }
        }
    }
}
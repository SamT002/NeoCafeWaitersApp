package com.example.neocafewaiterapplication.bottom_navigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.neocafewaiterapplication.R
import com.example.neocafewaiterapplication.databinding.FragmentUserBinding
import com.example.neocafewaiterapplication.tools.BaseFragment
import com.example.neocafewaiterapplication.tools.Consts
import com.example.neocafewaiterapplication.tools.alert_dialog.DoneCustomAlertDialog

class UserFragment : BaseFragment<FragmentUserBinding>() {

    private var isActiv = false

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): FragmentUserBinding {
        return FragmentUserBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding) {
            name.apply {
                isFocusable = false
            }
            surname.apply {
                isFocusable = false
            }
            rating.text = "#10"
            save.setOnClickListener {
                if (!isActiv) {
                    name.isFocusableInTouchMode = true
                    surname.isFocusableInTouchMode = true
                    isActiv = true
                    save.setImageResource(R.drawable.ic_done)

                } else {
                    isActiv = false
                    save.setImageResource(R.drawable.ic_pencil)
                    name.isFocusable = false
                    surname.isFocusable = false
                    insertDataToSharedPreference(name.text.toString(), surname.text.toString())
                    DoneCustomAlertDialog().show(childFragmentManager, "TAG")
                }
            }

        }
        setUpUi()


    }


    private fun setUpUi() {
        with(binding) {


            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            val userName = sharedPref.getString(Consts.NAME, "null")
            val userSurname = sharedPref.getString(Consts.SURNAME, "null")
            val numberPhone = sharedPref.getString(Consts.PHONE_NUMBER, "null")
            val userBirthday = sharedPref.getString(Consts.BIRTHDAY, "null")

            name.setText(userName)
            surname.setText(userSurname)
            number.text = numberPhone
            birthday.text = userBirthday


            backIcon.setOnClickListener { findNavController().navigateUp() }

        }

    }


    @SuppressLint("CommitPrefEdits")
    private fun insertDataToSharedPreference(name: String, surname: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(Consts.NAME, name)
            putString(Consts.SURNAME, surname)
        }.apply()
    }
}
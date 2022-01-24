package com.maku.interviewandroidtest.common.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.maku.interviewandroidtest.R

// TODO: totally redundant class for a small app... should totally remove it
open class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    // validations
    /**
     * field must not be empty
     */
    fun validateField(text: String, t: TextInputLayout, te: TextInputEditText): Boolean {
        if (text.trim().isEmpty()) {
            t.error = "Required Field!"
            te.requestFocus()
            return false
        } else {
            t.isErrorEnabled = true
        }
        return true
    }
}
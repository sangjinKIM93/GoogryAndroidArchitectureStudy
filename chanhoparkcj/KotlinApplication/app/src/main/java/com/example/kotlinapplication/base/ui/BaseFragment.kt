package com.example.kotlinapplication.base.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.kotlinapplication.R

abstract class BaseFragment(
    @LayoutRes layoutResId: Int
) : Fragment(layoutResId) {

    fun toast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun webLink(message: String) {
        val uri: Uri = Uri.parse(message)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
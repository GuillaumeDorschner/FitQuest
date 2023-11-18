package com.example.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HeaderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_header, container, false)
    }

    fun setUserImage(url: String) {
        //val imageView = view?.findViewById<ImageView>(R.id.ivUserProfile)
        // Glide.with(this)
        //    .load(url)
        //    .into(imageView)
    }
}

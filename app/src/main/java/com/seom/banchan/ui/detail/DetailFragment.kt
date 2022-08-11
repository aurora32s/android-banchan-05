package com.seom.banchan.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.seom.banchan.databinding.FragmentDetailBinding

class DetailFragment(
    private val menuId: String
) : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding?.root
    }

    companion object {
        const val TAG = ".DetailFragment"
        fun newInstance(menuId: String) = DetailFragment(menuId)
    }
}
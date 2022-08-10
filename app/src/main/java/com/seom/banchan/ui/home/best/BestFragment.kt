package com.seom.banchan.ui.home.best

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seom.banchan.R
import com.seom.banchan.databinding.FragmentBestBinding
import com.seom.banchan.ui.adapter.HomeAdapter
import com.seom.banchan.ui.model.home.UiModel

class BestFragment : Fragment() {
    private var _binding: FragmentBestBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBestBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHeader()
        initRecyclerView()
    }

    private fun initHeader() = binding?.let {
        it.tvHeader.text = getString(R.string.header_best)
        it.tvChip.text = getString(R.string.tab_best)
    }

    private fun initRecyclerView() = binding?.let {
        it.rvBest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val homeAdapter = HomeAdapter()
        it.rvBest.adapter = homeAdapter

        homeAdapter.submitList(
            (1..10).map {
                UiModel.CategoryTitle(title = "$it category")
            }
        )
    }

    companion object {
        const val TAG = ".BestFragment"
        fun newInstance() = BestFragment()
    }

}
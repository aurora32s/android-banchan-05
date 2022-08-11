package com.seom.banchan.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.seom.banchan.R
import com.seom.banchan.databinding.ActivityMainBinding
import com.seom.banchan.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this,R.layout.activity_main
        )

        binding?.lifecycleOwner = this

        initToolbar()
        initContainer()
    }

    private fun initToolbar() = binding?.let {
        setSupportActionBar(it.tbMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        it.tbMain.title = getString(R.string.main_title)
    }

    private fun initContainer() = binding?.let {
        supportFragmentManager.beginTransaction().add(it.flMain.id,HomeFragment.newInstance(),HomeFragment.TAG).commit()
    }
}
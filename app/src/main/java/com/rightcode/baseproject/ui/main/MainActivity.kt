package com.rightcode.baseproject.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rightcode.basekProject.ui.BaseActivity
import com.rightcode.baseproject.R
import com.rightcode.baseproject.databinding.ActivityMainBinding
import com.rightcode.baseproject.ui.component.CustomToolbar
import com.rightcode.baseproject.ui.component.imagePicker.ImagePickerActivity
import com.rightcode.baseproject.ui.component.imagePicker.model.Image
import com.rightcode.baseproject.ui.component.imagePicker.model.ImagePickerConfig
import com.rightcode.baseproject.ui.component.imagePicker.model.ImagePickerConfig.Companion.EXTRA_CONFIG
import com.rightcode.baseproject.util.extension.LogD
import com.rightcode.baseproject.util.extension.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel: MainViewModel by viewModels()
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewDataBinding.root)

        if (savedInstanceState == null) {
            initialize()
            setUpBottomNavigationBar()
        }


    }

    override fun initialize() {
        super.initialize()
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.viewModel = mViewModel
        mViewModel.insertUser.observe(this, Observer {
            println("Tag" + it)
        })

        mViewDataBinding.customToolbar.setOnClickListener(object :
            CustomToolbar.OnCustomToolbarListener {
            override fun onClickBack() {
                println("back")
            }

            override fun onClickSearch() {
                println("search")
            }

            override fun onClickCart() {
                println("cart")
            }

            override fun onClickSetting() {
                println("setting")
            }
        })
    }


//    override fun onBackPressed() {
//        MaterialDialog.Builder(this)
//            .setTitle(getString(R.string.exit_dialog_title))
//            .setMessage(getString(R.string.exit_dialog_message))
//            .setPositiveButton(getString(R.string.option_yes)) { dialogInterface, _ ->
//                dialogInterface.dismiss()
//                super.onBackPressed()
//            }
//            .setNegativeButton(getString(R.string.option_no)) { dialogInterface, _ ->
//                dialogInterface.dismiss()
//            }
//            .build()
//            .show()
//    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpBottomNavigationBar()
    }

    override fun getDataViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    private fun setUpBottomNavigationBar() {
        println("setUpBottomNavigationBar")

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navGraphIds = listOf(
            R.navigation.navigation_home,
            R.navigation.navigation_hot,
            R.navigation.navigation_list
        )
        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }
}
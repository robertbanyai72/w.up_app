package com.robert.banyai.wup.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.robert.banyai.wup.R
import com.robert.banyai.wup.presentation.base.BaseActivity
import com.robert.banyai.wup.presentation.base.ToolbarListener
import com.robert.banyai.wup.presentation.base.ToolbarModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>(),
    ToolbarListener {

    companion object {
        private const val bottomNavigationStatementsTabIndex = 2
    }

    override fun viewModelClass(): Class<out MainViewModel> {
        return MainViewModel::class.java
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = ""
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment

        NavigationUI.setupWithNavController(
            bottomNavigationView,
            navHostFragment.navController
        )

        inflateBadge()
    }

    override fun updateToolbarTitle(toolbarModel: ToolbarModel) {
        textViewToolbarTitle.text = toolbarModel.title
        textViewToolbarTitle.gravity = toolbarModel.gravity

        val layoutParams = textViewToolbarTitle.layoutParams as Toolbar.LayoutParams
        layoutParams.gravity = toolbarModel.gravity
        textViewToolbarTitle.layoutParams = layoutParams

        changeBackButtonVisibility(toolbarModel.backButtonVisibility)
    }

    private fun inflateBadge() {
        val v = (bottomNavigationView.getChildAt(0) as BottomNavigationMenuView).getChildAt(
            bottomNavigationStatementsTabIndex
        )

        LayoutInflater.from(this)
            .inflate(R.layout.view_badge, v as BottomNavigationItemView, true)
    }
}
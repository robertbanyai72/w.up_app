package com.robert.banyai.wup.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.robert.banyai.wup.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : DaggerAppCompatActivity() {

    abstract fun viewModelClass(): Class<out VM>

    @LayoutRes
    abstract fun layoutId(): Int

    @Inject
    lateinit var viewModelFactory: InjectingSavedStateViewModelFactory

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())

        viewModel =
            ViewModelProvider(this, viewModelFactory.create(this, bundleOf()))[viewModelClass()]
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    protected fun changeBackButtonVisibility(show: Boolean) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(show)
            setDisplayShowHomeEnabled(show)

            if (show)
                setHomeAsUpIndicator(R.drawable.ic_navigation_back)
        }
    }
}
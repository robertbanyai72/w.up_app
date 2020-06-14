package com.robert.banyai.wup.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : DaggerFragment() {

    abstract fun viewModelClass(): Class<out VM>

    @LayoutRes
    abstract fun layoutId(): Int

    @Inject
    lateinit var viewModelFactory: InjectingSavedStateViewModelFactory

    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel =
            ViewModelProvider(this, viewModelFactory.create(this, bundleOf()))[viewModelClass()]

        viewModel.errorStream.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        return inflater.inflate(layoutId(), container, false)
    }
}
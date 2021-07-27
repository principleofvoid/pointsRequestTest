package com.example.pointsrequesttest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pointsrequesttest.databinding.FragmentStartBinding
import javax.inject.Inject

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: StartPresenter

    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        binding.buttonStart.setOnClickListener {
            presenter.initGraph(binding.editTextPointsCount.text?.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        _binding = null
    }

    fun goToGraph(pointsCount: Int) {
        binding.layoutPointsCount.isErrorEnabled = false

        findNavController().navigate(
            StartFragmentDirections.actionStartFragmentToGraphFragment(pointsCount)
        )
    }

    fun showCountError(errorMessageId: Int) {
        with(binding.layoutPointsCount) {
            isErrorEnabled = true
            error = context.getString(errorMessageId)
        }
    }
}
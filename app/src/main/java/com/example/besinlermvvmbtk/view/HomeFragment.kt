package com.example.besinlermvvmbtk.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.example.besinlermvvmbtk.R
import com.example.besinlermvvmbtk.adapters.BesinAdapter
import com.example.besinlermvvmbtk.databinding.FragmentHomeBinding
import com.example.besinlermvvmbtk.vieModel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private val viewModel by lazy { HomeViewModel(requireActivity().application) }
    private val adapter=BesinAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,): View? {
        _binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            rvHome.setHasFixedSize(true)
            rvHome.adapter=adapter
            reflayout.setOnRefreshListener {
                rvHome.visibility=View.GONE
                viewModel.refreshLayout()
                reflayout.isRefreshing=false
            }
        }
        viewModel.refreshData()
        observeLiveData()
    }

    private fun observeLiveData(){
        with(binding){
            with(viewModel){
                besinler.observe(viewLifecycleOwner,{
                    it.let { liste->
                        adapter.updateList(liste)
                        rvHome.visibility=View.VISIBLE
                    }
                })

                besinLoading.observe(viewLifecycleOwner,{
                    if (it){
                        rvHome.visibility=View.GONE
                        progressHome.visibility=View.VISIBLE
                    }else{
                        rvHome.visibility=View.VISIBLE
                        progressHome.visibility=View.GONE
                    }
                })

                besinError.observe(viewLifecycleOwner,{
                    if (it){
                        rvHome.visibility=View.GONE
                        textViewHata.visibility=View.VISIBLE
                    }else{
                        rvHome.visibility=View.VISIBLE
                        textViewHata.visibility=View.GONE
                    }
                })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}
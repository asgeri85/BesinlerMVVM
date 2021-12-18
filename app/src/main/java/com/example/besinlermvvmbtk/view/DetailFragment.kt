package com.example.besinlermvvmbtk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.besinlermvvmbtk.R
import com.example.besinlermvvmbtk.databinding.FragmentDetailBinding
import com.example.besinlermvvmbtk.utils.downloadAPI
import com.example.besinlermvvmbtk.utils.plcProgresbar
import com.example.besinlermvvmbtk.vieModel.DetailViewModel

class DetailFragment : Fragment() {
    private var _binding:FragmentDetailBinding?=null
    private val binding get() = _binding!!
    private val navargs:DetailFragmentArgs by navArgs()
    private val detayModel by lazy { DetailViewModel(requireActivity().application) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding= FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detayModel.getDataRoom(navargs.id)
        observeLiveData()
    }

    private fun observeLiveData(){
        detayModel.besinLiveData.observe(viewLifecycleOwner,{besin->
            besin.let { data->
                binding.detay=data
                context?.let {
                    binding.imageViewDetail.downloadAPI(data.gorsel, plcProgresbar(it))
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}
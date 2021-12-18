package com.example.besinlermvvmbtk.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.besinlermvvmbtk.R
import com.example.besinlermvvmbtk.databinding.CardBesinBinding
import com.example.besinlermvvmbtk.model.Besin
import com.example.besinlermvvmbtk.utils.downloadAPI
import com.example.besinlermvvmbtk.utils.plcProgresbar
import com.example.besinlermvvmbtk.view.HomeFragmentDirections

class BesinAdapter(var besinler:ArrayList<Besin>):RecyclerView.Adapter<BesinAdapter.BesinHolder>() {
    inner class BesinHolder(var cardBesinBinding: CardBesinBinding):RecyclerView.ViewHolder(cardBesinBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinHolder {
        val tasrim=DataBindingUtil.inflate<CardBesinBinding>(LayoutInflater.from(parent.context), R.layout.card_besin,
        parent,false)
        return BesinHolder(tasrim)
    }

    override fun onBindViewHolder(holder: BesinHolder, position: Int) {
        val besin=besinler[position]
        holder.cardBesinBinding.besin=besin
        holder.cardBesinBinding.imageView.downloadAPI(besin.gorsel, plcProgresbar(holder.itemView.context))
        holder.cardBesinBinding.card.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(besin.uuid))
        }
    }

    override fun getItemCount(): Int {
        return besinler.size
    }

    fun updateList(list:List<Besin>){
        besinler.clear()
        besinler.addAll(list)
        notifyDataSetChanged()
    }
}
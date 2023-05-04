package com.example.test.apodlist.adaptor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.ApodData
import com.example.test.databinding.ApodItemBinding

class ApodListItemRecyclerViewAdapter(private var apodList: List<ApodData>) : RecyclerView.Adapter<ApodListItemRecyclerViewAdapter.ApodItemViewHolder>() {

    inner class ApodItemViewHolder(val binding: ApodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(apod: ApodData) {
            binding.apod = apod
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ApodItemBinding.inflate(layoutInflater, parent, false)
        return ApodItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApodItemViewHolder, position: Int) {
        val apod = apodList[position]
        holder.bind(apod)

        Glide.with(holder.itemView)
            .load(apod.url) // or apod.url
            .centerCrop()
            .into(holder.binding.apodImage)
    }

    override fun getItemCount() = apodList.size


    fun setApodList(apodList: List<ApodData>, clearPrevious: Boolean = true) {
        if (clearPrevious) {
            this.apodList = apodList
        } else {
            this.apodList = apodList + this.apodList
        }
        notifyDataSetChanged()
    }





}








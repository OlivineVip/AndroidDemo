package com.olivine.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.olivine.demo.databinding.RvItemTestBinding

class TestAdapter : RecyclerView.Adapter<TestAdapter.MyViewHolder>() {

    private val mData = mutableListOf<TestBean>()

    fun setData(list: List<TestBean>) {
        mData.clear()
        mData.addAll(list)
        notifyDataSetChanged()
    }


    inner class MyViewHolder(val bindings: RvItemTestBinding) : ViewHolder(bindings.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bindings = RvItemTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(bindings)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean = mData[position]
        holder.bindings.tvTitle.text = bean.title
        holder.bindings.root.setOnClickListener {
            bean.onCLick.invoke()
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}
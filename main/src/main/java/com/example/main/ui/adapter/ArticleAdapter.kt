package com.example.main.ui.adapter

import android.content.Context
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.main.R
import com.example.main.model.entity.ArticleEntity

class ArticleAdapter: RecyclerView.Adapter<ArticleAdapter.MyViewHolder>(){

    private val list: MutableList<ArticleEntity.ItemsBean> = mutableListOf()
    private lateinit var binding: ViewDataBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_item_article, parent, false) as ViewDataBinding
        return MyViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val binding = DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView) as ViewDataBinding
        val ret = binding.setVariable(BR.articleItem, list[position])
        Log.e("tag","${ret}")
        binding.executePendingBindings()
    }


    fun refresh(list: List<ArticleEntity.ItemsBean>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }



    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
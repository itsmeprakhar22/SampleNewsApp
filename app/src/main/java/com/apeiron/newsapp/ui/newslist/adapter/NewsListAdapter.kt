package com.apeiron.newsapp.ui.newslist.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.apeiron.newsapp.R
import com.apeiron.newsapp.data.model.Article
import com.apeiron.newsapp.data.model.News
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsListAdapter(private val newsList: ArrayList<Article>):
    RecyclerView.Adapter<NewsListAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(news: Article){
            itemView.textViewTitle.text = news.title
            itemView.textViewDescription.text = news.description
            itemView.textViewSource.text = news.source.name
            Glide.with(itemView.imageViewBanner.context)
                .load(news.urlToImage).into(itemView.imageViewBanner)
            itemView.setOnClickListener {
                val builder =  CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(news.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent, false)))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    fun addData(list: List<Article>){
        newsList.addAll(list)
    }
}
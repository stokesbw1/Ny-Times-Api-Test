package com.stokes.newsapp.adapters

import android.R.string
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stokes.newsapp.R
import com.stokes.newsapp.data.models.Result


class PopularNewsAdapter : RecyclerView.Adapter<PopularNewsAdapter.ResultViewHolder>() {
    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = differ.currentList[position]
        holder.itemView.apply {

            Glide.with(this).load(getImageUrl(result)).into(findViewById(R.id.article_image))
            findViewById<TextView>(R.id.article_title).text = result?.title
            findViewById<TextView>(R.id.article_authour).text = result?.byline
            findViewById<TextView>(R.id.article_publish_date).text = result?.published_date

            setOnClickListener {
                onItemClickListener?.let {
                    it(result)
                }
            }
        }
    }


    /*fun formatTitle(stringToFormat: String): String? {
        var tempStringVal: String = stringToFormat
        if (stringToFormat.count() > 50) {
            tempStringVal = ""
            for (j in 0..48) {
                tempStringVal += stringToFormat[j].toString()
                if (j == 48) tempStringVal += "..."
            }
        }
        return tempStringVal
    }*/

    fun getImageUrl(result: Result): String {
        var imageUrl = ""
        result.media?.let { mediaList ->
            var height = 0
            if (mediaList.isEmpty())
                return imageUrl

            for (meta in mediaList[0].`media-metadata`) {                               // 1
                if (meta.height > height) {
                    height = meta.height
                    imageUrl = meta.url
                }
            }
        }

        return imageUrl
    }

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
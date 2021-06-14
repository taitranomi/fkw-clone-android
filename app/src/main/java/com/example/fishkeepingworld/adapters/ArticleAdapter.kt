package com.example.fishkeepingworld.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fishkeepingworld.R
import com.example.fishkeepingworld.model.ArticlePreviewingModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_card.view.*


class ArticleAdapter (private val mArticles: List<ArticlePreviewingModel>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val articletView = inflater.inflate(R.layout.article_card, parent, false)
        // Return a new holder instance
        return ViewHolder(articletView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val article: ArticlePreviewingModel = mArticles.get(position)
        Log.d(TAG, "Positions: " + position)
        // set Image
        Picasso.get().load(article.image).into(viewHolder.ivVariable);
        // set Title
        viewHolder.tvTitleVariable.text = article.title
        // set Preheader
        viewHolder.tvPreheaderVariable.text = article.preHeader
    }

    override fun getItemCount(): Int {
        return mArticles.size
    }


    class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView){
        val ivVariable = listItemView.ivArticle
        val tvTitleVariable = listItemView.tvTitle
        val tvPreheaderVariable = listItemView.tvPreheader
    }
}
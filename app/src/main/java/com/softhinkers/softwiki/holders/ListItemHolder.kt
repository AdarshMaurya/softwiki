package com.softhinkers.softwiki.holders

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.softhinkers.softwiki.R
import com.softhinkers.softwiki.activities.ArticleDetailActivity
import com.softhinkers.softwiki.models.WikiPage
import com.squareup.picasso.Picasso

/**
 * Created by adarshmaurya on 28/10/18.
 * www.softhinkers.com all rights reserved
 */
class ListItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


    private val articleImageView: ImageView = itemView.findViewById<ImageView>(R.id.result_icon)
    private val titleTextView: TextView = itemView.findViewById<TextView>(R.id.result_title)
    private var currentPage: WikiPage? = null

    init {
        itemView.setOnClickListener { view: View? ->
            var detailedPageIntent = Intent(itemView.context, ArticleDetailActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailedPageIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailedPageIntent)
        }
    }

        fun updateWithPage(page: WikiPage) {
            if (page.thumbnail != null)
                Picasso.get().load(page.thumbnail!!.source).into(articleImageView)

            titleTextView.text = page.title

            currentPage = page
        }
    }

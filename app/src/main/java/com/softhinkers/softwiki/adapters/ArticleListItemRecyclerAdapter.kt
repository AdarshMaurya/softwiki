package com.softhinkers.softwiki.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.softhinkers.softwiki.R
import com.softhinkers.softwiki.holders.ListItemHolder
import com.softhinkers.softwiki.models.WikiPage

/**
 * Created by adarshmaurya on 28/10/18.
 * www.softhinkers.com all rights reserved
 */
class ArticleListItemRecyclerAdapter() : RecyclerView.Adapter<ListItemHolder>() {
    val currentResults: ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun getItemCount(): Int {
        return currentResults.size//temporary
    }

    override fun onBindViewHolder(p0: ListItemHolder, p1: Int) {
        //this is where we will update our view
        var page = currentResults[p1]
        p0?.updateWithPage(page)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListItemHolder {
        var cardItem = LayoutInflater.from(p0?.context).inflate(R.layout.article_list_item,p0,false)
        return ListItemHolder(cardItem)
    }

}
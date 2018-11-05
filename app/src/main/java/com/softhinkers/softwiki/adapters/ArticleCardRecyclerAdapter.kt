package com.softhinkers.softwiki.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.softhinkers.softwiki.R
import com.softhinkers.softwiki.holders.CardHolder
import com.softhinkers.softwiki.models.WikiPage

/**
 * Created by adarshmaurya on 28/10/18.
 * www.softhinkers.com all rights reserved
 */
class ArticleCardRecyclerAdapter() : RecyclerView.Adapter<CardHolder>() {

    var currentResults: ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun getItemCount(): Int {
        return currentResults.size//temporary
    }

    override fun onBindViewHolder(p0: CardHolder, p1: Int) {
        //this is where we will update our view
        var page =currentResults[p1]
        // update view within holder
        p0?.updateWithPage(page)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardHolder {
        var cardItem = LayoutInflater.from(p0.context).inflate(R.layout.article_card_item,p0,false)
        return CardHolder(cardItem)
    }

}
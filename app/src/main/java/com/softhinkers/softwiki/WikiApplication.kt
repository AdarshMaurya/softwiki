package com.softhinkers.softwiki

import android.app.Application
import com.softhinkers.softwiki.managers.WikiManager
import com.softhinkers.softwiki.providers.ArticleDataProvider
import com.softhinkers.softwiki.repositories.ArticleDatabaseOpenHelper
import com.softhinkers.softwiki.repositories.FavoritesRepository
import com.softhinkers.softwiki.repositories.HistoryRepository

/**
 * Created by adarshmaurya on 03/11/18.
 * www.softhinkers.com all rights reserved
 */
class WikiApplication: Application() {
    private var dbHelper: ArticleDatabaseOpenHelper? = null
    private var favoritesRepository: FavoritesRepository? = null
    private var historyRepository: HistoryRepository? = null
    private var wikiProvider: ArticleDataProvider? = null
    var wikiManager: WikiManager? = null
        private set

    override fun onCreate(){
        super.onCreate()

        dbHelper = ArticleDatabaseOpenHelper(applicationContext)
        favoritesRepository = FavoritesRepository(dbHelper!!)
        historyRepository = HistoryRepository(dbHelper!!)
        wikiProvider = ArticleDataProvider()

        wikiManager = WikiManager(wikiProvider!!, favoritesRepository!!, historyRepository!!)
    }
}
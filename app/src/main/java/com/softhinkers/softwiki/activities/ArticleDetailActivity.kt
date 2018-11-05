package com.softhinkers.softwiki.activities

import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.gson.Gson
import com.softhinkers.softwiki.R
import com.softhinkers.softwiki.WikiApplication
import com.softhinkers.softwiki.managers.WikiManager
import com.softhinkers.softwiki.models.WikiPage
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.jetbrains.anko.toast
import java.lang.Exception

/**
 * Created by adarshmaurya on 26/10/18.
 * softhinker.com all rights reserved
 */
class ArticleDetailActivity : AppCompatActivity() {

    private var wikiManager: WikiManager? = null
    private var currentPage: WikiPage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        wikiManager = (applicationContext as WikiApplication).wikiManager

        setSupportActionBar(toolbar);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        // get the page from the extras
        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson<WikiPage>(wikiPageJson, WikiPage::class.java)

        supportActionBar?.title = currentPage?.title

        article_detail_webview!!.settings.javaScriptEnabled = true

        article_detail_webview?.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return true
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                toast("Error occured " + error?.description.toString() + " " + error?.errorCode.toString())
                view!!.loadData(
                    "<html><body> ERROR OCCURED: ${error?.description.toString()} </br>${error?.errorCode.toString()}</body></html>",
                    "text/html",
                    "UTF-8"
                )
            }

        }
        var url = currentPage!!.fullurl
        //var url ="https://www.wikipedia.org/wiki/Test"
        article_detail_webview.loadUrl(url)

        wikiManager?.addHistory(currentPage!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
        } else if (item!!.itemId == R.id.action_favorite) {
            try {
                // determine if article is already a favorite or not
                if (wikiManager!!.getIsFavorite(currentPage!!.pageid!!)) {
                    wikiManager!!.removeFavorite(currentPage!!.pageid!!)
                    toast("Article removed from favorites")
                } else {
                    wikiManager!!.addFavorite(currentPage!!)
                    toast("Article added to favorites")
                }
            } catch (ex: Exception) {
                toast("Unable to update this article.")
            }
        }
        return true;
    }
}
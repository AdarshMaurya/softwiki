package com.softhinkers.softwiki.providers

import android.app.AlertDialog
import android.support.v4.app.FragmentActivity
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.softhinkers.softwiki.models.Urls
import com.softhinkers.softwiki.models.WikiResult
import java.io.Reader
import java.lang.Exception

/**
 * Created by adarshmaurya on 29/10/18.
 * www.softhinkers.com all rights reserved
 */
class ArticleDataProvider {

    //set user=agent header
    init {
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "Softhinkers Softwiki")
    }

    fun search(term: String, skip: Int, take: Int, responseHandler:(result: WikiResult) -> Unit? ) {
        Urls.getSearchUrl(term, skip, take).httpGet().
            responseObject(WikipediaDataDeserializer()) { _, response, result ->
                //do something with result
                if(response.statusCode!=200){
                    throw Exception("Unable to get articles")
                }
                val(data, _) = result
                responseHandler.invoke(data as WikiResult)
        }
    }

    fun getRandom(
        take: Int,
        responseHandler: (result: WikiResult) -> Unit?

    ){
        Urls.getRandomUrl(take).httpGet().
            responseObject(WikipediaDataDeserializer()){_, response, result ->
                if(response.statusCode!=200){
                    throw Exception("Unable to get articles")
                }
                val(data, error) = result
                responseHandler.invoke(data as WikiResult)


        }
    }

    class WikipediaDataDeserializer : ResponseDeserializable<WikiResult> {
        override fun deserialize(reader: Reader) = Gson().fromJson(reader, WikiResult::class.java)

    }
}
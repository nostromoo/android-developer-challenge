package com.streetbees.marvel.api

import com.google.gson.GsonBuilder
import com.streetbees.marvel.BuildConfig
import com.streetbees.marvel.bo.ComicsWrapper
import com.streetbees.marvel.utils.md5
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Romain on 26/08/2018.
 */

class ComicsServices{

  companion object
  {
    private var marvelAPI: MarvelApi? = null

    init
    {
      val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create()
      val retrofit = Retrofit.Builder()
          .baseUrl(BuildConfig.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build()

      marvelAPI = retrofit?.create(MarvelApi::class.java)
    }

    fun getComics() : Observable<ComicsWrapper>?
    {
      val ts = System.currentTimeMillis().toString()
      return marvelAPI?.getMarvelComics(ts = ts, hash ="$ts${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}".md5())
    }
  }
}

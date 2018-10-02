package com.streetbees.marvel.api

import com.streetbees.marvel.BuildConfig
import com.streetbees.marvel.bo.ComicsWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Romain on 26/08/2018.
 */
interface MarvelApi {

  @GET("v1/public/comics")
  fun getMarvelComics(@Query("ts")            ts : String,
                      @Query("apikey")        apikey : String = BuildConfig.PUBLIC_KEY,
                      @Query("hash")          hash : String,
                      @Query("offset")          offset : Int) : Observable<ComicsWrapper>
}

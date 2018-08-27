package com.streetbees.marvel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.streetbees.marvel.api.ComicsServices
import com.streetbees.marvel.bo.ComicsWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

  private var comicsDisposable: Disposable? = null

  override fun onCreate(savedInstanceState: Bundle?)
  {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val observable = ComicsServices.getComics()
    observable?.let { comics ->
      comicsDisposable = comics
          .subscribeOn(Schedulers.computation())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe({ comicsWrapper ->
            showComics(comicsWrapper)
          }, { _ -> Log.d("Marvel", "Impossible to get Comics !") })
    }

  }

  private fun showComics(comicsWrapper: ComicsWrapper){
    val adapter = ComicsAdapter(comicsWrapper)
    recyclerView.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.span))
    recyclerView.adapter = adapter
  }
}

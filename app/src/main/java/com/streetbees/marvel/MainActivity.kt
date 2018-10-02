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
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()
{

  private var comicsDisposable: Disposable? = null

  override fun onCreate(savedInstanceState: Bundle?)
  {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initList()

    getComics()
  }

  private fun initList()
  {
    recyclerView.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.span))
    recyclerView.adapter = ComicsAdapter()

    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener()
    {
      override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int)
      {
        val lastVisible = (recyclerView?.layoutManager as GridLayoutManager).findLastVisibleItemPosition()

        val endHasBeenReached = lastVisible + 5 >= recyclerView.adapter.itemCount
        if (recyclerView.adapter.itemCount > 0 && endHasBeenReached)
        {
          getComics(recyclerView.adapter.itemCount)
        }
      }
    })
  }

  private fun getComics(offset : Int = 0)
  {
    val observable = ComicsServices.getComics(offset)
    observable?.let { comics ->
      comicsDisposable = comics
          .subscribeOn(Schedulers.computation())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe({ comicsWrapper ->
            showComics(comicsWrapper.data.results)
          }, { _ -> Log.d("Marvel", "Impossible to get Comics !") })
    }
  }

  private fun showComics(comics: ArrayList<ComicsWrapper.ComicsList.Comic>){
    (recyclerView.adapter as ComicsAdapter).addItems(comics)

    Log.d("Marvel", "Impossible to get Comics !")


  }

}

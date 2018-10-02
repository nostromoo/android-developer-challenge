package com.streetbees.marvel

import android.app.Activity
import android.app.ActivityOptions
import android.support.v7.widget.RecyclerView
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.streetbees.marvel.bo.ComicsWrapper
import kotlinx.android.synthetic.main.comics_item.view.*
import org.jetbrains.anko.intentFor


/**
 * Created by Romain on 26/08/2018.
 */
class ComicsAdapter :
    RecyclerView.Adapter<ComicsAdapter.ComicViewHolder>() {

  private var comics :  ArrayList<ComicsWrapper.ComicsList.Comic> = arrayListOf()

  class ComicViewHolder(val view: View) : RecyclerView.ViewHolder(view){

    fun bind(comic : ComicsWrapper.ComicsList.Comic){

      val thumbnail = comic.thumbnail
      thumbnail?.let { image ->
        val context = view.context
        Glide.with(context)
            .load(image.url())
            .into(view.cover)


        view.setOnClickListener {
          val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, Pair.create(view, "comic_item"), Pair.create(view, "comic_details")).toBundle()
          context.startActivity(context.intentFor<ComicActivity>(ComicActivity.THUMBNAIL to image),options)
        }
      }
      view.title.text = comic.title

    }
  }

  override fun onBindViewHolder(holder: ComicViewHolder, position: Int)
  {
    holder.bind(comics[position])
  }

  override fun onCreateViewHolder(parent: ViewGroup,
                                  viewType: Int): ComicsAdapter.ComicViewHolder {
    val textView = LayoutInflater.from(parent.context)
        .inflate(R.layout.comics_item, parent, false)
    return ComicViewHolder(textView)
  }


  override fun getItemCount() = comics.size

  fun addItems(comics : List<ComicsWrapper.ComicsList.Comic>) {
    this.comics.addAll(comics)
    notifyDataSetChanged()
  }
}

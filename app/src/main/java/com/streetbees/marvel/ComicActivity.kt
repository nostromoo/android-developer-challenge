package com.streetbees.marvel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.streetbees.marvel.bo.ComicsWrapper
import kotlinx.android.synthetic.main.activity_comic.*
import org.jetbrains.anko.ctx

class ComicActivity : AppCompatActivity()
{
  companion object
  {
    const val THUMBNAIL = "THUMBNAIL"
  }

  override fun onCreate(savedInstanceState: Bundle?)
  {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_comic)

    val thumbnail = intent.getSerializableExtra(ComicActivity.THUMBNAIL) as ComicsWrapper.ComicsList.Comic.Image?
    thumbnail?.let { image ->
      Glide.with(ctx)
          .load(image.url())
          .into(comic)
    }

  }
}

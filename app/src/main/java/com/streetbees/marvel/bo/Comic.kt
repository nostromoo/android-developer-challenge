package com.streetbees.marvel.bo

import com.google.gson.annotations.Expose
import java.io.Serializable

/**
 * Created by Romain on 26/08/2018.
 */
data class ComicsWrapper( @Expose val data : ComicsList)
{
  data class ComicsList( @Expose val count : Int,
                         @Expose val results : ArrayList<Comic>)
  {
    data class Comic( @Expose val title : String?,
                      @Expose val thumbnail : Image?,
                      @Expose val images : List<Image>?){

      data class Image( @Expose val path : String?,
                        @Expose val extension : String?) : Serializable{
        fun url() = "$path.$extension"
      }
    }
  }
}






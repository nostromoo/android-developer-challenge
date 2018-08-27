package com.streetbees.marvel.utils

import java.security.MessageDigest

/**
 * Created by Romain on 27/08/2018.
 */
fun String.md5(): String {
  val md = MessageDigest.getInstance("MD5")
  val digested = md.digest(toByteArray())
  return digested.joinToString("") {
    String.format("%02x", it)
  }
}
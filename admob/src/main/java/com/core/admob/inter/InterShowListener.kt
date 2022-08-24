package com.core.admob.inter

interface InterShowListener {
   fun onAdInterClose(failToShowAd:Boolean)
   fun onAdInterFailShow()
   fun onAdInterShow()
}
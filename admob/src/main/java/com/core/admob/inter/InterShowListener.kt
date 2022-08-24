package com.core.admob.inter

interface InterShowListener {
   fun onAdClose(failToShowAd:Boolean)
   fun onAdFailShow()
   fun onAdShow()
}
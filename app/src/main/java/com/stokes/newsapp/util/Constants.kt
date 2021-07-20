package com.stokes.newsapp.util

object Constants {
    const val BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/viewed/"

    //News Period Constants
    const val TODAYS_NEWS = 1;
    const val LAST_7_DAYS_NEWS = 7;
    const val LAST_30_DAYS_NEWS = 30;

    //Search time delay
    const val NEWS_TIME_DELAY = 1000L

    //selected article bundle key
    const val CLICKED_ARTICLE = "clickedArticle"
}
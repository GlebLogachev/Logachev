package com.glogachev.developerslife.data

import com.glogachev.developerslife.data.model.LatestHotTopPostsNW
import com.glogachev.developerslife.data.model.RandomPostNW
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("random?json=true")
    suspend fun getRandomPost(): Response<RandomPostNW>

    @GET("latest/0?json=true")
    suspend fun getLatestPosts(): Response<LatestHotTopPostsNW>

    @GET("top/0?json=true")
    suspend fun getTopPosts(): Response<LatestHotTopPostsNW>

    @GET("hot/0?json=true")
    suspend fun getHotPosts(): Response<LatestHotTopPostsNW>
}
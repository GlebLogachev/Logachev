package com.glogachev.developerslife.domain

import com.glogachev.developerslife.domain.model.LatesHotTopPost
import com.glogachev.developerslife.domain.model.RandomPost

interface Repository {

    suspend fun getRandomPost(): NetworkResult<RandomPost>
    suspend fun getLatestPost(): NetworkResult<List<LatesHotTopPost>>
    suspend fun getTopPost(): NetworkResult<List<LatesHotTopPost>>
    suspend fun getHotPost(): NetworkResult<List<LatesHotTopPost>>
}
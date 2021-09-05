package com.glogachev.developerslife.data

import android.util.Log
import com.glogachev.developerslife.domain.NetworkResult
import com.glogachev.developerslife.domain.Repository
import com.glogachev.developerslife.domain.model.LatesHotTopPost
import com.glogachev.developerslife.domain.model.RandomPost
import com.glogachev.developerslife.domain.toDomain
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api
) : Repository {
    override suspend fun getRandomPost(): NetworkResult<RandomPost> {
        return try {
            val data = api.getRandomPost().body()!!.toDomain()
            NetworkResult.Success(data = data)
        } catch (ex: Throwable) {
            Log.d(this.javaClass.simpleName, ex.message.toString())
            NetworkResult.Error
        }
    }

    override suspend fun getLatestPost(): NetworkResult<List<LatesHotTopPost>> {
        return try {
            val data = api.getLatestPosts().body()!!.result.map {
                it.toDomain()
            }
            NetworkResult.Success(data = data)
        } catch (ex: Throwable) {
            Log.d(this.javaClass.simpleName, ex.message.toString())
            NetworkResult.Error
        }
    }

    override suspend fun getTopPost(): NetworkResult<List<LatesHotTopPost>> {
        return try {
            val data = api.getTopPosts().body()!!.result.map {
                it.toDomain()
            }
            NetworkResult.Success(data = data)
        } catch (ex: Throwable) {
            Log.d(this.javaClass.simpleName, ex.message.toString())
            NetworkResult.Error
        }
    }

    override suspend fun getHotPost(): NetworkResult<List<LatesHotTopPost>> {
        return try {
            val data = api.getHotPosts().body()!!.result.map {
                it.toDomain()
            }
            NetworkResult.Success(data = data)
        } catch (ex: Throwable) {
            Log.d(this.javaClass.simpleName, ex.message.toString())
            NetworkResult.Error
        }
    }
}
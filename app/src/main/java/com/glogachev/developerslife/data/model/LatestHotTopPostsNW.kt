package com.glogachev.developerslife.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LatestHotTopPostsNW(
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("totalCount")
    val totalCount: Int
) {
    @Keep
    data class Result(
        @SerializedName("author")
        val author: String,
        @SerializedName("canVote")
        val canVote: Boolean,
        @SerializedName("commentsCount")
        val commentsCount: Int,
        @SerializedName("date")
        val date: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("fileSize")
        val fileSize: Int,
        @SerializedName("gifSize")
        val gifSize: Int,
        @SerializedName("gifURL")
        val gifURL: String,
        @SerializedName("height")
        val height: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("previewURL")
        val previewURL: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("videoPath")
        val videoPath: String,
        @SerializedName("videoSize")
        val videoSize: Int,
        @SerializedName("videoURL")
        val videoURL: String,
        @SerializedName("votes")
        val votes: Int,
        @SerializedName("width")
        val width: String
    )
}
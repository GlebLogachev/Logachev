package com.glogachev.developerslife.domain

import com.glogachev.developerslife.data.model.LatestHotTopPostsNW
import com.glogachev.developerslife.data.model.RandomPostNW
import com.glogachev.developerslife.domain.model.LatesHotTopPost
import com.glogachev.developerslife.domain.model.RandomPost

internal fun RandomPostNW.toDomain(): RandomPost =
    RandomPost(
        description = description,
        gif = gifURL
    )

internal fun LatestHotTopPostsNW.Result.toDomain(): LatesHotTopPost =
    LatesHotTopPost(
        description = description,
        gif = gifURL
    )
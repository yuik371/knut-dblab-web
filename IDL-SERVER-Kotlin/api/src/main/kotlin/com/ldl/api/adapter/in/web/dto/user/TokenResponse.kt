package com.ldl.api.adapter.`in`.web.dto.user

import com.ldl.api.domain.TokenInfo

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        @JvmStatic
        fun from(tokenInfo: TokenInfo): TokenResponse = TokenResponse(
            accessToken = tokenInfo.accessToken,
            refreshToken = tokenInfo.refreshToken,
        )
    }
}
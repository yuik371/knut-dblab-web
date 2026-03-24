package com.ldl.api.domain

// jwt 토큰 정보 담는 dto
data class TokenInfo(
    val accessToken: String, // jwt 액세스 토큰
    val refreshToken: String, // 리프레시 토큰
)
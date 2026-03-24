package com.ldl.api.adapter.out.security

import io.jsonwebtoken.security.Keys

@SecurityAdapter
class JwtProvider (
    @Value("\${jwt.secret.key}") private val secretKey: String,
    @Value("\${jwt.access-token.plus-hour}") private val accessTokenPlusHour: Long,
    @Value("\${jwt.refresh-token.plus-hour}") private val refreshTokenPlusHour: Long,
) : JwtProviderPort {
    private val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    override fun generateTokens(userId: Long, role: UserRole): TokenInfo {

    }
}
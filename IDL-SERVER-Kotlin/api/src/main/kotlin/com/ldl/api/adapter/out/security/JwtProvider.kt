package com.ldl.api.adapter.out.security

import com.ldl.api.domain.TokenInfo
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.Date

@SecurityAdapter
class JwtProvider (
    @Value("\${jwt.secret.key}") private val secretKey: String,
    @Value("\${jwt.access-token.plus-hour}") private val accessTokenPlusHour: Long,
    @Value("\${jwt.refresh-token.plus-hour}") private val refreshTokenPlusHour: Long,
) : JwtProviderPort {
    private val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    override fun generateTokens(userId: Long, role: UserRole): TokenInfo {
        val accessToken = createToken(userId, role, accessTokenPlusHour)
        val refreshToken = createToken(userId, role, refreshTokenPlusHour)

        return TokenInfo(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    override fun getAuthenticatedUserInfo(accessToken: String): AuthenticatedUserInfo {
        val token = accessToken.removePrefix("Bearer ")
        val claims = parseTokenSafely(token)

        return AuthenticatedUserInfo(
            userId = claims.subject.toLong(),
            role = UserRole.valueOf(claims.get("role", String::class.java))
        )
    }

    override fun validateToken(token: String): Boolean {
        parseTokenSafely(token) // throws ExpiredJwtException / MalformedJwtException if invalid
        return true
    }

    private fun createToken(userId: Long, role: UserRole, plusHour: Long): String {
        val now = Date()
        val expiry = Date(now.time + plusHour * 60 * 60 * 1_000)

        return Jwts.builder()
            .setSubject(userId.toString())
            .claim("role", role.name)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(key)
            .compact()
    }

    private fun parseTokenSafely(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}
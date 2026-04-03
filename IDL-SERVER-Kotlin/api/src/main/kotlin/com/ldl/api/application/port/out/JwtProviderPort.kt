package com.ldl.api.application.port.out

interface JwtProviderPort {
    fun generateToken(userId: Long, role: UserRole): TokenInfo

    fun validateToken(token: String): Boolean

    fun getAuthenticatedUserInfo(accessToken: String): AuthenticatedUserInfo
}
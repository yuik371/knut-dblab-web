package com.ldl.api.domain

enum class UserRole (
    val description: String,
) {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER")
}
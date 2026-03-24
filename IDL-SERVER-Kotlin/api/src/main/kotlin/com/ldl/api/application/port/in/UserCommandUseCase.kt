package com.ldl.api.application.port.`in`

import com.ldl.api.application.port.`in`.dto.UserLoginCommand
import com.ldl.api.domain.TokenInfo
import com.ldl.api.domain.User
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated

@Validated
interface UserCommandUseCase {
    fun singUp(@Valid signUserCommandUseCase: UserCommandUseCase): User
    fun login(@Valid loginCommand: UserLoginCommand): TokenInfo
}
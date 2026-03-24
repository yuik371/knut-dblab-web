package com.ldl.api.adapter.`in`.web.swagger

import com.ldl.api.adapter.`in`.web.dto.user.TokenResponse
import com.ldl.api.adapter.`in`.web.dto.user.UserIdResponse
import io.github.seob7.Api
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "USER API")
interface UserOpenApiSwagger {
    @ApiResponseExplanation(
        erros = [
            ApiExceptionExplanation(
                name = "회원가입 실패 - email",
                description = "email이 이미 존재하는 경우",
                value = UserErrorCode::class,
                constant = "EMAIL_EXISTS"
            ),
            ApiExceptionExplanation(
                name = "회원가입 실패 - nickname",
                description = "nickname이 이미 존재하는 경우",
                value = UserErrorCode::class,
                constant = "NICKNAME_EXISTS"
            )
        ]
    )
    @Operation(summary = "회원가입", description = "비밀번호는 영문과 숫자를 포함하여 5자 이상 20자 이하여야 합니다.<br>\" +\n" +
            "            \"닉네임은 2자 이상 30자 이하여야 합니다.")
    fun signUp(
        @RequestBody @Valid signUpRequest: UserSignUpRequest
    ): ResponseEntity<Api<UserIdResponse>>

    @ApiResponseExplanations(
        erros = [
            ApiExceptionExplanation(
                name = "로그인 실패 - 1",
                description = "사용자를 찾을 수 없는 경우",
                value = UserErrorCode::class,
                constant = "USER_NOT_FOUND"
            ),
            ApiExceptionExplanation(
                name = "로그인 실패 - 2",
                description = "비밀번호가 일치 하지 않는 경우",
                value = UserErrorCode::class,
                constant = "INVALID_PASSWORD"
            )
        ]
    )
    @Operation(summary = "로그인", description = "비밀번호는 영문과 숫자를 포함하여 5자 이상 20자 이하여야 합니다.<br>" +
            "응답으로 AccessToken 과 RefreshToken 을 전달합니다.")
    fun login(
        @RequestBody @Valid loginRequest: UserLoginRequest
    ): ResponseEntity<Api<TokenResponse>>
}
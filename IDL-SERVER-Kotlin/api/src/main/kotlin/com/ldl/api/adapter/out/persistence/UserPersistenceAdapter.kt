package com.ldl.api.adapter.out.persistence

import com.ldl.api.domain.User

@PersistenceAdapter
class UserPersistenceAdapter (
    private val userMongoRepository: UserMongoRepository
) : UserPersistencePort {
    override fun save(user: User): User = userMongoRepository.save(UserDocument.from(user)).toDomain()
    override fun existsByEmail(email: String): Boolean = userMongoRepository.existsByEmail(email)
    override fun existsByNickname(nicknames: String): Boolean = userMongoRepository.existsByNickname(nickname)
    override fun findByEmail(email: String): User ?= userMongoRepository.findByEmail()
}
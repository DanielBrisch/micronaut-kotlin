package com.mongoproject.repository

import com.mongoproject.model.AppUser
import io.micronaut.data.mongodb.annotation.MongoFindQuery
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository
interface AppUserRepository : CrudRepository<AppUser, String> {

    fun findByEmailEquals(email: String) : List<AppUser>

    @MongoFindQuery("{ name: { \$regex: :name, \$options: 'i' }}")
    fun findNameLike(name: String) : List<AppUser>
}
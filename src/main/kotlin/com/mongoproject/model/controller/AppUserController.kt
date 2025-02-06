package com.mongoproject.model.controller

import com.mongoproject.model.Address
import com.mongoproject.model.AppUser
import com.mongoproject.model.request.AppUserRequest
import com.mongoproject.model.request.SearchRequest
import com.mongoproject.service.AppUserService;
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*

@Controller("/user")
class AppUserController(
    private val appUserService: AppUserService
) {

    @Post("/create")
    @Status(HttpStatus.CREATED)
    fun create(@Body appUserRequest: AppUserRequest) = appUserService.create(appUser = appUserRequest.toModel())

    @Get
    fun getAll() = appUserService.getAll()

    @Get("/{id}")
    fun getById(id: String) = appUserService.getById(id)

    @Put("/{id}")
    fun update(
        id: String,
        @Body request: AppUserRequest,
        @Header("X-Foo") header: String
    ) : AppUser {
        println("Header: $header")

        return appUserService.update(id, request.toModel())
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    fun delete(id: String) = appUserService.delete(id)

    @Post("/search")
    fun search(@Body searchRequest: SearchRequest) : List<AppUser> = appUserService.search(searchRequest)

    private fun AppUserRequest.toModel(): AppUser =
        AppUser(
            name = this.name,
            email = this.email,
            address = Address(
                street = this.street,
                city = this.city,
                code = this.code
            )
        )
}

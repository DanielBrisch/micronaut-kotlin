package com.mongoproject.model.request

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable.Deserializable

@Introspected
@Deserializable
data class SearchRequest(
    val name: String? = null,
    val email: String? = null,
)

package com.pranav.graphql.models

import com.google.gson.annotations.SerializedName

data class GraphQLResponseModel(
    @SerializedName("data")
    val data: Data?
) {
    data class Data(
        @SerializedName("countries")
        val countries: List<Country?>?
    ) {
        data class Country(
            @SerializedName("name")
            val name: String?
        )
    }
}

package com.pranav.graphql

import com.apollographql.apollo.ApolloClient
import com.pranav.GetCountriesQuery
import javax.inject.Inject


class MainRepository @Inject constructor(private val apolloClient: ApolloClient) {
    suspend fun getCountries(): List<GetCountriesQuery.Country>? {
        return  apolloClient.query(GetCountriesQuery()).execute().data?.countries
    }
}

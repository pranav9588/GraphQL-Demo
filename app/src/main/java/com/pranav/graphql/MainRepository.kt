package com.pranav.graphql

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloClient
import com.pranav.FindCountriesOfAContinentQuery
import com.pranav.GetCountriesQuery
import com.pranav.graphql.models.GraphQLResponseModel
import javax.inject.Inject


class MainRepository @Inject constructor(private val apolloClient: ApolloClient) {



    suspend fun getCountriesOfSelectedContinent(continentCode: String) = apolloClient.query(
        FindCountriesOfAContinentQuery(continentCode)
    ).execute()

    suspend fun getCountries(): List<GetCountriesQuery.Country>? {
        return  apolloClient.query(GetCountriesQuery()).execute().data?.countries
    }
}

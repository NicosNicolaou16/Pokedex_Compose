package com.nicos.pokedex_compose.utils.generic_classes

import android.content.Context
import com.nicos.pokedex_compose.R
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class HandlingError(private var context: Context) {
    internal fun handleErrorMessage(e: Throwable): String {
        return when (e) {
            is HttpException -> {
                val responseBody: ResponseBody? = e.response()?.errorBody()
                val errorMessage = responseBody?.let { getErrorMessage(it.string()) }
                    ?: context.getString(R.string.something_went_wrong)
                errorMessage
            }

            is SocketTimeoutException -> {
                context.getString(R.string.something_went_wrong_with_server)
            }

            is IOException -> {
                context.getString(R.string.check_your_internet_connection)
            }

            else -> {
                e.message ?: context.getString(R.string.something_went_wrong)
            }
        }
    }

    private fun getErrorMessage(handleErrorMessage: String): String? {
        return try {
            val jsonObject = JSONObject(handleErrorMessage)
            val jsonArray = JSONArray(handleErrorMessage)
            jsonObject.optString("message")//depend from server the json key - this is an example
        } catch (e: Exception) {
            e.message
            context.getString(R.string.something_went_wrong)
        }
    }
}
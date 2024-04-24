package br.com.study.tmdb_prime_video.core.mock

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> parseJsonToModel(jsonString: String): T {
    val gson = Gson()
    return gson.fromJson(jsonString, object : TypeToken<T>() {}.type)
}

fun readJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}
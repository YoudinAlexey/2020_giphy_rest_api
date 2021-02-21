package ua.youdin.a2020giphyrestapi.data.giphyAPI

import android.os.Build
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//    https://api.giphy.com/v1/gifs/search?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&q=car&limit=2&offset=0&rating=g&lang=en
//    https://api.giphy.com/v1/gifs/search?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&q=car&limit=1&offset=1&rating=g&lang=en

interface GiphyService {
    @GET("v1/gifs/search?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&rating=g&lang=en")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("limit") itemsPerPage: Int,
    ): RepoSearchResponse

    companion object {
        private val BASE_URL =
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) "https://api.giphy.com/" else "http://api.giphy.com/"

        fun create(): GiphyService {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GiphyService::class.java)
        }
    }
}
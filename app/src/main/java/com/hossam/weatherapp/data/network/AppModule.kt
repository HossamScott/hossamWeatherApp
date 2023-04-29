package com.hossam.weatherapp.data.network


import com.facebook.stetho.okhttp3.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.hossam.weatherapp.utils.ConstantsObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object AppModule {

    private var okHttpClient: OkHttpClient? = null

    fun create(): RetroServieInstance? {
        /*-------------------*/
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .setPrettyPrinting() // Pretty print
            .create()
        /*-------------------*/okHttpClient = buildClient()

        val retrofit = Retrofit.Builder()
            .baseUrl(ConstantsObject.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        return retrofit.create(RetroServieInstance::class.java)
    }

    private fun buildClient(): OkHttpClient? {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(object : Interceptor {
            var request: Request? = null

            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json").build()
                return chain.proceed(request!!)
            }
        }).addNetworkInterceptor(httpLoggingInterceptor)

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(StethoInterceptor())
        }
        return builder.connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()
    }

}
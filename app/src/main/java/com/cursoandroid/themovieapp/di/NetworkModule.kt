package com.cursoandroid.themovieapp.di

import com.cursoandroid.themovieapp.application.AppConstants
import com.cursoandroid.themovieapp.repository.WebService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }


    @Singleton
    @Provides
    fun provideWebService(retrofit: Retrofit):WebService{
        return retrofit.create(WebService::class.java)
    }
}
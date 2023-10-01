package com.example.recipes.di

import com.example.recipes.data.repo.RecipeKdaoRepo
import com.example.recipes.retrofit.ApiUtils
import com.example.recipes.retrofit.RecipeKdao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecipeKdaoRepo(kdao: RecipeKdao) : RecipeKdaoRepo{
        return RecipeKdaoRepo(kdao)
    }

    @Provides
    @Singleton
    fun provideRecipeKdao() : RecipeKdao{
        return ApiUtils.getRecipeDao()
    }
}
package nl.kops.jesse.student_645147.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.kops.jesse.student_645147.data.api.PokemonDetailService
import nl.kops.jesse.student_645147.data.api.PokemonService
import nl.kops.jesse.student_645147.data.mapper.PokemonListMapper
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/") // This base URL should be common if both services share it
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun providePokemonService(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)

    @Provides
    @Singleton
    fun providePokemonDetailService(retrofit: Retrofit): PokemonDetailService =
        retrofit.create(PokemonDetailService::class.java)

    @Provides
    @Singleton
    fun providePokemonListMapper(): PokemonListMapper = PokemonListMapper()
}

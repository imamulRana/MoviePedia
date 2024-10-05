package inc.anticbyte.moviepedia.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import inc.anticbyte.moviepedia.data.repo.NetworkRepositoryImpl
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModules {
    @Binds
    @Singleton
    fun bindNetworkRepo(impl: NetworkRepositoryImpl): NetworkRepository
}
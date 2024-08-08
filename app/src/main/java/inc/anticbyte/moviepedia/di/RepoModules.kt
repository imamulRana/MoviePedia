package inc.anticbyte.moviepedia.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import inc.anticbyte.moviepedia.data.remote.NetworkRepoImpl
import inc.anticbyte.moviepedia.domain.repo.NetworkRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModules {
    @Binds
    @Singleton
    fun bindNetworkRepo(impl: NetworkRepoImpl): NetworkRepo
}
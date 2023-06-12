package net.hwyz.iov.data.user.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import net.hwyz.iov.data.user.IUserRepository
import net.hwyz.iov.data.user.UserDataStore
import net.hwyz.iov.data.user.UserRepository
import net.hwyz.iov.data.user.local.LocalUserDataStore
import net.hwyz.iov.data.user.local.database.UserDB
import net.hwyz.iov.data.user.local.database.UserDao
import net.hwyz.iov.data.user.remote.RemoteUserDataStore
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object DataModule {


    @Provides
    @Named("local")
    fun provideLocalUserDataStore(userDao: UserDao): UserDataStore = LocalUserDataStore(userDao)


    @Provides
    @Named("remote")
    fun provideRemoteUserDataStore(): UserDataStore = RemoteUserDataStore()


    @Provides
    fun provideUserRepository(
        @Named("local") localUserDataStore: UserDataStore,
        @Named("remote") remoteUserDataStore: UserDataStore
    ): IUserRepository = UserRepository(localUserDataStore, remoteUserDataStore)
}

@Module
@InstallIn(ActivityComponent::class)
object AppDataModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): UserDB = UserDB.getInstance(context)


    @Provides
    @Singleton
    fun provideUserDao(db: UserDB): UserDao = db.userDao()

}
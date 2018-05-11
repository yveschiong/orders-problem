package com.yveschiong.ordersproblem.injection

import com.yveschiong.ordersproblem.interfaces.RequestService
import com.yveschiong.ordersproblem.requests.RequestHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideRequestService() = RequestService.create()

    @Provides
    @Singleton
    fun provideRequestHandler(requestService: RequestService) = RequestHandler(requestService)
}
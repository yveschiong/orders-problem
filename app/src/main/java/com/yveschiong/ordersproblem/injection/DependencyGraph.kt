package com.yveschiong.ordersproblem.injection

import com.yveschiong.ordersproblem.interfaces.RequestService
import com.yveschiong.ordersproblem.requests.RequestHandler
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface DependencyGraph {
    val getRequestService: RequestService

    val getRequestHandler: RequestHandler
}
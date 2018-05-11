package com.yveschiong.ordersproblem
import android.app.Application
import com.yveschiong.ordersproblem.injection.ApplicationModule
import com.yveschiong.ordersproblem.injection.DaggerDependencyGraph
import com.yveschiong.ordersproblem.injection.DependencyGraph

class App : Application() {

    companion object {
        // Allow access from java code
        @JvmStatic lateinit var graph: DependencyGraph
    }

    override fun onCreate() {
        super.onCreate()

        graph = DaggerDependencyGraph.builder()
                .applicationModule(ApplicationModule())
                .build()
    }
}
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()       // Required for Firebase
        mavenCentral() // Required for other dependencies
    }
}

rootProject.name = "FaceItOff"
include(":app")

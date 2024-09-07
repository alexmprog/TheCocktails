pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "TheCocktails"
include(":app")
include(":core:common")
include(":core:data")
include(":core:database")
include(":core:domain")
include(":core:ui")
include(":core:model")
include(":core:network")
include(":feature:categories")
include(":feature:cocktails")
include(":feature:glasses")
include(":feature:ingredients")

# TheCocktails

![GitHub Logo](/screenshots/cocktails_app_flow.gif)

## About
It simply loads data from API and stores it in persistence storage (i.e. SQLite Database).
* User real [TheCocktailDB](https://www.thecocktaildb.com/) api.<br>
* This makes it offline capable.<br>
* Clean and Simple Compose Material UI.<br>
* Use Gradle version catalog and convention plugins.<br>
* Clean architecture and MVVM.<br>

## Built With 🛠
[Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.<br>
[Kotlin Gradle DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Provides an alternative syntax to the traditional Groovy DSL for Gradle build system. <br>
[Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Kotlin Asynchronous or non-blocking programming.<br>
[Compose](https://developer.android.com/develop/ui/compose/documentation) - The modern toolkit for building native Android UI.<br>
[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.<br>
[Jetpack Navigation](https://developer.android.com/guide/navigation) - Component helps you implement navigation.<br>
[Datastore](https://developer.android.com/topic/libraries/architecture/datastore) - Data storage solution that stores key-value pairs or complex typed objects.<br>
[Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.<br>
[Hilt](https://dagger.dev/hilt/) - Easy way to incorporate Dagger dependency injection into an Android application.<br>
[Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.<br>
[Coil](https://coil-kt.github.io/coil/) - An image loading library for Android.<br>
[Kotlin Serialization]([https://github.com/square/moshi](https://kotlinlang.org/docs/serialization.html)) - A modern JSON library for Kotlin and Java.<br>
[Testing](https://developer.android.com/training/testing) - App contains different kinds of tests: Local Unit, Integration, UI, End2End tests.<br>
## Module Graph
```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph LR
  subgraph :core
    :core:model["model"]
    :core:domain["domain"]
    :core:ui["ui"]
  end
  subgraph :feature
    :feature:categories-list["categories-list"]
    :feature:cocktails-list["cocktails-list"]
    :feature:cocktail-details["cocktail-details"]
    :feature:glasses-list["glasses-list"]
    :feature:ingredients-list["ingredients-list"]
    :feature:settings["settings"]
  end
  :app --> :core:model
  :app --> :core:domain
  :app --> :core:ui
  :app --> :feature:categories-list
  :app --> :feature:cocktails-list
  :app --> :feature:cocktail-details
  :app --> :feature:glasses-list
  :app --> :feature:ingredients-list
  :app --> :feature:settings
```
## Modules Overview
The project is divided into several modules:
- :app:mobile - Android app module for phone devices.
- :build:logic:convention - Conventions plugins for managing build configurations.
- :core:common - Kotlin-only module containing utility functions (not an Android library).
- :core:data - Android library provides data access using Repository pattern.
- :core:database - Android library with common Room database.
- :core:datastore - Android library with common Datastore Preferences.
- :core:domain - Android library provides access to main UseCases.
- :core:model - Kotlin-only module containing app model data classes.
- :core:network - Android library with common Retrofit/Okttp network services.
- :core:ui - Android library with common Jetpack Compose UI widgets.
- :feature:categories-list - Android library with categories list screen.
- :feature:ingredients-list - Android library with ingredients list screen.
- :feature:glasses-list - Android library with glasses list screen.
- :feature:cocktails-list - Android library with cocktails list screen.
- :feature:cocktails-details - Android library with cocktails details screen.
- :feature:settings - Android library with settings details screen.

## Architecture
This repository uses recommended Android [App architecture](https://developer.android.com/topic/architecture).
![Image of MVVM](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-ui-udf.png)
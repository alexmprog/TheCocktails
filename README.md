# TheCocktails

![GitHub Logo](/screenshots/cocktails_app_flow.gif)

## About
It simply loads data from API and stores it in persistence storage (i.e. SQLite Database).
* User real [TheCocktailDB](https://www.thecocktaildb.com/) api.<br>
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
[Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) - A modern JSON library for Kotlin and Java.<br>
[Testing](https://developer.android.com/training/testing) - App contains different kinds of tests: Local Unit, Integration, UI, End2End tests.<br>
## Module Graph

```mermaid
%%{
  init: {
    'theme': 'base',
    'themeVariables': {"primaryTextColor":"#F6F8FAff","primaryColor":"#5a4f7c","primaryBorderColor":"#5a4f7c","tertiaryColor":"#40375c","lineColor":"#f5a623","fontSize":"12px"}
  }
}%%

graph TB
  subgraph :core
    :core:model["model"]
    :core:domain["domain"]
    :core:ui["ui"]
    :core:data["data"]
    :core:network["network"]
    :core:database["database"]
    :core:datastore["datastore"]
    :core:common["common"]
  end
  subgraph :feature
    :feature:settings["settings"]
    :feature:glasses-list["glasses-list"]
    :feature:categories-list["categories-list"]
    :feature:cocktails-list["cocktails-list"]
    :feature:cocktail-details["cocktail-details"]
    :feature:ingredients-list["ingredients-list"]
  end
  :feature:settings --> :core:model
  :feature:settings --> :core:domain
  :feature:settings --> :core:ui
  :feature:glasses-list --> :core:model
  :feature:glasses-list --> :core:domain
  :feature:glasses-list --> :core:ui
  :app --> :core:model
  :app --> :core:domain
  :app --> :core:ui
  :app --> :feature:categories-list
  :app --> :feature:cocktails-list
  :app --> :feature:cocktail-details
  :app --> :feature:glasses-list
  :app --> :feature:ingredients-list
  :app --> :feature:settings
  :core:data --> :core:model
  :core:data --> :core:network
  :core:data --> :core:database
  :core:data --> :core:datastore
  :core:data --> :core:common
  :core:network --> :core:common
  :feature:categories-list --> :core:model
  :feature:categories-list --> :core:domain
  :feature:categories-list --> :core:ui
  :feature:cocktail-details --> :core:model
  :feature:cocktail-details --> :core:domain
  :feature:cocktail-details --> :core:ui
  :feature:cocktails-list --> :core:model
  :feature:cocktails-list --> :core:domain
  :feature:cocktails-list --> :core:ui
  :core:domain --> :core:model
  :core:domain --> :core:data
  :core:datastore --> :core:common
  :feature:ingredients-list --> :core:model
  :feature:ingredients-list --> :core:domain
  :feature:ingredients-list --> :core:ui

classDef android-library fill:#3BD482,stroke:#fff,stroke-width:2px,color:#fff;
classDef kotlin-jvm fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
classDef android-application fill:#2C4162,stroke:#fff,stroke-width:2px,color:#fff;
class :feature:settings android-library
class :core:model kotlin-jvm
class :core:domain android-library
class :core:ui android-library
class :feature:glasses-list android-library
class :app android-application
class :feature:categories-list android-library
class :feature:cocktails-list android-library
class :feature:cocktail-details android-library
class :feature:ingredients-list android-library
class :core:data android-library
class :core:network android-library
class :core:database android-library
class :core:datastore android-library
class :core:common kotlin-jvm

```

## Architecture
This repository uses recommended Android [App architecture](https://developer.android.com/topic/architecture).
![Image of MVVM](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-ui-udf.png)
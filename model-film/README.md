# Module model-film

This contains all media model/entity classes used by both the app and the provider system. 

In this package, all media content instances extends the abstract [**Film**](com.flixclusive.model.film.Film.kt) class. This includes both [**FilmDetails**](com.flixclusive.model.film.FilmDetails) and
[**FilmSearchItem**](com.flixclusive.model.film.FilmSearchItem) classes. 

**FilmDetails** class is a parent class of both [**Movie**](com.flixclusive.model.film.Movie) and
[**TvShow**](com.flixclusive.model.film.TvShow) while **FilmSearchItem** is a single final class only of every search media item results.
---

## Use as a dependency

**Step 1.** Add the JitPack repository to your build file
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // other repositories here...
        mavenCentral()
        maven {
            url = uri("https://jitpack.io") // <-- add this
        }
    }
}
```

**Step 2.** Add the dependency

_The latest version can be found here: [latest tag](https://github.com/flixclusiveorg/core-stubs/releases/latest)_
```kotlin
dependencies {
    implementation("com.github.flixclusiveorg.core-stubs:model-film:$LATEST_CORE_STUBS_VERSION")
}
```
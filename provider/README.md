# Module provider

This contains all provider stubs that are used to create a custom provider. The best place to start with is [Provider](com.flixclusive.provider.Provider) and
[ProviderApi](com.flixclusive.provider.ProviderApi)

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

_The latest version can be found here: [latest tag](https://github/com/flixclusiveorg/core-stubs/releases)_
```kotlin
dependencies {
    implementation("com.github.flixclusiveorg.core-stubs:provider:$LATEST_CORE_STUBS_VERSION")
}
```
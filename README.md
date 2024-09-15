![Flixclusive logo](https://i.imgur.com/tizcKbi.png)

## Provider stubs and utilities

Most data classes, models or entities can be found here.

- [:core-util](./core-util) - contains most of utility and common tools that can be used in the app and providers. 
- [:model-film](./model-film) - contains all model classes that can be considered as stream media (film or shows).
- [:model-provider](./model-provider) - contains all the model stubs that will be used on both compilation and runtime of providers.
- [:provider](./provider) - contains all provider stubs such as [Provider](./provider/src/main/kotlin/com/flixclusive/provider/Provider.kt), [ProviderApi](./provider/src/main/kotlin/com/flixclusive/provider/ProviderApi.kt), and [ProviderWebView](./provider/src/main/kotlin/com/flixclusive/provider/webview/ProviderWebView.kt).

To use _all_ of the modules:

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
    implementation("com.github.flixclusiveorg.core-stubs:provider:$LATEST_CORE_STUBS_VERSION")
}
```
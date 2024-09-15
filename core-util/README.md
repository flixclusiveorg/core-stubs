# Module core-util

This contains all the utility functions/methods used by both the app and the provider system. These utilities could be helpful for common development tasks such as logging, exception handling and response parsing.

Here are the utility packages, grouped by their responsibilities:
- android - contains all android related utilities such as notification and toast handling.
- common - contains mostly of constants and common utilities used all through out the app.
- coroutines - contains all of coroutine related QoL utilities such as singleton dispatchers and scopes.
- exception - contains mostly of exception helper utilities.
- log - contains all log related utilities including mock test rules.
- network - contains all network utilities such as response handling and QoL code for HTTP requests.
- webview - contains the main base WebView used all through out the app.

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
    implementation("com.github.flixclusiveorg.core-stubs:core-util:$LATEST_CORE_STUBS_VERSION")
}
```
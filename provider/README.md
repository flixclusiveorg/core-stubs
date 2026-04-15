# Module provider

This contains all provider stubs that are used to create a custom provider.

## Capability-first provider design

Providers expose APIs from the plugin level through `ProviderPlugin.getApi(context)`.

Capability contracts are now grouped by package:
- `com.flixclusive.provider.capability.api.*`
- `com.flixclusive.provider.capability.ext.*`

Capability APIs:
- `CatalogProviderApi`
- `SearchProviderApi`
- `MetadataProviderApi`
- `CrossMatchProviderApi`
- `MediaLinkProviderApi<T : MediaLink>`
- `LinkProviderApi`
- `StreamProviderApi`
- `SubtitleProviderApi`
- `TrackerProviderApi`

Load provider API from a plugin:
```kotlin
val api = plugin.getApi(context)
```

`ProviderPlugin.getApi(context, client)` is deprecated and kept temporarily for backward compatibility.

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
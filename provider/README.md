# Module provider

This contains all provider stubs that are used to create a custom provider.

## Capability-first provider design

Providers can now expose capabilities at the plugin level using capability provider/factory interfaces.

Capability contracts are now grouped by package:
- `com.flixclusive.provider.capability.marker.*`
- `com.flixclusive.provider.capability.api.*`
- `com.flixclusive.provider.capability.ext.*`

Available plugin capability providers/factories:
- `CatalogProvider`
- `SearchProvider`
- `MetadataProvider`
- `CrossMatchProvider`
- `StreamProvider`
- `SubtitleProvider`
- `TrackerProvider`

Example capability discovery:
```kotlin
val metadataPlugins = allPlugins.filterIsInstance<MetadataProvider>()
```

Capability APIs are split from plugin capability provider/factory interfaces:
- `CatalogProviderApi`
- `SearchProviderApi`
- `MetadataProviderApi`
- `CrossMatchProviderApi`
- `MediaLinkProviderApi<T : MediaLink>`
- `LinkProviderApi`
- `StreamProviderApi`
- `SubtitleProviderApi`
- `TrackerProviderApi`

Load a capability API on-demand from a plugin:
```kotlin
// Dedicated helpers (returns null if plugin does not implement that capability provider/factory)
val metadataApi = plugin.getMetadataProviderApi(context, client)
val crossMatchApi = plugin.getCrossMatchProviderApi(context, client)
```

`ProviderPlugin.getApi(context, client)` is deprecated and kept temporarily for legacy compact providers.

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
# Module provider

This contains all provider stubs that are used to create a custom provider.

## Capability-first provider design

Providers can expose capability APIs from the plugin level through nullable getters on `ProviderPlugin`.

Capability contracts are available under:
- `com.flixclusive.provider.capability.*`

Capability APIs:
- `CatalogProviderApi`
- `SearchProviderApi`
- `MetadataProviderApi`
- `CrossMatchProviderApi`
- `MediaLinkProviderApi`
- `TrackerProviderApi`

Get capability APIs from a plugin:
```kotlin
val searchApi = plugin.getSearchApi(context)
val catalogApi = plugin.getCatalogApi(context)
val metadataApi = plugin.getMetadataApi(context)
val mediaLinkApi = plugin.getMediaLinkApi(context)

// Catalog loading is async (preferred)
val catalogList = catalogApi?.getCatalogs()
```

If a capability is not supported by a provider, the getter returns `null`.

## Pagination

Capability APIs that return paged results use `PaginatedResponse<T>`.

`SearchResponseData<T>` is deprecated.

`ProviderApi` is deprecated.

Legacy API factory method is still available for backward compatibility:
- `ProviderPlugin.getApi(context, client)` (deprecated)

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

_The latest version can be found in the [core-stubs releases page](https://github.com/flixclusiveorg/core-stubs/releases)._ 
```kotlin
dependencies {
    implementation("com.github.flixclusiveorg.core-stubs:provider:$LATEST_CORE_STUBS_VERSION")
}
```
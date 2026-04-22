# Module model-provider

This contains all model/entity classes that are used by the provider system. This mainly includes media and provider information entities.

## Link flag migration

For media links that redirect/handoff to another streaming site, use:
- `Flag.ThirdPartyGateway(name, url, logo, description)`

`Flag.Trusted` is deprecated and kept temporarily for compatibility.

## Catalog model migration

`ProviderCatalog` has been renamed to `Catalog`.

Use `Catalog` as the canonical catalog model.

- `Catalog.mediaType` uses `CatalogType` with values: `All`, `Shows`, `Movies`.
- `ProviderCatalog` remains as a deprecated alias for compatibility.

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
    implementation("com.github.flixclusiveorg.core-stubs:model-provider:$LATEST_CORE_STUBS_VERSION")
}
```
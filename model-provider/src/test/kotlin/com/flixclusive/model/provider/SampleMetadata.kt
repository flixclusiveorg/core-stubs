package com.flixclusive.model.provider

internal const val SAMPLE_METADATA_STRING = """
    {
        "iconUrl": "https://i.imgur.com/XXXXXX.png",
        "repositoryUrl": "https://github.com/xxxxxxxx/sample-repo",
        "adult": false,
        "providerType": {
            "type": "Movies, TV Shows, etc."
        },
        "status": "Down",
        "language": {
            "languageCode": "Multiple"
        },
        "authors": [
            {
                "image": "https://github.com/xxxxxxxx.png",
                "name": "xxxxxxxx",
                "socialLink": "https://github.com/xxxxxxxx"
            }
        ],
        "versionCode": 60001,
        "description": "Sample description",
        "buildUrl": "https://raw.githubusercontent.com/xxxxxxxx/sample-repo/builds/MyProvider.flx",
        "changelog": "# Changelogs\n- Update!",
        "versionName": "6.0.1",
        "name": "MyProvider"
    }
"""


internal val sampleMetadata = ProviderMetadata(
    iconUrl = "https://i.imgur.com/XXXXXX.png",
    repositoryUrl = "https://github.com/xxxxxxxx/sample-repo",
    adult = false,
    providerType = ProviderType.All,
    status = Status.Down,
    language = Language.Multiple,
    authors = listOf(
        Author(
            image = "https://github.com/xxxxxxxx.png",
            name = "xxxxxxxx",
            socialLink = "https://github.com/xxxxxxxx"
        )
    ),
    versionCode = 60001,
    description = "Sample description",
    buildUrl = "https://raw.githubusercontent.com/xxxxxxxx/sample-repo/builds/MyProvider.flx",
    changelog = "# Changelogs\n- Update!",
    versionName = "6.0.1",
    name = "MyProvider",
    id = "test",
)
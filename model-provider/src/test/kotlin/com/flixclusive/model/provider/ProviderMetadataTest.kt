package com.flixclusive.model.provider

import com.google.gson.Gson
import org.junit.Test

class ProviderMetadataTest {
    private val gson = Gson()
    
    @Test
    fun `Two provider metadata from different sources with equal id`() {
        val metadataFromString = gson.fromJson(SAMPLE_METADATA_STRING, ProviderMetadata::class.java)

        assert(sampleMetadata == metadataFromString)
        assert(sampleMetadata.id == metadataFromString.id)
    }

    @Test
    fun `Gson toJson should return id property`() {
        val metadataWithCustomId = sampleMetadata.copy(customId = "test-id")
        val metadataAsString = gson.toJson(metadataWithCustomId)

        assert(metadataAsString.contains("\"id\":\"test-id\""))
    }
}
package com.flixclusive.provider.filter

/**
 * An abstract class representing a filter group.
 *
 * @param name The name of the filter.
 * @param list The list of filters in the group.
 */
open class FilterGroup(
    val name: String,
    val list: List<Filter<*>>
) : List<Filter<*>> by list {
    @Suppress("unused")
    constructor(
        name: String,
        vararg list: Filter<*>,
    ) : this(
        name = name,
        list = when {
            list.isNotEmpty() -> list.asList()
            else -> emptyList()
        }
    )

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + list.hashCode()
        return result
    }
}

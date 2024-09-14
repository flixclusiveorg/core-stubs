package com.flixclusive.core.util.network.okhttp;

/**
 * Enum class representing standard HTTP request methods.
 *
 * @property requiresBody Indicates whether the HTTP method typically requires a request body or data.
 *
 * @see HttpMethod.GET
 * @see HttpMethod.POST
 * @see HttpMethod.PUT
 * @see HttpMethod.DELETE
 * @see HttpMethod.PATCH
 * @see HttpMethod.HEAD
 * @see HttpMethod.OPTIONS
 * @see HttpMethod.TRACE
 */
enum class HttpMethod(val requiresBody: Boolean = false) {
    /**
     * The GET method requests a representation of the specified resource.
     * Requests using GET should only retrieve data and should have no other effect.
     */
    GET,

    /**
     * The POST method submits an entity to the specified resource,
     * often causing a change in state or side effects on the server.
     */
    POST(requiresBody = true),

    /**
     * The PUT method replaces all current representations of the target
     * resource with the request payload.
     */
    PUT(requiresBody = true),

    /**
     * The DELETE method deletes the specified resource.
     */
    DELETE,

    /**
     * The PATCH method partially modifies the specified resource.
     */
    PATCH(requiresBody = true),

    /**
     * The HEAD method asks for a response identical to a GET request,
     * but without the response body. Useful for retrieving meta-information.
     */
    HEAD,

    /**
     * The OPTIONS method describes the communication options for the target resource.
     */
    OPTIONS,

    /**
     * The TRACE method performs a message loop-back test along the path to the target resource.
     */
    TRACE;

    /**
     * Indicates whether this HTTP method is considered safe.
     * Safe methods should not change the state of the server.
     *
     * @return true if the method is safe, false otherwise
     */
    val isSafe: Boolean
        get() = this in setOf(GET, HEAD, OPTIONS, TRACE)

    /**
     * Indicates whether this HTTP method is idempotent.
     * Idempotent methods can be called multiple times without different outcomes.
     *
     * @return true if the method is idempotent, false otherwise
     */
    val isIdempotent: Boolean
        get() = this in setOf(GET, PUT, DELETE, HEAD, OPTIONS, TRACE)

    /**
     * The companion object provides utility methods for working with HttpMethod enum values.
     * */
    companion object {
        /**
         * Parses a string representation of an HTTP method and returns the corresponding HttpMethod enum.
         *
         * @param method The string representation of the HTTP method.
         * @return The corresponding HttpMethod enum value.
         * @throws IllegalArgumentException if the method string doesn't match any known HTTP method.
         */
        @Throws(IllegalArgumentException::class)
        fun parse(method: String): HttpMethod {
            return try {
                valueOf(method.uppercase())
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException("Unknown HTTP method: $method", e)
            }
        }
    }
}
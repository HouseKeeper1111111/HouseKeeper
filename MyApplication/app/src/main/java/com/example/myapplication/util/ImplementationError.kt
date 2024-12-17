package com.example.myapplication.util

/**
 * Indicates that the application is in a state which is illegal according to its design. This is
 * probably caused by an implementation error.
 *
 * @author Felix Wiemuth
 */
class ImplementationError : RuntimeException {
    constructor()

    constructor(message: String?) : super(message)

    constructor(message: String?, cause: Throwable?) : super(message, cause)

    constructor(cause: Throwable?) : super(cause)
}
package dk.lessor.danlon.events

interface Event<T> {
    fun get(): T
}
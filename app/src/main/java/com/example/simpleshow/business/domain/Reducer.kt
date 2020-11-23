package com.example.simpleshow.business.domain

class Reducer<T>(private val reduce: T.() -> T) {
    operator fun invoke(t: T) = t.reduce()
}
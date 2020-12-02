package com.example.simpleshow.util

enum class MeasurementUnit(val value: String) {

    METRIC("metric"), IMPERIAL("imperial");

    override fun toString(): String {
        return value
    }
}
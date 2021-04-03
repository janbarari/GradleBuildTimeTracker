package com.github.janbarari.gradlebuildtimetracker

data class BuildReport(
    val duration: Long,
    val path: String
)
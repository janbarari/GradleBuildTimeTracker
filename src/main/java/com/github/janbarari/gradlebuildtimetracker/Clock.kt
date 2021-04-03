package com.github.janbarari.gradlebuildtimetracker

import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

object Clock {

    fun now(): ZonedDateTime {
        return ZonedDateTime.now()
    }

    fun nowInMillis(): Long {
        return now().toInstant().toEpochMilli()
    }

    fun diffFromNow(previousInMillis: Long): Long {
        return nowInMillis() - previousInMillis
    }

    fun formatMillis(value: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(value)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(value) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(value))
        val seconds = TimeUnit.MILLISECONDS.toSeconds(value) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(value))
        var milliseconds = TimeUnit.MILLISECONDS.toMillis(value) - TimeUnit.MINUTES.toMillis(TimeUnit.MILLISECONDS.toSeconds(value))
        if (milliseconds < 0) {
            milliseconds = 0
        }
        return when {
            hours > 0 -> {
                String.format("%02dh %02dm", hours, minutes)
            }
            minutes > 0 -> {
                String.format("%02dm %02ds", minutes, seconds)
            }
            seconds > 0 -> {
                String.format("%02ds", seconds)
            }
            else -> {
                String.format("%02dms", milliseconds)
            }
        }
    }

}
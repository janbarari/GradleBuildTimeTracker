package com.github.janbarari.gradlebuildtimetracker

import com.opencsv.CSVReader
import com.opencsv.CSVWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object BuildTimeExport {

    fun saveBuildTime(buildTime: Long) {
        var file = File(Constants.directoryName)
        if (file.exists().not()) {
            file.mkdir()
        }
        file = File("${Constants.directoryName}/${Constants.fileName}")
        try {
            val outputFile = FileWriter(file, true)
            val writer = CSVWriter(outputFile)
            writer.writeNext(arrayOf(Clock.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), buildTime.toString()))
            writer.close()
        } catch (e: Exception) {
        }
    }

    fun getAll(): Long {
        try {
            val file = File("${Constants.directoryName}/${Constants.fileName}")
            val fileReader = FileReader(file)
            val csvReader = CSVReader(fileReader)
            var total = 0L

            var line: Array<String>?
            do {
                line = csvReader.readNext()
                if (line == null || line.isEmpty()) {
                    break
                }
                total += line[1].toLong()
            } while (true)
            return total
        } catch (e: java.io.IOException) {
            return 0L
        }
    }

    fun getYear(): Long {
        try {
            val currentYearStartTime = Clock.now().minusYears(1)
            val file = File("${Constants.directoryName}/${Constants.fileName}")
            val fileReader = FileReader(file)
            val csvReader = CSVReader(fileReader)
            var total = 0L
            var line: Array<String>?
            do {
                line = csvReader.readNext()
                if (line == null || line.isEmpty()) {
                    break
                }
                if (ZonedDateTime.parse(line[0]).isAfter(currentYearStartTime)) {
                    total += line[1].toLong()
                }
            } while (true)
            return total
        } catch (e: java.io.IOException) {
            return 0L
        }
    }

    fun getMonth(): Long {
        try {
            val currentYearStartTime = Clock.now().minusMonths(1)
            val file = File("${Constants.directoryName}/${Constants.fileName}")
            val fileReader = FileReader(file)
            val csvReader = CSVReader(fileReader)
            var total = 0L
            var line: Array<String>?
            do {
                line = csvReader.readNext()
                if (line == null || line.isEmpty()) {
                    break
                }
                if (ZonedDateTime.parse(line[0]).isAfter(currentYearStartTime)) {
                    total += line[1].toLong()
                }
            } while (true)
            return total
        } catch (e: java.io.IOException) {
            return 0L
        }
    }

    fun getDay(): Long {
        try {
            val currentYearStartTime = Clock.now().minusDays(1)
            val file = File("${Constants.directoryName}/${Constants.fileName}")
            val fileReader = FileReader(file)
            val csvReader = CSVReader(fileReader)
            var total = 0L
            var line: Array<String>?
            do {
                line = csvReader.readNext()
                if (line == null || line.isEmpty()) {
                    break
                }
                if (ZonedDateTime.parse(line[0]).isAfter(currentYearStartTime)) {
                    total += line[1].toLong()
                }
            } while (true)
            return total
        } catch (e: java.io.IOException) {
            return 0L
        }
    }

}
package com.github.janbarari.gradlebuildtimetracker

object AnalysisPrinter {

    private const val caption = "Made with ❤ for developers"
    private const val repoLink = "https://github.com/janbarari/GradleBuildTimeTracker"
    private const val defaultSpaceBetweenPathAndDuration = 5
    var maximumLineLength = 0

    fun print(reports: ArrayList<BuildReport>, buildTime: Long) {

        maximumLineLength = defaultSpaceBetweenPathAndDuration

        if (reports.isEmpty()) return

        val maximumDurationTextLength = reports.sortedByDescending { it.duration }.first().duration.toString().length
        val maximumTaskPathTextLength = reports.sortedByDescending { it.path.length }.first().path.length
        maximumLineLength += maximumDurationTextLength + maximumTaskPathTextLength

        if (maximumLineLength < (caption.length + repoLink.length + 5)) {
            maximumLineLength += ((caption.length + repoLink.length + 5) - maximumLineLength)
        }

        var tasksBuildTime = 0L
        reports.forEach { task -> tasksBuildTime += task.duration }

        val unTrackedBuildTime = buildTime - tasksBuildTime
        val totalBuildTime = tasksBuildTime + unTrackedBuildTime

        printFirstLine()
        printLine("Build Analysis", "")
        printBreakLine()
        reports.sortedByDescending { it.duration }.filter { it.duration > 0 }.forEach {
            printLine(it.path, Clock.formatMillis(it.duration))
        }
        printLine(":untracked", Clock.formatMillis(unTrackedBuildTime))
        printBreakLine('-')
        printLine("Total", Clock.formatMillis(totalBuildTime))
        printLine("Daily", Clock.formatMillis(BuildTimeExport.getDay()))
        printLine("Monthly", Clock.formatMillis(BuildTimeExport.getMonth()))
        printLine("Yearly", Clock.formatMillis(BuildTimeExport.getYear()))
        printLine("All-the-time", Clock.formatMillis(BuildTimeExport.getAll()))
        printLastLine()

        printFirstLine()
        printLine("GradleBuildTimeTracker", "")
        printBreakLine()
        printLine("", repoLink)
        printLine("", "Hit the star(★) button if you enjoyed ☝")
        printBreakLine(' ')
        printLine("", caption)
        printLine("", "by MJ")
        printLastLine()
    }

    private fun printFirstLine() {
        val output = StringBuilder()
        output.append("\n")
        output.append("┌")
        for (range in 0..(maximumLineLength)) {
            output.append("─")
        }
        output.append("┐")
        println(output.toString())
    }

    private fun printLastLine() {
        val output = StringBuilder()
        output.append("└")
        for (range in 0..(maximumLineLength)) {
            output.append("─")
        }
        output.append("┘")
        println(output.toString())
    }

    private fun printLine(title: String, value: String) {
        val output = StringBuilder()
        output.append("│")
        output.append(" ")
        output.append(title)
        val remainingEmptyCharactersCount = maximumLineLength - title.length - value.length - 1
        for (i in 0 until remainingEmptyCharactersCount) {
            output.append(" ")
        }
        output.append(value)
        output.append(" ")
        output.append("│")
        println(output.toString())
    }

    private fun printBreakLine(char: Char = '─') {
        val output = StringBuilder()
        output.append("│")
        for (range in 0..(maximumLineLength)) {
            output.append(char)
        }
        output.append("│")
        println(output.toString())
    }

}

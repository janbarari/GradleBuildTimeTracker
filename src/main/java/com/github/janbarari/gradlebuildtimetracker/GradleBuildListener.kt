package com.github.janbarari.gradlebuildtimetracker

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState

class GradleBuildListener : TaskExecutionListener, BuildListener {
    private var untrackedStartTimestamp = Clock.nowInMillis()
    private var startTimestamp: Long = 0L
    private var reports: ArrayList<BuildReport> = arrayListOf()

    override fun beforeExecute(task: Task) {
        startTimestamp = Clock.nowInMillis()
    }

    override fun afterExecute(task: Task, state: TaskState) {
        reports.add(BuildReport(Clock.diffFromNow(startTimestamp), task.path))
    }

    override fun settingsEvaluated(p0: Settings) {

    }

    override fun projectsLoaded(p0: Gradle) {

    }

    override fun projectsEvaluated(p0: Gradle) {
    }

    override fun buildFinished(result: BuildResult) {

        var buildTime = 0L
        reports.forEach { buildTime += it.duration }
        buildTime += (Clock.diffFromNow(untrackedStartTimestamp) - buildTime)
        BuildTimeExport.saveBuildTime(buildTime)

        AnalysisPrinter.print(reports, buildTime)
    }

}
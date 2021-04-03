package com.github.janbarari.gradlebuildtimetracker

import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleBuildTimeTrackerPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.gradle.addBuildListener(GradleBuildListener())
    }
}
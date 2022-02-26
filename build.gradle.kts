
plugins {
    kotlin("jvm") version "1.4.21"
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.12.0"
    id("com.github.janbarari.gradlebuildtimetracker") version "1.0.1"
}

group = "com.github.janbarari"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly(gradleApi())
    implementation("com.opencsv:opencsv:5.5.2")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

pluginBundle {
    website = "https://github.com/janbarari/GradleBuildTimeTracker/"
    vcsUrl = "https://github.com/janbarari/GradleBuildTimeTracker.git"
    tags = listOf("gradle", "build", "time", "tracker")
    version = "1.0.1"
}

gradlePlugin {
    plugins {
        create("GradleBuildTimeTrackerPlugin") {
            id = "com.github.janbarari.gradlebuildtimetracker"
            displayName = "Gradle Build Time Tracker"
            description = "A lightweight console tool that shows you how much time you spent at Gradle build process. Simple analytics tells you time spent for the day, month, year, and also you can export it as a CSV file."
            implementationClass = "com.github.janbarari.gradlebuildtimetracker.GradleBuildTimeTrackerPlugin"
        }
    }
}
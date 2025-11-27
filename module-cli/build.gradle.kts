plugins {
    application
}

dependencies {
    implementation("org.fusesource.jansi:jansi:2.4.2")

    implementation(project(":module-core"))
}

application {
    mainClass = "by.gstu.project.cli.App"
}

tasks.run {
    standardInput = System.`in`
    jvmArgs("-Dfile.encoding=UTF-8") 
}

java { 
    toolchain {
        languageVersion = JavaLanguageVersion.of(24) 
    }
}
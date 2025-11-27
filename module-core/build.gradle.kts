plugins {
    java
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}

tasks.test {
    useJUnit() 
}

java { 
    toolchain {
        languageVersion = JavaLanguageVersion.of(24) 
    }
}
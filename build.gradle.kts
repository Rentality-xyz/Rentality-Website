import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    val kotlinVersion: String by System.getProperties()
    kotlin("plugin.serialization") version kotlinVersion
    kotlin("js") version kotlinVersion
    val kvisionVersion: String by System.getProperties()
    id("io.kvision") version kvisionVersion
}

version = "1.55"
group = "com.eigenmethod"

repositories {
    mavenCentral()
    mavenLocal()
}

// Versions
val kotlinVersion: String by System.getProperties()
val kvisionVersion: String by System.getProperties()
val serializationVersion: String by System.getProperties()
val coroutinesVersion: String by System.getProperties()
val wrappersPREVersion: String by System.getProperties()

val webDir = file("src/main/web")

kotlin {
    js(IR) {
        browser {
            runTask {
                outputFileName = "main.bundle.js"
                sourceMaps = false
                devServer = KotlinWebpackConfig.DevServer(
                    open = false,
                    port = 3000,
                    proxy = mutableMapOf(
                        "/kv/*" to "http://localhost:8080",
                        "/kvws/*" to mapOf("target" to "ws://localhost:8080", "ws" to true)
                    ),
                    static = mutableListOf("$buildDir/processedResources/js/main")
                )
            }
            webpackTask {
                outputFileName = "main.bundle.js"
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        binaries.executable()
    }
    sourceSets["main"].dependencies {
        implementation("io.kvision:kvision:$kvisionVersion")
        implementation("io.kvision:kvision-state:$kvisionVersion")
        implementation("io.kvision:kvision-routing-navigo-ng:$kvisionVersion")
        implementation("io.kvision:kvision-redux-kotlin:$kvisionVersion")
        implementation("io.kvision:kvision-pace:$kvisionVersion")

        implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
        implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

        // https://mvnrepository.com/artifact/org.jetbrains.kotlin-wrappers/kotlin-redux
        implementation("org.jetbrains.kotlin-wrappers:kotlin-redux:4.1.2$wrappersPREVersion")
        // https://mvnrepository.com/artifact/org.jetbrains.kotlin-wrappers/kotlin-extensions
        implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1$wrappersPREVersion")
        // https://mvnrepository.com/artifact/org.jetbrains.kotlin-wrappers/kotlin-js-js
        implementation("org.jetbrains.kotlin-wrappers:kotlin-js-js:1.0.0$wrappersPREVersion")

        //Coroutines & serialization
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

        implementation(npm("ethers", "5.7.0"))
        implementation(npm("axios", "1.4.0"))
    }
    sourceSets["test"].dependencies {
        implementation(kotlin("test-js"))
        implementation("io.kvision:kvision-testutils:$kvisionVersion")
    }
    sourceSets["main"].resources.srcDir(webDir)
}

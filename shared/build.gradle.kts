plugins {
    kotlin("multiplatform") version "1.9.20"
}

kotlin {
    android {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }
    ios {
        binaries.framework {
            baseName = "Shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("com.squareup.okhttp3:okhttp:4.12.0") // For WebSocket
            }
        }
        
        val androidMain by getting {
            dependencies {
                implementation("com.squareup.okhttp3:okhttp:4.12.0")
            }
        }
        
        val iosMain by getting {
            dependencies {
                implementation("com.squareup.okhttp3:okhttp:4.12.0")
            }
        }
    }
}
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.0-alpha05").apply(false)
    id("com.android.library").version("8.0.0-alpha05").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
}

buildscript {
    dependencies {
        with(Deps.Gradle) {
            classpath(shadow)
            classpath(kotlinter)
            classpath(gradleVersionsPlugin)
            classpath("com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:${Versions.kmpNativeCoroutinesVersion}")
            classpath(realmPlugin)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}



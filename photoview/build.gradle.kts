plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.sprouts.photoview"
    compileSdk = 35

    defaultConfig {
//        applicationId = "com.sprouts.photoview"
        minSdk = 26
        targetSdk = 35
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


}



afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                group = "io.github.sprouts-clark"
                artifactId = "PhotoView"
                version = "1.0.0"

                // Adds javadocs and sources as separate jars.
                artifact(tasks["androidJavadocsJar"])
                artifact(tasks["sourceJar"])

                pom {
                    name.set("PhotoView")
                    description.set("A simple ImageView that support zooming, both by Multi-touch gestures and double-tap.")
                    url.set("https://github.com/Baseflow/PhotoView")
                    licenses {
                        license {
                            name = "The Apache License, Version 2.0"
                            url = "http://www.apache.org/licenses/LICENSE-2.0.txt"

                        }
                    }
                    developers {
                        developer {
                            id = "Sprouts-Clark"
                            name = "Clark"
                        }

                    }
                    scm {
                        connection.set("scm:git@github.com/chrisbanes/PhotoView.git")
                        developerConnection.set("scm:git@github.com/chrisbanes/PhotoView.git")
                        url.set("https://github.com/chrisbanes/PhotoView")
                    }
                }
            }
        }
    }
}


tasks.register<Javadoc>("androidJavadocs") {
    // 注册生成 Javadoc 的任务
    source = files(android.sourceSets["main"].java.srcDirs).asFileTree
    // 将 Android 的引导类路径添加到 Javadoc 类路径中
    classpath += files(android.bootClasspath.joinToString(File.pathSeparator))
    // 遍历所有库变体
    android.libraryVariants.all { variant ->
        if (variant.name == "release") {
            classpath += variant.javaCompileProvider.get().classpath
        }
        true
    }
    exclude("**/R.html", "**/R.*.html", "**/index.html")
}


// 注册生成 Javadoc Jar 包的任务，依赖于 androidJavadocs 任务
tasks.register<Jar>("androidJavadocsJar") {
    dependsOn("androidJavadocs")
    // 设置归档文件的分类器
    archiveClassifier.set("javadoc")
    // 使用 tasks.named 获取类型安全的 Javadoc 任务引用
    from(tasks.named<Javadoc>("androidJavadocs").get().destinationDir)
}

// 注册生成源文件 Jar 包的任务
tasks.register<Jar>("sourceJar") {
    // 指定 Jar 包的内容来源为 main 源集的 Java 源文件目录
    from(android.sourceSets["main"].java.srcDirs)
    // 设置归档文件的分类器
    archiveClassifier.set("sources")
}
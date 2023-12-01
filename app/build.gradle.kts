import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.clementcorporation.stockmarketapp"
    compileSdk = 34
    val localPropertiesFile = rootProject.file("local.properties")
    val localProperties = Properties()
    localProperties.load(FileInputStream(localPropertiesFile))
    defaultConfig {
        applicationId = "com.clementcorporation.stockmarketapp"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "API_KEY", localProperties["API_KEY"] as String)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val lifecycleVersion = "2.6.2"
    val activityVersion = "1.8.1"
    val retrofitVersion = "2.9.0"
    val roomVersion = "2.6.0"
    val hiltVersion = "1.1.0"
    val moshiVersion = "1.9.2"

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    //Moshi
    implementation ("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")
    //Desugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    // OpenCSV
    implementation("com.opencsv:opencsv:5.5.2")
    // Compose Nav Destinations
    implementation ("io.github.raamcosta.compose-destinations:core:1.1.2-beta")
    ksp ("io.github.raamcosta.compose-destinations:ksp:1.1.2-beta")
    // Coil
    implementation("io.coil-kt:coil-compose:1.4.0")
    // Accompanist
    implementation("com.google.accompanist:accompanist-flowlayout:0.17.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.2-alpha")
    // Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.48")
    implementation ("androidx.hilt:hilt-navigation-compose:$hiltVersion")
    ksp("androidx.hilt:hilt-compiler:$hiltVersion")
    ksp("com.google.dagger:hilt-android-compiler:2.47")
    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")
    //Standard Android Libraries
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")
    implementation ("androidx.compose.material:material:1.5.4")
    implementation ("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0-rc01")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:$activityVersion")
    implementation ("androidx.activity:activity-ktx:$activityVersion")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
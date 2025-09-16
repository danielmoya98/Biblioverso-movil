plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.library"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.library"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        vectorDrawables { useSupportLibrary = true }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures { viewBinding = true }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.swiperefreshlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Navigationñ
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.0")

// Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
// Coil (imágenes de portadas)
    implementation("io.coil-kt:coil:2.7.0")
// RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
// Swipe to Refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("com.tbuonomo:dotsindicator:5.1.0")
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    // Converter (puedes usar Gson, Moshi o Kotlinx Serialization)
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // OkHttp (Cliente base)
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // Logging interceptor (muy útil para debug de requests/responses)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")



}
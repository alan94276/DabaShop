plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.dabashop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dabashop"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.github.bumptech.glide:glide:4.16.0")

    apply(plugin = "dagger.hilt.android.plugin")
    apply(plugin = "kotlin-kapt")
    implementation("com.github.leandroborgesferreira:loading-button-android:2.3.0")

    implementation("io.github.vejei.viewpagerindicator:viewpagerindicator:1.0.0-alpha.1")

    implementation("com.shuhart.stepview:stepview:1.5.1")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")

    implementation("com.google.dagger:hilt-android:2.38.1")

    val nav_version = "2.7.5"
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")



// Dagger Android
    api ("com.google.dagger:dagger-android:2.37")
    api ("com.google.dagger:dagger-android-support:2.37")
   kapt ("com.google.dagger:dagger-android-processor:2.37")

// Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.48.1")
   kapt ("com.google.dagger:hilt-android-compiler:2.48.1")


}

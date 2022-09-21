object Versions {
    const val CORE_KTX = "1.8.0"
    const val APP_COMPAT = "1.5.1"
    const val CONSTRAINT = "2.1.4"
    const val LIFECYCLE_KTX = "2.5.1"
    const val ACTIVITY_KTX = "1.5.1"
    const val FRAGMENT_KTX = "1.5.2"
    const val PREFERENCE_KTX = "1.2.0"
    const val PAGING = "3.1.1"

    const val MATERIAL = "1.6.1"

    const val RETROFIT = "2.9.0"
    const val OKHTTP = "4.10.0"

    const val RXJAVA = "3.1.5"
    const val RXANDROID = "3.0.0"

    const val GLIDE = "4.13.2"

    const val HILT = "2.43.2"

    const val JUNIT = "4.13.2"
    const val ANDROID_JUNIT = "1.1.3"
    const val ESPRESSO_CORE = "3.4.0"
    const val ROBOLECTRIC = "4.8.2"
}

object AndroidX {
    const val CORE_KTX                = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APP_COMPAT              = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"

    const val ACTIVITY_KTX            = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
    const val FRAGMENT_KTX            = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"

    const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}"
    const val LIFECYCLE_LIVEDATA_KTX  = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_KTX}"

    const val PREFERENCE              = "androidx.preference:preference-ktx:${Versions.PREFERENCE_KTX}"
    const val CONSTRAINT              = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT}"

    const val PAGING_KTX              = "androidx.paging:paging-runtime-ktx:3.1.1:${Versions.PAGING}"
    const val PAGING_RXJAVA           = "androidx.paging:paging-rxjava3:${Versions.PAGING}"
    const val PAGING_COMMON           = "androidx.paging:paging-common-ktx:${Versions.PAGING}"
}

object Google {
    const val HILT_ANDROID          = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_CORE             = "com.google.dagger:hilt-core:${Versions.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
}

object Libraries {
    const val RETROFIT                   = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON    = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val RETROFIT_ADAPTER           = "com.squareup.retrofit2:adapter-rxjava3:${Versions.RETROFIT}"

    const val OKHTTP                     = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"

    const val GLIDE                      = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER             = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"

    const val RX_ANDROID                 = "io.reactivex.rxjava3:rxandroid:${Versions.RXANDROID}"
    const val RX_JAVA                    = "io.reactivex.rxjava3:rxjava:${Versions.RXJAVA}"
}
object UnitTest {
    const val JUNIT         = "junit:junit:${Versions.JUNIT}"
    const val ROBOLECTRIC   = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}"
}

object AndroidTest {
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}


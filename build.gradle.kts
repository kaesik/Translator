plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.native.cocoapods) apply false
    alias(libs.plugins.ksp) apply false

    alias(libs.plugins.compose.compiler) apply false

    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.sqldelight) apply false
}

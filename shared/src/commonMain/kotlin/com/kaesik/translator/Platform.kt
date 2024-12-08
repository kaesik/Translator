package com.kaesik.translator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.alramlawi.museum

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package jf.janice.equall

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
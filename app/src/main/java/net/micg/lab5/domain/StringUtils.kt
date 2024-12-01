package net.micg.lab5.domain

object StringUtils {
    fun String.camelcase(): String {
        if (this.isEmpty()) return this
        return this[0].uppercase() + this.substring(1).lowercase()
    }
}
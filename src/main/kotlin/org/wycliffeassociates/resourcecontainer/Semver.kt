package org.wycliffeassociates.resourcecontainer

import kotlin.math.max



internal object Semver {

    
    fun gt(v1: String, v2: String): Boolean {
        return compare(v1, v2) == 1
    }

    
    fun lt(v1: String, v2: String): Boolean {
        return compare(v1, v2) == -1
    }

    
    fun eq(v1: String, v2: String): Boolean {
        return compare(v1, v2) == 0
    }

    
    fun compare(v1: String, v2: String): Int {
        val ver1 = Version(v1)
        val ver2 = Version(v2)

        val max = max(ver1.size, ver2.size)
        for (i in 0 until max) {
            if (ver1.isWild(i) || ver2.isWild(i)) continue
            if (ver1[i] > ver2[i]) return 1
            if (ver1[i] < ver2[i]) return -1
        }
        return 0
    }

    
    private class Version(v: String) {
        private val slices = v.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }

        val size: Int = slices.size

        
        operator fun get(index: Int): Int {
            if (index >= 0 && index < slices.size) {
                val value = clean(slices[index])
                return Integer.parseInt(value)
            } else {
                return 0
            }
        }

        
        fun isWild(index: Int): Boolean =
            slices.isNotEmpty() && clean(slices[index.coerceIn(0, slices.size - 1)]) == "*"

        
        private fun clean(s: String): String {
            val cleaned = s.replace("[^\\d\\*]".toRegex(), "").trim { it <= ' ' }
            return if (cleaned.isEmpty()) {
                "0"
            } else {
                cleaned
            }
        }
    }
}
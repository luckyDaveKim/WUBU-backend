package com.wubu.api.common.web.model

enum class Balance(val description: String) {

    UP("up"),
    EQUAL("equal"),
    DOWN("down");

    companion object {
        fun <T : Number> compare(target: T, other: T): Balance {
            val num1 = target.toDouble()
            val num2 = other.toDouble()

            return if (num1 == num2) {
                EQUAL
            } else if (num1 > num2) {
                UP
            } else {
                DOWN
            }
        }
    }
}

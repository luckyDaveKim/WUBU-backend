package com.wubu.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WubuApplication

fun main(args: Array<String>) {
    runApplication<WubuApplication>(*args)
}

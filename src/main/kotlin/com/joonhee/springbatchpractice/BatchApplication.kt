package com.joonhee.springbatchpractice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
class BatchApplication

fun main(args: Array<String>) {
    val applicationContext = runApplication<BatchApplication>(*args)
    val exitCode = SpringApplication.exit(applicationContext)
    exitProcess(exitCode)
}

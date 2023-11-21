package com.joonhee.springbatchpractice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import kotlin.system.exitProcess

@SpringBootApplication
@EnableAsync
@EnableScheduling
class BatchApplication

fun main(args: Array<String>) {
    val applicationContext = runApplication<BatchApplication>(*args)
//    val exitCode = SpringApplication.exit(applicationContext)
//    exitProcess(exitCode)
}

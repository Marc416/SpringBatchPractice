package com.joonhee.springbatchpractice.controller

import com.joonhee.springbatchpractice.service.JobService
import org.springframework.batch.core.Job
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/job")
class JobController(
    val jobLauncher: JobLauncher,

    @Qualifier("job1")      // job 주입이 여러개이기 때문에 Qualifier로 명시해준다
    val job1: Job,

    @Qualifier("job2")
    val job2: Job,
    val jobService :JobService
) {

    @GetMapping("/start/{jobName}")
    fun startJob(
        @PathVariable jobName: String
    ): String {

        jobService.startJob(jobName)

        return "Job Started"
    }
}
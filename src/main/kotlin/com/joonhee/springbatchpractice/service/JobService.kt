package com.joonhee.springbatchpractice.service

import com.joonhee.springbatchpractice.request.JobParamsRequest
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class JobService(
    private val jobLauncher: org.springframework.batch.core.launch.JobLauncher,
    private val job1: org.springframework.batch.core.Job,
    private val job2: org.springframework.batch.core.Job
) {
    @Async
    fun startJob(jobName:String) {
        val params = HashMap<String, JobParameter>()
        params["currentTime"] = JobParameter(System.currentTimeMillis())
        val jobParameters = JobParameters(params)
        var jobExecution:JobExecution? =null
        try {
            if (jobName== "FirstJob") {
                jobExecution=jobLauncher.run(job1, jobParameters)
            } else if (jobName == "SecondJob") {
                jobExecution=jobLauncher.run(job2, jobParameters)
            }
            println("Job Execution: ${jobExecution?.id}")

        }catch (e: Exception) {
            println("Exception occurred while running $jobName")
        }
    }

    @Async
    fun startJobWithBodyData(jobName:String, jobParams:List<JobParamsRequest>) {
        val params = HashMap<String, JobParameter>()
        jobParams.forEach {
            params[it.paramKey] = JobParameter(it.paramValue)
        }
        params["currentTime"] = JobParameter(System.currentTimeMillis())
        val jobParameters = JobParameters(params)
        var jobExecution:JobExecution? =null
        try {
            if (jobName== "FirstJob") {
                jobExecution=jobLauncher.run(job1, jobParameters)
            } else if (jobName == "SecondJob") {
                jobExecution=jobLauncher.run(job2, jobParameters)
            }
            println("Job Execution: ${jobExecution?.id}")

        }catch (e: Exception) {
            println("Exception occurred while running $jobName")
        }
    }
}
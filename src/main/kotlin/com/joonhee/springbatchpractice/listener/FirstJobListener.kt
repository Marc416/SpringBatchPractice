package com.joonhee.springbatchpractice.listener

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.stereotype.Component

@Component
class FirstJobListener:JobExecutionListener {
    override fun beforeJob(jobExecution: JobExecution) {
        println("Before Job" + jobExecution.jobInstance.jobName)
        println("Params" + jobExecution.jobParameters)
        println("Exec Context" + jobExecution.executionContext)

        jobExecution.executionContext.put("jec", "jec value")
    }

    override fun afterJob(jobExecution: JobExecution) {
        println("After Job" + jobExecution.jobInstance.jobName)
        println("Params" + jobExecution.jobParameters)
        println("Exec Context" + jobExecution.executionContext)
    }
}
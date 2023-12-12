package com.joonhee.springbatchpractice.listener

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.stereotype.Component

@Component
class JobListener:JobExecutionListener {
    override fun beforeJob(jobExecution: JobExecution) {
        println("잡 시작합니다")
        println("Before Job" + jobExecution.jobInstance.jobName)
        println("Params" + jobExecution.jobParameters)
        println("Exec Context" + jobExecution.executionContext)

        jobExecution.executionContext.put("jec", "jec value")
    }

    override fun afterJob(jobExecution: JobExecution) {
        println("After Job" + jobExecution.jobInstance.jobName)
        println("Params" + jobExecution.jobParameters)
        println("Exec Context" + jobExecution.executionContext)
        println("잡 종료합니다")
    }
}
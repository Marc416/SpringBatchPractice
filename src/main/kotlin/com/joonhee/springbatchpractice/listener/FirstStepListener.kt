package com.joonhee.springbatchpractice.listener

import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.stereotype.Component

@Component
class FirstStepListener: StepExecutionListener{
    override fun beforeStep(stepExecution: StepExecution) {
        println("Before Step" + stepExecution.stepName)
        println("Params" + stepExecution.jobParameters)
        println("Exec Context" + stepExecution.executionContext)

        stepExecution.executionContext.put("sec", "sec value")
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus? {
        println("After Step" + stepExecution.stepName)
        println("Params" + stepExecution.jobParameters)
        println("Exec Context" + stepExecution.executionContext)
        return null
    }
}
package com.joonhee.springbatchpractice.listener

import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.stereotype.Component

@Component
class StepListener: StepExecutionListener{
    override fun beforeStep(stepExecution: StepExecution) {
        println("스텝 시작합니다")
        println("Before Step" + stepExecution.stepName)
        println("Params" + stepExecution.jobParameters)
        println("Exec Context" + stepExecution.executionContext)

        stepExecution.executionContext.put("sec", "sec value")
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus? {
        println("After Step" + stepExecution.stepName)
        println("Params" + stepExecution.jobParameters)
        println("Exec Context" + stepExecution.executionContext)
        println("스텝 종료합니다")
        return null
    }
}
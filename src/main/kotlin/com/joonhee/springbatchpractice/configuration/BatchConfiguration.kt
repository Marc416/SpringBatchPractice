package com.joonhee.springbatchpractice.configuration

import com.joonhee.springbatchpractice.listener.FirstJobListener
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableBatchProcessing
class BatchConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,           // 의존성 주입 (빨간줄은 intellij 에서만 뜨는거임)
    private val stepBuilderFactory: StepBuilderFactory,
    private val firstJobListener: FirstJobListener,             // job listener 추가함으로써 job context 사용가능

    ) {

    @Bean
    fun job1(): Job {
        return jobBuilderFactory.get("FirstJob")
            .incrementer(RunIdIncrementer())
            .start(step1())
            .next(step2())
            .listener(firstJobListener)
            .build()
    }

    @Bean
    fun step1(): Step {
        return stepBuilderFactory.get("Step1").tasklet(firstTask()).build()
    }

    @Bean
    fun step2(): Step {
        return stepBuilderFactory.get("Step2").tasklet(secondTask()).build()
    }

    private fun firstTask(): Tasklet {
        return Tasklet { contribution, chunkContext ->
            println("First Task")
            RepeatStatus.FINISHED
        }
    }

    private fun secondTask(): Tasklet {
        return Tasklet { contribution, chunkContext ->
            println("Second Task")
            println(chunkContext.stepContext.jobExecutionContext)
            RepeatStatus.FINISHED
        }
    }

}


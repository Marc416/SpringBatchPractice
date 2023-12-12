package com.joonhee.springbatchpractice.configuration

import com.joonhee.springbatchpractice.listener.JobListener
import com.joonhee.springbatchpractice.listener.StepListener
import com.joonhee.springbatchpractice.processor.FirstItemProcessor
import com.joonhee.springbatchpractice.reader.FirstItemReader
import com.joonhee.springbatchpractice.service.SecondTasklet
import com.joonhee.springbatchpractice.writer.FirstItemWriter
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
    private val jobListener: JobListener,             // job listener 추가함으로써 job context 사용가능
    private val stepListener: StepListener,

    private val firstItemReader: FirstItemReader,
    private val firstItemProcessor: FirstItemProcessor,
    private val firstItemWriter: FirstItemWriter,

    private val secondTasklet: SecondTasklet
    ) {

    @Bean
    fun job1(): Job {
        return jobBuilderFactory.get("FirstJob")
            .incrementer(RunIdIncrementer())
            .start(step1())
            .next(step2())
            .build()
    }

    @Bean
    fun job2(): Job {
        return jobBuilderFactory.get("SecondJob")
            .incrementer(RunIdIncrementer())
            .start(step1())
            .listener(jobListener)
            .build()
    }

    @Bean
    fun job3(): Job {
        return jobBuilderFactory.get("ThirdJob")
            .incrementer(RunIdIncrementer())
            .start(firstChunkStep())
            .build()
    }

    @Bean
    fun step1(): Step {
        return stepBuilderFactory.get("Step1")
            .tasklet(firstTask())
            .listener(stepListener)
            .build()
    }

    @Bean
    fun step2(): Step {
        return stepBuilderFactory.get("Step2").tasklet(secondTasklet).build()
    }

    @Bean
    fun firstChunkStep():Step{
        return stepBuilderFactory.get("First Chunk Step")
            .chunk<Int,Long>(3)
            .reader(firstItemReader)
            .processor(firstItemProcessor)
            .writer(firstItemWriter)
            .build()
    }

    private fun firstTask(): Tasklet {
        return Tasklet { contribution, chunkContext ->
            println("First Task")
            println(chunkContext.stepContext.jobExecutionContext)
            println("SEC= " +chunkContext.stepContext.stepExecutionContext)
            RepeatStatus.FINISHED
        }
    }


}


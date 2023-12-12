package com.joonhee.springbatchpractice.service

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SecondJobScheduler(
    private val jobLauncher: JobLauncher,
    @Qualifier("job2")  // job 주입이 여러개이기 때문에 Qualifier로 명시해준다(여기서는 job2)
    private val job2: Job
) {
    // 크론 표현식은 다음 사이트에서 확인 가능
    // http://www.cronmaker.com
    @Scheduled(cron = "0 0/30 * 1/1 * ?")
    fun secondJobStarter() {
        println("스케쥴링 시작")
        val params = HashMap<String, JobParameter>()
        params["currentTime"] = JobParameter(System.currentTimeMillis())
        val jobParameters = JobParameters(params)
        try{
            val jobExecution = jobLauncher.run(job2, jobParameters)
            println("Job2 Execution: ${jobExecution.id}")
        }catch (e: Exception) {
            println("Exception while starting job2")
        }
        println("스케쥴링 종료")
    }
}
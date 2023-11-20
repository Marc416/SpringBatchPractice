package com.joonhee.springbatchpractice.reader

import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component

@Component
class FirstItemReader : ItemReader<Int> {

    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    var index = 0

    override fun read(): Int? {
        println("Inside Item Reader")
        return if (index < numbers.size) {
            val number = numbers[index]
            index++
            number
        } else {
            index = 0
            // null 을 리턴하면 reader 가 끝났음을 알린다
            // 11번째 reader가 실행되지만 null을 리턴하므로 process는 실행되지 않는다
            null
        }
    }

}
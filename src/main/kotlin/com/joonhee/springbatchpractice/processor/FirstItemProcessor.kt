package com.joonhee.springbatchpractice.processor

import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class FirstItemProcessor : ItemProcessor<Int, Long> {
    override fun process(item: Int): Long? {
        println("Inside Item Processor")
        return item.toLong() + 20
    }
}
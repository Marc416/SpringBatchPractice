package com.joonhee.springbatchpractice.writer

import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
class FirstItemWriter: ItemWriter<Long> {
    // items 는 reader에서 넘어온 chunk 사이즈와 같다
    override fun write(items: MutableList<out Long>) {
        println("Inside Item Writer")
        items.forEach { println(it) }
    }
}
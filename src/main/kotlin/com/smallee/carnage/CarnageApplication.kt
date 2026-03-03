package com.smallee.carnage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarnageApplication

fun main(args: Array<String>) {
	runApplication<CarnageApplication>(*args)
}

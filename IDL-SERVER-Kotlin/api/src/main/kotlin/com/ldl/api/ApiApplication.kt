package com.ldl.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.scheduling.annotation.EnableScheduling

//@EnableFeignClients
@EnableScheduling
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = ["com.ldl.api"])
@ComponentScan(basePackages = ["com.ldl.api", "com.ldl.api.adapter", "com.ldl.global", "com.ldl.readingroom"])
@SpringBootApplication
class ApiApplication

fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}
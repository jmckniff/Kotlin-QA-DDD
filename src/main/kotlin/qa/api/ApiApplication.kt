package qa.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@ComponentScan(basePackages = arrayOf("qa.api", "qa.domain", "qa.services", "qa.persistence.inMemory"))
class ApiApplication

fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}
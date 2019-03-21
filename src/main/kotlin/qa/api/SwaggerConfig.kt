package qa.api

import com.google.common.base.Predicate
import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
    }

    // Describe your apis
    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Swagger Sample APIs")
                .description("This page lists all the rest apis for Swagger Sample App.")
                .version("1.0-SNAPSHOT")
                .build()
    }

    // Only select apis that matches the given Predicates.
    private fun paths(): Predicate<String> {
        // Match all paths except /error
        return Predicates.and(
                PathSelectors.regex("/.*"),
                Predicates.not(PathSelectors.regex("/error.*"))
        )
    }
}
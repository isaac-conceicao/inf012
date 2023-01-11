package ifba.edu.br.chamadosapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@EnableWebMvc
@Configuration
public class SwaggerConfig {

    /*@Bean
    public Docket swagget() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("ifba.edu.br.chamadosapi.controllers"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(metaData());
    }*/
	
	 @Bean
	 public Docket api() {
		 return new Docket(DocumentationType.SWAGGER_2)
				 .select()
				 .apis(RequestHandlerSelectors.any())
				 .paths(PathSelectors.any())
				 .build();
	}

    /*
     private ApiInfo metaData() {
        return new ApiInfoBuilder()
            .title("Chamados-API")
            .description("\"Spring Boot REST API\"")
            .version("1.0.0")
            .license("Apache License Version 2.0")
            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
            .build();
    }*/
}

//http://localhost:8080/swagger-ui/index.html

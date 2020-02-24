package com.freenow;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.freenow.util.LoggingInterceptor;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class FreeNowServerApplicantTestApplication extends WebMvcConfigurerAdapter
{

    public static void main(String[] args)
    {
        SpringApplication.run(FreeNowServerApplicantTestApplication.class, args);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }


    @Bean
    public Docket driverCarAPI()
    {
        List<Parameter> params = new ArrayList<>();
        params
            .add(
                new ParameterBuilder()
                    .name("Authorization")
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .required(true)
                    .build());
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Driver-Car APIs")
            .select()
            .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
            //basePackage(getClass().getPackage().getName())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(generateApiInfo())
            .globalOperationParameters(params);
    }


    @Bean
    public Docket authenticationAPI()
    {
        List<Parameter> params = new ArrayList<>();
        params
            .add(
                new ParameterBuilder()
                    .name("Authorization")
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .required(true)
                    .defaultValue("Basic YzIzZTQ3ZmEtZTI4Yi00ODEzLThhNjItNTIxMDYwZjU4ZGI5OmQ1NGNiYjViLTc5ODUtNDg3YS1iNGU2LTFmMmFlMzk5N2Q4MQ==")
                    .build());
        params
            .add(
                new ParameterBuilder()
                    .name("grant_type")
                    .modelRef(new ModelRef("string"))
                    .parameterType("query")
                    .required(true)
                    .defaultValue("client_credentials")
                    .build());
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Authorization")
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.ant("/oauth/token"))
            .build()
            .apiInfo(generateApiInfo())
            .globalOperationParameters(params);
    }


    private ApiInfo generateApiInfo()
    {
        return new ApiInfo("freenow Server Applicant Test Service", "This service is to check the technology knowledge of a server applicant for freenow.", "Version 1.0 - mw",
            "urn:tos", "career@freenow.com", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    }
}

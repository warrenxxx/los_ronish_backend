/**
 * The UserController class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.controller;

import io.unsaac.ronypsquienas.boletos_backend.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Controller
public class UserController {

    @Bean
    RouterFunction routerFunction(UserService service){
        return route(GET("/user"),service::findAll)
                .andRoute(GET("/user/{id}"),service::find)
                .andRoute(PUT("/user"),service::update)
                .andRoute(POST("/user"),service::insert)
                .andRoute(POST("/login"),service::login)
                ;
    }
}

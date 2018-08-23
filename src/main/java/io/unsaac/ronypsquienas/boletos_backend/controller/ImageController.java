/**
 * The ImageController class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.controller;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.gridfs.GridFSBucket;
import com.mongodb.reactivestreams.client.gridfs.GridFSBuckets;
import io.unsaac.ronypsquienas.boletos_backend.Service.ImageService;
import io.unsaac.ronypsquienas.boletos_backend.models.Identifier;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ImageController {


    @Autowired
    private ImageService imageHandler;


    @Bean
    public RouterFunction<?> landingRouterFunction() {
        return RouterFunctions.nest(RequestPredicates.path("/api"),
                RouterFunctions.route(RequestPredicates.GET("/images"),
                        request -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(imageHandler.findAllNames(), Identifier.class))
                        .andRoute(RequestPredicates.POST("/image/save"),
                                request -> ServerResponse.ok()
                                        .body(imageHandler.saveImage(request.bodyToFlux(Part.class)), String.class))
                        .andRoute(RequestPredicates.GET("/image/get/{id}"),
                                request -> ServerResponse.ok()
                                        .body(imageHandler.fetchImage(request.pathVariable("id")), byte[].class))
                        .andRoute(RequestPredicates.DELETE("/image/delete/{id}"),
                                request -> ServerResponse.ok()
                                        .body(imageHandler.removeImage(request.pathVariable("id")), Boolean.class))
        );
    }
}

package io.unsaac.ronypsquienas.boletos_backend.utils;

import com.mongodb.reactivestreams.client.gridfs.AsyncInputStream;
import org.springframework.core.io.buffer.DataBuffer;
import reactor.core.publisher.Flux;



public class FluxAsyncStreamConverter {

  public static AsyncInputStream convert(Flux<DataBuffer> source){
    System.out.println("ddd");
    return new FluxAsyncInputStream(source);
  }
}

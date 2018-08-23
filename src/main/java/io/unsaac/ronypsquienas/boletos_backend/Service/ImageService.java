/**
 * The ImageService class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/08/2018
 */
package io.unsaac.ronypsquienas.boletos_backend.Service;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.gridfs.GridFSBucket;

import com.mongodb.reactivestreams.client.gridfs.GridFSBuckets;
import com.mongodb.reactivestreams.client.internal.GridFSBucketImpl;
import io.unsaac.ronypsquienas.boletos_backend.models.Identifier;
import io.unsaac.ronypsquienas.boletos_backend.utils.DownloadStreamToFluxFactory;
import io.unsaac.ronypsquienas.boletos_backend.utils.FluxAsyncStreamConverter;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Service
public class ImageService {


    @Autowired
    private  GridFSBucket gridFSBucket;

    private final DownloadStreamToFluxFactory downloadStreamToFluxFactory = new DownloadStreamToFluxFactory();


    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    public GridFSBucket gridFSBucket(MongoClient reactiveMongoClient) throws Exception {
        return GridFSBuckets.create(reactiveMongoClient.getDatabase("boletos" ));
    }



    public Flux<String> saveImage(Flux<Part> multipartFile) {

        return multipartFile
                .flatMap(part -> Mono.from(gridFSBucket.uploadFromStream(part.name(),
                        FluxAsyncStreamConverter.convert(part.content()))))
                .map(ObjectId::toHexString);

    }

    public Flux<byte[]> fetchImage(String imageId) {
        return downloadStreamToFluxFactory
                .convert(gridFSBucket.openDownloadStream(getId(imageId)));
    }

    public Mono<Boolean> removeImage(String imageId) {
        return Mono.from(gridFSBucket.delete(getId(imageId)))
                .map(Objects::nonNull)
                .onErrorReturn(false);
    }

    public Flux<Identifier> findAllNames() {
        return Flux.from(gridFSBucket.find())
                .map(GridFSFile::getId)
                .map(BsonValue::asObjectId)
                .map(BsonObjectId::getValue)
                .map(ObjectId::toHexString)
                .map(Identifier::new);

    }

    private ObjectId getId(String imageId) {
        return new ObjectId(imageId);
    }
}

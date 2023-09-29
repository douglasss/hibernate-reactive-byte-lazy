package com.example;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.IOException;
import java.nio.file.Files;

@Path("/storage")
public class StorageController {

    @POST
    @WithTransaction
    public Uni<StorageFile> upload(@RestForm("file") FileUpload fileUpload) throws IOException {
        byte[] data = Files.readAllBytes(fileUpload.uploadedFile());
        StorageFile storageFile = new StorageFile();
        storageFile.data = data;
        return storageFile.persist();
    }

    @GET
    @Path("/{id}")
    @WithSession
    public Uni<byte[]> download(@PathParam("id") long id) {
        return StorageFile.<StorageFile>findById(id)
                .map(s -> s.data);
    }
}

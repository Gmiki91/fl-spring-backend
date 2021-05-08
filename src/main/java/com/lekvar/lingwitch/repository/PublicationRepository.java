package com.lekvar.lingwitch.repository;

import com.lekvar.lingwitch.model.Publication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublicationRepository extends MongoRepository<Publication, String> {
}

package com.bootcamp.businessclient.repository;

import com.bootcamp.businessclient.model.Client;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ClientRepository extends ReactiveCrudRepository<Client, String> {
  Flux<Client> findByDocumentId(String documentId);
}

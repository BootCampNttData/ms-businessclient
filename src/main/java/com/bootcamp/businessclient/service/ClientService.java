package com.bootcamp.businessclient.service;


import com.bootcamp.businessclient.model.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<Client> findAll();
    Mono<Client> create(Client client);
    Flux<Client> getByDocumentId(String documentId);
    Mono<Client> update(Client client);
    Mono<Void> deleteById(String id);
    Mono<Void> delete(Client client);
}

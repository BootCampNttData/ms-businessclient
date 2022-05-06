package com.bootcamp.businessclient.service.Impl;

import com.bootcamp.businessclient.service.ClientService;
import org.springframework.stereotype.Service;
import com.bootcamp.businessclient.model.Client;
import com.bootcamp.businessclient.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class ClientServiceImpl implements ClientService {
    public final ClientRepository repository;

    @Override
    public Mono<Client> create(Client client) {
        return repository.save(client);
    }

    @Override
    public Mono<Client> update(Client client) {
        return repository.save(client);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Void> delete(Client client) {
        return repository.delete(client);
    }

    @Override
    public Flux<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<Client> getByDocumentId(String documentId) {
        return repository.findByDocumentId(documentId);
    }
    
}

package com.bootcamp.businessclient.service.Impl.impl;

import com.bootcamp.businessclient.model.BusinessClient;
import com.bootcamp.businessclient.repository.BusinessClientRepository;
import com.bootcamp.businessclient.service.Impl.BusinessClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class BusinessClientServiceImpl implements BusinessClientService {
    public final BusinessClientRepository repository;

    @Override
    public Mono<BusinessClient> create(BusinessClient businessClient) {
        return repository.save(businessClient);
    }

    @Override
    public Mono<BusinessClient> update(BusinessClient businessClient) {
        return repository.save(businessClient);
    }

    @Override
    public Mono deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono delete(BusinessClient businessClient) {
        return repository.delete(businessClient);
    }

    @Override
    public Flux<BusinessClient> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<BusinessClient> getByRucNumber(String rucNumber) {
        return repository.findByRucNumber(rucNumber);
    }

}

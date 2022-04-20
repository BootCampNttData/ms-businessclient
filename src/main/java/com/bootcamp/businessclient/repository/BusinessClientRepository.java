package com.bootcamp.businessclient.repository;

import com.bootcamp.businessclient.model.BusinessClient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BusinessClientRepository extends ReactiveCrudRepository<BusinessClient, String> {
    Flux<BusinessClient> findByRucNumber(String ruc);
}

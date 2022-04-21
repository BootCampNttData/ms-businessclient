package com.bootcamp.businessclient.service.Impl;

import com.bootcamp.businessclient.dto.AccountAverageDTO;
import com.bootcamp.businessclient.model.BusinessClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BusinessClientService {

    Flux<BusinessClient> findAll();
    Mono<BusinessClient> create(BusinessClient businessClient);
    Flux<BusinessClient> getByRucNumber(String rucNumber);
    Mono<BusinessClient> update(BusinessClient businessClient);
    Mono<Void> deleteById(String id);
    Mono<Void> delete(BusinessClient businessClient);
    Flux<AccountAverageDTO> getDailyAverageBalance(String clientId);

}

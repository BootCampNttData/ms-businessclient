package com.bootcamp.businessclient.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.businessclient.model.BusinessClient;
import com.bootcamp.businessclient.model.DailyAverageBalanceDto;
import com.bootcamp.businessclient.service.Impl.BusinessClientService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/business-client")
@RequiredArgsConstructor
public class BusinessClientController {
    public final BusinessClientService service;
    @GetMapping
    public Flux<BusinessClient> getAll(){
        return service.findAll();
    }

    @GetMapping("/find/{rucNumber}")
    public Flux<BusinessClient> getByDocumentId(@PathVariable("rucNumber") String rucNumber){
        return service.getByRucNumber(rucNumber);
    }

    /**
     * Valida que exista por lo menos un Titular para la Compañia
     * en caso de no tener ninguno se retorna un objeto nulo
     * y no se guarda en BD
     * @param businessClient
     * @return
     */
    @PostMapping
    public Mono<BusinessClient> create(@RequestBody BusinessClient businessClient){
        if(businessClient.getAccountHolders().length > 0){
            return service.create(businessClient);
        }
        return Mono.just(new BusinessClient());
    }

    @PostMapping("/update")
    public Mono<BusinessClient> update(@RequestBody BusinessClient businessClient){
        return service.create(businessClient);
    }

    @DeleteMapping
    public Mono<BusinessClient> delete(@RequestBody BusinessClient businessClient){
        return service.delete(businessClient);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<BusinessClient> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }
    
    @GetMapping("/{id}/summaryDailyAverageBalance")
    public Flux<DailyAverageBalanceDto> getDailyAverageBalance(@PathVariable("id") Integer clientId) {
      return service.getDailyAverageBalance(clientId);
    }


}
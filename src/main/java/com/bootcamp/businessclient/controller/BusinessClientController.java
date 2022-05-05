package com.bootcamp.businessclient.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.businessclient.dto.AccountAverageDTO;
import com.bootcamp.businessclient.model.BusinessClient;
import com.bootcamp.businessclient.model.Client;
import com.bootcamp.businessclient.service.Impl.BusinessClientService;
import com.bootcamp.businessclient.service.Impl.ClientService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/businessclient")
@RequiredArgsConstructor
public class BusinessClientController {
    public final BusinessClientService service;
    public final ClientService clientService;
    @GetMapping
    public Flux<BusinessClient> getAll(){
        return service.findAll();
    }

    @GetMapping("/find/{rucNumber}")
    public Flux<BusinessClient> getByRucNumber(@PathVariable("rucNumber") String rucNumber){
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

//    @DeleteMapping
//    public Mono<Void> delete(@RequestBody BusinessClient businessClient){
//        return service.delete(businessClient);
//    }

    @DeleteMapping("/byId/{id}")
    public Mono<Void> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }
    
    @GetMapping("/{clientId}/summaryDailyAverageBalance")
    public Flux<AccountAverageDTO> getDailyAverageBalance(@PathVariable("clientId") String clientId) {
      return service.getDailyAverageBalance(clientId);
    }

    @GetMapping("/isPyme/{clientId}")
    public Mono<Boolean> isPyme(@PathVariable("clientId") String id){
        return service.isPyme(id);
    }

    /**  ****************************************************************************************************/

    @GetMapping("/client")
    public Flux<Client> getAllClients(){
        return clientService.findAll();
    }

    @GetMapping("client/find/{documentId}")
    public Flux<Client> getByDocumentId(@PathVariable("documentId") String documentId){
        return clientService.getByDocumentId(documentId);
    }

    @PostMapping("/client")
    public Mono<Client> create(@RequestBody Client client){
        return clientService.create(client);
    }

//    @DeleteMapping("/client")
//    public Mono<Void> delete(@RequestBody Client client){
//        return clientService.delete(client);
//    }

    @DeleteMapping("/client/byId/{id}")
    public Mono<Void> deleteClientById(@RequestBody String id){
        return clientService.deleteById(id);
    }



}
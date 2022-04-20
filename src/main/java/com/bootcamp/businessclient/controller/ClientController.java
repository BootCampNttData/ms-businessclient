package com.bootcamp.businessclient.controller;

import com.bootcamp.businessclient.model.Client;
import com.bootcamp.businessclient.service.Impl.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {
    public final ClientService service;
    @GetMapping
    public Flux<Client> getAll(){
        return service.findAll();
    }

    @GetMapping("/find/{documentId}")
    public Flux<Client> getByDocumentId(@PathVariable("documentId") String documentId){
        return service.getByDocumentId(documentId);
    }

    @PostMapping
    public Mono<Client> create(@RequestBody Client client){
        return service.create(client);
    }

    @PostMapping("/update")
    public Mono<Client> update(@RequestBody Client client){
        return service.create(client);
    }

    @DeleteMapping
    public Mono<Client> delete(@RequestBody Client client){
        return service.delete(client);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<Client> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }

}
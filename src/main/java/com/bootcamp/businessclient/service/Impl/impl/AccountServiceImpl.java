package com.bootcamp.businessclient.service.Impl.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.bootcamp.businessclient.dto.AccountDTO;
import com.bootcamp.businessclient.service.Impl.AccountService;
import reactor.core.publisher.Flux;

@Service
public class AccountServiceImpl implements AccountService {

  @Value("${gateway.url}")
  private String gatewayURL;

  @Override
  public Flux<AccountDTO> getAccountsDetailByBusinessClient(String businessClientId) {
    var webClient = WebClient.create(gatewayURL);
    Flux<AccountDTO> flux = webClient.get().uri("/passiveaccount/byClient/{clientId}/", businessClientId)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .exchangeToFlux(response -> {
        return response.bodyToFlux(AccountDTO.class);
      });
    return flux;
  }


}

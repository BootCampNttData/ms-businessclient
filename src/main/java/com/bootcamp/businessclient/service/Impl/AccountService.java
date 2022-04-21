package com.bootcamp.businessclient.service.Impl;

import com.bootcamp.businessclient.dto.AccountDTO;
import reactor.core.publisher.Flux;

public interface AccountService {

  Flux<AccountDTO> getAccountsDetailByBusinessClient(String businessClientId);

}

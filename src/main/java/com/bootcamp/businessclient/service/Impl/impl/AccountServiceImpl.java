package com.bootcamp.businessclient.service.Impl.impl;

import org.springframework.stereotype.Service;
import com.bootcamp.businessclient.dto.AccountDTO;
import com.bootcamp.businessclient.service.Impl.AccountService;
import reactor.core.publisher.Flux;

@Service
public class AccountServiceImpl implements AccountService {

  @Override
  public Flux<AccountDTO> getAccountsDetailByBusinessClient(String businessClientId) {
    // TODO Auto-generated method stub
    return null;
  }

  
}

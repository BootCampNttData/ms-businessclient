package com.bootcamp.businessclient.service.Impl;

import com.bootcamp.businessclient.service.AccountService;
import org.springframework.stereotype.Service;
import com.bootcamp.businessclient.dto.AccountDTO;
import reactor.core.publisher.Flux;

@Service
public class AccountServiceImpl implements AccountService {

  @Override
  public Flux<AccountDTO> getAccountsDetailByBusinessClient(String businessClientId) {
    // TODO Auto-generated method stub
    return null;
  }


  
}

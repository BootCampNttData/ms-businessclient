package com.bootcamp.businessclient.service.Impl.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.businessclient.dto.AccountAverageDTO;
import com.bootcamp.businessclient.dto.AverageByDate;
import com.bootcamp.businessclient.model.BusinessClient;
import com.bootcamp.businessclient.repository.BusinessClientRepository;
import com.bootcamp.businessclient.service.Impl.AccountService;
import com.bootcamp.businessclient.service.Impl.BusinessClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BusinessClientServiceImpl implements BusinessClientService {
 
  @Autowired
  private BusinessClientRepository repository;
  
  @Autowired
  private AccountService accountService;

  @Override
  public Mono<BusinessClient> create(BusinessClient businessClient) {
    return repository.save(businessClient);
  }

  @Override
  public Mono<BusinessClient> update(BusinessClient businessClient) {
    return repository.save(businessClient);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return repository.deleteById(id);
  }

  @Override
  public Mono<Void> delete(BusinessClient businessClient) {
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
  
  @Override
  public Flux<AccountAverageDTO> getDailyAverageBalance(String clientId) {
    
    var currentDate = LocalDate.now();
    var currentYear = currentDate.get(ChronoField.YEAR);
    var currentMonth = currentDate.get(ChronoField.MONTH_OF_YEAR);

    var flux = accountService.getAccountsDetailByBusinessClient(clientId);
    return flux
        .map(acc -> {
          var accountAverageDTO = new AccountAverageDTO();
          
          accountAverageDTO.setAccountId(acc.getAccountId());
          accountAverageDTO.setAccountType(acc.getTypeAccount());
          var averageList = new ArrayList<AverageByDate>();
          Flux.fromStream(
              IntStream.rangeClosed(1, YearMonth.of(currentYear, currentMonth)
                  .lengthOfMonth())
                  .mapToObj(i -> LocalDate.of(currentYear, currentMonth, i))
              )
              .map(date -> {
                var average = acc.getMovements().stream()
                    .filter(mov -> mov.getDate().isEqual(date))
                    .mapToDouble(mov -> mov.getAmount()).average().orElse(0d);
                return new AverageByDate(date, average);
              }).subscribe(averageList::add);
          accountAverageDTO.setAverage(averageList);
          
          return accountAverageDTO;
        });
  }


    public Mono<Boolean> isPyme(String id){
        return repository.findByRucNumberAndIsPyme(id,true)
                .map(cli -> {
                    if(cli.getIsPyme()){
                        return Boolean.TRUE;
                    }else{
                        return Boolean.FALSE;
                    }
                });
    }

}

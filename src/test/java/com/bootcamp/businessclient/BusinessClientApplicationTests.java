package com.bootcamp.businessclient;

import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.bootcamp.businessclient.dto.AccountDTO;
import com.bootcamp.businessclient.dto.MovementDTO;
import com.bootcamp.businessclient.service.AccountService;
import com.bootcamp.businessclient.service.BusinessClientService;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class BusinessClientApplicationTests {

  @Autowired
  private BusinessClientService businessClientService;
    
  @MockBean
  private AccountService accountService;
  
  private String businessClientId = "123";
  
  @BeforeAll
  public static void setup() {
    System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
  }

  @Test
  public void averageDailyBalance() {
    var fixedAccountDetail = new AccountDTO();
    fixedAccountDetail.setAccountId("1");
    fixedAccountDetail.setBalance(1400d);
    fixedAccountDetail.setTypeAccount("Fixed account");
    fixedAccountDetail.setMovements(List.of(
        new MovementDTO(LocalDate.of(2022, Month.APRIL, 10), 1500d),
        new MovementDTO(LocalDate.of(2022, Month.APRIL, 20), -100d)
     ));
    
    var savingAccountDetail = new AccountDTO();
    savingAccountDetail.setAccountId("2");
    savingAccountDetail.setBalance(0d);
    savingAccountDetail.setTypeAccount("Saving account");
    savingAccountDetail.setMovements(List.of(
        new MovementDTO(LocalDate.of(2022, Month.APRIL, 1), 200d),
        new MovementDTO(LocalDate.of(2022, Month.APRIL, 2), -100d),
        new MovementDTO(LocalDate.of(2022, Month.APRIL, 5), -100d)
     ));
    
    var currentAccountDetail = new AccountDTO();
    currentAccountDetail.setAccountId("3");
    currentAccountDetail.setBalance(1000d);
    currentAccountDetail.setTypeAccount("Current account");
    currentAccountDetail.setMovements(List.of(
        new MovementDTO(LocalDate.of(2022, Month.APRIL, 1), 1000d)
     ));
    
    var accountList = List.of(fixedAccountDetail, savingAccountDetail, currentAccountDetail);
    when(accountService.getAccountsDetailByBusinessClient(businessClientId)).thenReturn(Flux.fromIterable(accountList));
    
    var averageDailyBalance = businessClientService.getDailyAverageBalance(businessClientId);
    StepVerifier.create(averageDailyBalance).expectNextCount(1L).expectNextCount(1L).expectNextCount(1L).verifyComplete();
  }

}

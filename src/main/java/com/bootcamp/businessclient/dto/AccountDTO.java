package com.bootcamp.businessclient.dto;

import java.util.List;
import lombok.Data;

@Data
public class AccountDTO {

  private String accountId;
  private String typeAccount;
  private Double balance;
  private List<MovementDTO> movements;
  
}

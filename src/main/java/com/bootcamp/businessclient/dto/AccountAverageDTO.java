package com.bootcamp.businessclient.dto;

import java.util.List;
import lombok.Data;

@Data
public class AccountAverageDTO {

  private String accountId;
  private String accountType;
  private List<AverageByDate> average;
}

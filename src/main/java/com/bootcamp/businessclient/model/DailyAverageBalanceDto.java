package com.bootcamp.businessclient.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class DailyAverageBalanceDto {

  private LocalDate date;
  private Double averageBalance;
}

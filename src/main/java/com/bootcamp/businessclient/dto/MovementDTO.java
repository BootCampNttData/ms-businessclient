package com.bootcamp.businessclient.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementDTO {

  private LocalDate date;
  private Double amount;
}

package com.bootcamp.businessclient.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class BusinessClient {
    @Id
    private String id;
    private String rucNumber;
    private String[] accountHolders;
    private String[] signers;
    private String companyName;
    private String address;
    private String phone;
}

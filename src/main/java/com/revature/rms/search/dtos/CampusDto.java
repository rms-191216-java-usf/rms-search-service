package com.revature.rms.search.dtos;

import com.revature.rms.search.entites.campus.Address;
import com.revature.rms.search.entites.campus.Campus;
import com.revature.rms.search.entites.employee.Employee;

public class CampusDto {
    private int id;
    private String name;
    private String abbrName;
    private Address shippingAddress;
    private Employee trainingManager;
}

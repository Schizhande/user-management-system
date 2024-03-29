package com.schizhande.usermanagementsystem.service.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateRoleRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;
}

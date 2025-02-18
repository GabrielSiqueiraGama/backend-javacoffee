package com.JavaCoffee.BackEndJC.dto;

import com.JavaCoffee.BackEndJC.model.entities.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}

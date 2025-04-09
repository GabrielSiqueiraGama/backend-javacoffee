package com.JavaCoffee.BackEndJC.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "combo")
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private int id;

    @NotNull
    private String nome;
    @NotNull
    private Double preco;
    
    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "combo")
    //private List<Produto> produtos = new ArrayList<Produto>();
}

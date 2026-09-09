package com.agro.telemetria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "equipamentos_spring")

public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "O modelo não pode ser vazio")
    private String modelo;

    @NotNull(message = "As horas trabalhadas são obrigatórias")
    @Min(value = 0, message = "As horas trabalhadas não podem ser negativas")
    private Integer horasTrabalhadas;

    public Equipamento() {}

    public Equipamento(String nome, String modelo, Integer horasTrabalhadas) {
        this.nome = nome;
        this.modelo = modelo;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Long getId() { return id;}
    public void setId(Long id) {this.id = id;}

    public String getNome() { return nome;}
    public void setNome(String nome) { this.nome = nome;}

    public String getModelo() { return modelo;}
    public void setModelo(String modelo) { this.modelo = modelo;}

    public Integer getHorasTrabalhadas() { return horasTrabalhadas;}
    public void setHorasTrabalhadas(Integer horasTrabalhadas) { this.horasTrabalhadas = horasTrabalhadas;}

    
}

package com.agro.telemetria;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "manutencoes_spring")

public class Manutencao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A decrição é obrigatória")
    private String descricao;

    @NotNull(message = "O custo é obrigatório")
    @Min(value = 0, message = "O custo não pode ser negativo")
    private Double custo;

    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    public Manutencao() {}

    public Manutencao (String descricao, Double custo, Equipamento equipamento) {
        this.descricao = descricao;
        this.custo = custo;
        this.equipamento = equipamento;
    }

    public Long getId() { return id;}
    public void setId(Long id) {this.id = id; }

    public String gesDescricao() { return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public Double getCusto() { return custo;}
    public void setCusto(Double custo) {this.custo = custo;}

    public Equipamento getEquipamento() { return equipamento;}
    public void setEquipamento (Equipamento equipamento) {this.equipamento = equipamento;}
}

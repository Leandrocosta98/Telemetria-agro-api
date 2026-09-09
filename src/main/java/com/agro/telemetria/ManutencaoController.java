package com.agro.telemetria;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    /*@GetMapping
    public ResponseEntity<List<Manutencao>> listar() {
        return ResponseEntity.ok(manutencaoRepository.findAll());
    }*/

    @GetMapping("/equipamento/{equipamentoId}")
    public ResponseEntity<List<Manutencao>> buscarPorEquipamento(@PathVariable Long equipamentoId) {
        List<Manutencao> lista = manutencaoRepository.findByEquipamentoId(equipamentoId);
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody ManutencaoDTO dto) {
        // Busca se o equipamento existe no banco antes de cadastrar a manutenção
        return equipamentoRepository.findById(dto.equipamentoId())
                .map(equipamento -> {
                    Manutencao manutencao = new Manutencao(dto.descricao(), dto.custo(), equipamento);
                    Manutencao salva = manutencaoRepository.save(manutencao);
                    return ResponseEntity.status(HttpStatus.CREATED).body(salva);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // DTO (Data Transfer Object) para receber os dados limpos no POST
    public record ManutencaoDTO(
            @NotBlank String descricao,
            @NotNull @Min(0) Double custo,
            @NotNull Long equipamentoId
    ) {}
}

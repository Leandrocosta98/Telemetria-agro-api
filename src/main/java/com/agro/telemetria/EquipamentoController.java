package com.agro.telemetria;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository repository;

    /*@GetMapping
    public ResponseEntity<List<Equipamento>> listar() {
        List<Equipamento> lista = repository.findAll();
        return ResponseEntity.ok(lista); // Status 200 OK
    }*/

    @GetMapping("/horas-maior")
    public ResponseEntity<List<Equipamento>> buscarPorHorasMaiorQue(@RequestParam Integer horas) {
        List<Equipamento> lista = repository.findByHorasTrabalhadasGreaterThan(horas);
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Equipamento> salvar(@Valid @RequestBody Equipamento equipamento) {
        Equipamento novo = repository.save(equipamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo); // Status 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Long id, @Valid @RequestBody Equipamento dadosAtualizados) {
        return repository.findById(id)
                .map(equipamento -> {
                    equipamento.setNome(dadosAtualizados.getNome());
                    equipamento.setModelo(dadosAtualizados.getModelo());
                    equipamento.setHorasTrabalhadas(dadosAtualizados.getHorasTrabalhadas());
                    Equipamento atualizado = repository.save(equipamento);
                    return ResponseEntity.ok(atualizado); // Status 200 OK
                })
                .orElse(ResponseEntity.notFound().build()); // Status 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // Status 204 No Content
        }
        return ResponseEntity.notFound().build(); // Status 404 Not Found
    }
}
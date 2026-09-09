package com.agro.telemetria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{
    List<Equipamento> findBymodelo(String modelo);
    
    List<Equipamento> findByHorasTrabalhadasGreaterThan(Integer horas);
}

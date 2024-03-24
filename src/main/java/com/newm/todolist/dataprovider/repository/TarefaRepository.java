package com.newm.todolist.dataprovider.repository;

import com.newm.todolist.dataprovider.model.Tarefa;
import com.newm.todolist.dto.tarefa.TarefaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query("SELECT t FROM Tarefa t WHERE t.status = 'PENDENTE'")
    List<Tarefa> findAllByStatusPendente();
}

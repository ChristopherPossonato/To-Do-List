package com.newm.todolist.service.tarefa;

import com.newm.todolist.dto.tarefa.TarefaDto;

import java.util.List;
import java.util.Optional;

public interface TarefaService {
    List<TarefaDto> listar();
    Optional<TarefaDto> buscarPorId(Long id);
    Long salvar(TarefaDto tarefaDto);
    Long alterar(TarefaDto tarefaDto);
    void excluir(Long id);
}

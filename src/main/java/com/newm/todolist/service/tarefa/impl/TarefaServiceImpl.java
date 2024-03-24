package com.newm.todolist.service.tarefa.impl;

import com.newm.todolist.dataprovider.model.Tarefa;
import com.newm.todolist.dataprovider.repository.TarefaRepository;
import com.newm.todolist.dto.tarefa.TarefaDto;
import com.newm.todolist.service.tarefa.TarefaService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TarefaServiceImpl implements TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<TarefaDto> listar() {
        var list = new ArrayList<TarefaDto>();
        this.tarefaRepository.findAllByStatusPendente()
                .forEach(o -> {
            list.add(this.mapper.map(o, TarefaDto.class));
        });
        return list.stream()
                .sorted(Comparator.comparing(TarefaDto::getDataExpiracao))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TarefaDto> buscarPorId(Long id) {

        var tarefa = tarefaRepository.getReferenceById(id);

        if (tarefa != null) {
            TarefaDto obj = mapper.map(tarefa, TarefaDto.class);

            return Optional.of(obj);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public TarefaDto salvar(TarefaDto tarefaDto) {
        var obj = this.mapper.map(tarefaDto, Tarefa.class);
        var tarefaSalva = tarefaRepository.save(obj);
        return this.mapper.map(tarefaSalva, TarefaDto.class);
    }

    @Override
    public Long alterar(TarefaDto tarefaDto) {
        if (tarefaDto.getId() == null) {
            throw new IllegalArgumentException("ID da tarefa não pode ser nulo para atualização");
        }

        var tarefaExistente = tarefaRepository.findById(tarefaDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));

        // Atualize os campos relevantes da tarefa existente com os valores do DTO
        mapper.map(tarefaDto, tarefaExistente);

        Tarefa tarefaAtualizada = tarefaRepository.save(tarefaExistente);

        return tarefaAtualizada.getId();
    }



    @Override
    public void excluir(Long id) {
        var obj = tarefaRepository.findById(id);
        obj.ifPresent(cliente -> tarefaRepository.delete(cliente));
    }

}

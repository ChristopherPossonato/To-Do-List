package com.newm.todolist.service.tarefa.impl;

import com.newm.todolist.dataprovider.model.Tarefa;
import com.newm.todolist.dataprovider.repository.TarefaRepository;
import com.newm.todolist.dto.tarefa.TarefaDto;
import com.newm.todolist.service.tarefa.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

        this.tarefaRepository.findAll().forEach(o -> {
            list.add(this.mapper.map(o, TarefaDto.class));
        });

        return list.stream()
                .sorted(Comparator.comparing(TarefaDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TarefaDto> buscarPorId(Long id) {

        var tarefa = tarefaRepository.findById(id).orElse(null);

        if (tarefa != null) {

            TarefaDto obj = null;
            obj = mapper.map(tarefa, TarefaDto.class);

            return Optional.of(obj);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Long salvar(TarefaDto tarefaDto) {
        var obj = this.mapper.map(tarefaDto, Tarefa.class);

        var clienteSalvo = tarefaRepository.save(obj);

        return clienteSalvo.getId();
    }

    @Override
    public Long alterar(TarefaDto tarefaDto) {

        if (tarefaDto.getId() == null) {
            throw new IllegalArgumentException("ID da tarefa não pode ser nulo para atualização");
        }

        var tarefaExistente = mapper.map(tarefaDto, Tarefa.class);
        tarefaRepository.findById(tarefaExistente.getId()).orElseThrow();

        Tarefa clienteAtualizado = tarefaRepository.save(tarefaExistente);

        return clienteAtualizado.getId();
    }


    @Override
    public void excluir(Long id) {
        var obj = tarefaRepository.findById(id);
        obj.ifPresent(cliente -> tarefaRepository.delete(cliente));
    }

}

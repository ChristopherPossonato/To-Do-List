package com.newm.todolist.entrypoint;

import com.newm.todolist.dto.tarefa.TarefaDto;
import com.newm.todolist.entrypoint.model.tarefa.TarefaRequest;
import com.newm.todolist.entrypoint.model.tarefa.TarefaResponse;
import com.newm.todolist.entrypoint.model.tarefa.TarefaResponseAll;
import com.newm.todolist.service.tarefa.TarefaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Tarefas", description = "Recursos para manipular tarefas")
@RequestMapping("/api/tarefas")
public class TarefaResource {

    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> listar() {
        List<TarefaDto> tarefas = tarefaService.listar();
        var response = tarefas.stream()
                .map(dto -> mapper.map(dto,TarefaResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca tarefa por Id.")
    public ResponseEntity<TarefaResponseAll> buscarPorId(@PathVariable Long id) {
        var obj = tarefaService.buscarPorId(id).orElse(null);

        if (obj != null) {
            var ret = this.mapper.map(obj, TarefaResponseAll.class);
            System.out.println(ret);
            return ResponseEntity.ok(ret);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation(value = "Salva nova tarefa.")
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid TarefaRequest  tarefaRequest, UriComponentsBuilder uriComponentsBuilder) {

        var tarefaDto = mapper.map(tarefaRequest, TarefaDto.class);
        var tarefaSalvaDto = tarefaService.salvar(tarefaDto);

        var uri = uriComponentsBuilder.path("api/tarefas/{id}").buildAndExpand(tarefaSalvaDto.getId()).toUri();
        return ResponseEntity.created(uri).body(tarefaSalvaDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza tarefa.")
    @Transactional
    public ResponseEntity<Long> alterar(@RequestBody TarefaRequest tarefaRequest, @PathVariable("id") Long id) {
        var tarefaDto = mapper.map(tarefaRequest, TarefaDto.class);
        tarefaDto.setId(id);

        Long idTarefaAtualizada = tarefaService.alterar(tarefaDto);

        return ResponseEntity.ok(idTarefaAtualizada);
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta tarefa.")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        tarefaService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}

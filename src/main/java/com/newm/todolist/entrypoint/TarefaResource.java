package com.newm.todolist.entrypoint;

import com.newm.todolist.dto.tarefa.TarefaDto;
import com.newm.todolist.entrypoint.model.tarefa.TarefaRequest;
import com.newm.todolist.entrypoint.model.tarefa.TarefaResponse;
import com.newm.todolist.service.tarefa.TarefaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Tarefas", description = "Recursos para manipular tarefas")
@RequestMapping("/tarefas")
public class TarefaResource {

    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<TarefaDto>> listar() {
        var list = this.tarefaService.listar();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca tarefa por Id.")
    public ResponseEntity<TarefaResponse> buscarPorId(@PathVariable Long id) {
        var obj = tarefaService.buscarPorId(id).orElse(null);

        if (obj != null) {
            var ret = this.mapper.map(obj, TarefaResponse.class);

            return ResponseEntity.ok().body(ret);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ApiOperation(value = "Salva nova tarefa.")
    @Transactional
    public ResponseEntity<Long> salvar(@RequestBody @Valid TarefaRequest  tarefaRequest) {

        var tarefaDto = mapper.map(tarefaRequest, TarefaDto.class);

        Long tarefaId = tarefaService.salvar(tarefaDto);

        return ResponseEntity.ok().body(tarefaId);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza tarefa.")
    public ResponseEntity<Long> atualizar(@RequestBody TarefaRequest tarefaRequest, @PathVariable("id") Long id) {
        var tarefaDto = mapper.map(tarefaRequest, TarefaDto.class);

        tarefaDto.setId(id);

        var tarefaAtualizada = tarefaService.alterar(tarefaDto);

        return ResponseEntity.ok().body(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta tarefa.")
    public void deletar(@PathVariable Long id) {
        tarefaService.excluir(id);
    }


}

package com.newm.todolist.entrypoint.model.tarefa;

import com.newm.todolist.dataprovider.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponse {
    private Long id;
    private String titulo;
    private String detalhes;
    private Status status;
}

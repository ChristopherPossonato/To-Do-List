package com.newm.todolist.entrypoint.model.tarefa;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.newm.todolist.dataprovider.model.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "titulo",
        "descricao",
        "status"
})
public class TarefaRequest {
    @NotBlank(message = "Digte o título da tarefa")
    private String titulo;
    @NotBlank(message = "Digite a descrição da tarefa")
    private String detalhes;

    private Status status;

}

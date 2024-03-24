package com.newm.todolist.entrypoint.model.tarefa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.newm.todolist.dataprovider.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "titulo",
        "descricao",
        "dataExpiracao"
})
public class TarefaRequest {
    @NotBlank(message = "Digte o título da tarefa")
    private String titulo;
    @NotBlank(message = "Digite a descrição da tarefa")
    private String descricao;
    @NotNull(message = "Digite a data expiração da tarefa")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataExpiracao;

}

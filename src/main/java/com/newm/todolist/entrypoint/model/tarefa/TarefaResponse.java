package com.newm.todolist.entrypoint.model.tarefa;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponse {
    private Long id;
    private String titulo;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataCriacao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataExpiracao;
    private Boolean status;
}

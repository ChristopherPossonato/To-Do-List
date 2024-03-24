package com.newm.todolist.entrypoint.model.tarefa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newm.todolist.dataprovider.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponseAll {
    private Long id;
    private String titulo;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataCriacao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataExpiracao;
    private Status status;

}

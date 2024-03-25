package com.newm.todolist.dto.tarefa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDto implements Serializable {
    private Long id;
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private Date dataExpiracao;
    private Boolean status;

}

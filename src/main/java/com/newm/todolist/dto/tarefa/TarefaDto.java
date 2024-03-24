package com.newm.todolist.dto.tarefa;

import com.newm.todolist.dataprovider.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
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
    private String dataExpiracao;
    private Status status;

}

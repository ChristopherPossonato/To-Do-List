package com.newm.todolist.dataprovider.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tarefas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @Column(columnDefinition = "DATE")
    private Date dataCriacao = new Date();
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataExpiracao;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDENTE;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = new Date();
        if (this.status == null) {
            this.status = Status.PENDENTE;
        }
    }
}
package com.somei.apisomei.model;


import java.time.LocalDateTime;

public class Despesa {

    private String descricao;
    private float valor;
    private boolean fixa;
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dtVencimento;
}

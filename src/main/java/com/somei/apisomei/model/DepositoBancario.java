package com.somei.apisomei.model;

import com.somei.apisomei.model.enums.StatusDeposito;

import java.time.LocalDateTime;

public class DepositoBancario {

    private float valor;
    private Profissional profissional;
    private LocalDateTime dataPrevista;
    private LocalDateTime dataDeposito;
    private StatusDeposito status;

}

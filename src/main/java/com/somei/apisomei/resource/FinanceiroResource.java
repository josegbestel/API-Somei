package com.somei.apisomei.resource;

import com.somei.apisomei.model.Lancamento;
import com.somei.apisomei.model.representationModel.FinanceiroModel;
import com.somei.apisomei.service.FinanceiroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/profissional/{idProfissional}/financeiro")
@Api
@CrossOrigin(origins = "*")
public class FinanceiroResource {

    @Autowired
    FinanceiroService financeiroService;

    //Alterar meta mensal
    @PutMapping("/meta-mensal")
    @ApiOperation("ALtera a meta mensal do profissional")
    public ResponseEntity<Object> alterarMetaMensal(@PathVariable(value = "idProfissional") Long idProfissional,
                                                    @RequestParam(value = "valor") Float valor){
        financeiroService.editMetaMensal(idProfissional, valor);
        return ResponseEntity.noContent().build();
    }

    //Criar Lançamento financeiro
    @PostMapping("/lancamento")
    @ApiOperation("Realiza um lançamento financeiro do profissional")
    public ResponseEntity<List<Lancamento>> lancamentoFinanceiro(@PathVariable(value = "idProfissional") Long idProfissional,
                                                                 @RequestBody @Valid Lancamento lancamento){

        return ResponseEntity.ok(financeiroService.createLancamento(idProfissional, lancamento));
    }

    //Obter lançamentos financeiros
    @GetMapping("/lancamento")
    @ApiOperation("Obtem todos os lançamentos dentro do mês a partir do id do profissional")
    public ResponseEntity<List<Lancamento>> lancamentosMes(@PathVariable(value = "idProfissional") Long idProfissional){
        return ResponseEntity.ok(financeiroService.readMonthByProfissional(idProfissional));
    }

    //Obtem o relatório financeiro
    @GetMapping("/relatorio")
    @ApiOperation("Retorna todas as informações para preencher o relatório do profissional")
    public ResponseEntity<FinanceiroModel> relatorioFinanceiro(@PathVariable(value = "idProfissional") Long idProfissional){
        return ResponseEntity.ok(financeiroService.abstractMonth(idProfissional));
    }

    //TODO: Realizar transferência
}

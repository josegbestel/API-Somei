package com.somei.apisomei.service;

import com.somei.apisomei.config.ApplicationConfig;
import com.somei.apisomei.exception.DomainException;
import com.somei.apisomei.model.Servico;
import com.somei.apisomei.model.dto.CompaniesNfeDTO;
import com.somei.apisomei.model.dto.CompanyNfeDTO;
import com.somei.apisomei.model.dto.ServiceInvoiceNfeDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Service
public class NfeService {

    private static final String TOKEN = ApplicationConfig.TOKEN_NFE_IO;
    private static final String END_POINT = ApplicationConfig.ENDPOINT_NFE;

    public NfeService() {
    }

    private HttpHeaders createHeaders(){
        return new HttpHeaders(){{
            add( "Authorization", TOKEN );
            add("Content-Type", "application/json");
            add("Accept", "application/json");
            add("Keep-Alive", "true");
        }};
    }

    //RETORNA A EMPRESA JÁ CADASTRADA OU CADASTRA UMA NOVA
    public CompanyNfeDTO obterEmpresa(CompanyNfeDTO company){

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = createHeaders();
        String request = END_POINT + "/companies/" + company.getFederalTaxNumber();
        HttpMethod method = HttpMethod.GET;
        ResponseEntity<CompaniesNfeDTO> response;

        try {
            HttpEntity<String> entity = new HttpEntity<>(headers);
            response = template.exchange(request, method, entity, CompaniesNfeDTO.class);
            return response.getBody().getCompanies();
        }catch (Exception e){
            return this.criarEmpresa(company);
        }
    }

    //CRIAR EMPRESA
    private CompanyNfeDTO criarEmpresa(CompanyNfeDTO company){
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = createHeaders();
        String request = END_POINT + "/companies";
        HttpMethod method = HttpMethod.POST;
        ResponseEntity<CompaniesNfeDTO> response;

        try {
            HttpEntity<String> entity = new HttpEntity<>(company.toJson().toString(), headers);
            response = template.exchange(request, method, entity, CompaniesNfeDTO.class);
            return response.getBody().getCompanies();
        }catch (Exception e){
            throw new DomainException("Erro ao criar empresa na NFe.io: " + e.getMessage());
        }
    }

    //CRIAR NFSe
    public ServiceInvoiceNfeDTO criarNFSe(Servico servico){

        String companyId = servico.getProfissional().getIdNfe();
        ServiceInvoiceNfeDTO serviceInvoice = new ServiceInvoiceNfeDTO(servico);

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = createHeaders();
        String request = END_POINT + "/companies/" + companyId + "/serviceinvoices";
        HttpMethod method = HttpMethod.POST;
        ResponseEntity<ServiceInvoiceNfeDTO> response;

        try {
            HttpEntity<String> entity = new HttpEntity<>(serviceInvoice.toJson().toString(), headers);
            response = template.exchange(request, method, entity, ServiceInvoiceNfeDTO.class);
            return response.getBody();
        }catch (Exception e){
            throw new DomainException("Erro ao criar NFSe: " + e.getMessage());
        }
    }
}

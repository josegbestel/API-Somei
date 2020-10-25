package com.somei.apisomei.resource;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/financeiro")
@Api
@CrossOrigin(origins = "*")
public class FinanceiroResource {
}

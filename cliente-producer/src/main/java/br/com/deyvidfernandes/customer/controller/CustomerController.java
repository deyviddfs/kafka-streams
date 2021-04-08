package br.com.deyvidfernandes.customer.controller;

import br.com.deyvidfernandes.customer.business.object.CustomerBO;
import br.com.deyvidfernandes.customer.controller.dto.CustomerDto;
import br.com.deyvidfernandes.customer.controller.dto.EmailDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "customer", tags = "customer", description = "API para cadastro de cliente")
@RestController
@RequestMapping("/cliente")
public class CustomerController {

    @Autowired
    CustomerBO customerBO;

    @ApiOperation(value = "Esta operação simula o recebimento dos dados de um cliente e produz uma mensagem no Kafka")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna Sucesso")
    })
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveClient(CustomerDto customerDto) {
        customerBO.create(customerDto);
        return "sucesso";
    }

    @ApiOperation(value = "Esta operação simula o recebimento do e-mail de um cliente e produz uma mensagem no Kafka")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna Sucesso")
    })
    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public String saveEmail(EmailDto emailDto) {
        customerBO.create(emailDto);
        return "sucesso";
    }
}

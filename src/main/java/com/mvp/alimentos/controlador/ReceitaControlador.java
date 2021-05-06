package com.mvp.alimentos.controlador;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import com.mvp.alimentos.servico.ReceitaServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@Api(value = "Informa o valor de uma dada receita")
public class ReceitaControlador {

	@Autowired
	private ReceitaServico receitaServico;


	@ApiOperation(value = "Informa o valor de uma receita cadastrada na base de dados")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Retorna o valor de uma receita cadastra na base de dados"),
		@ApiResponse(code = 400, message = "Paâmetro incorreto ou a receita passada por parâmetro não existe"),
		@ApiResponse(code = 500, message = "Houve um erro inesperado no sistema"),
	})
	@RequestMapping(value = "/receita/calculaValorReceitaCardapio", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
	public ResponseEntity<String> calculaValorReceitaCardapio(
		@ApiParam(
    		name =  "nomeReceita",
    		type = "String",
    		value = "O nome da receita a ser calculado o valor",
    		example = "X-Burger",
    		required = true)
		@RequestParam("nomeReceita") String nomeReceita) {
		try {
			return ResponseEntity.ok().body(String.valueOf(receitaServico.calculaValorReceitaCardapio(nomeReceita)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}

	@ApiOperation(value = "Informa o valor de uma receita personalizada")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Retorna o valor de uma receita personalizada pelo cliente"),
		@ApiResponse(code = 400, message = "Parâmetro incorreto ou os ingredientes passados por parâmetro não existem"),
		@ApiResponse(code = 500, message = "Houve um erro inesperado no sistema"),
	})
	@RequestMapping(value = "/receita/calculaValorReceitaPersonalizada", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
	public ResponseEntity<String> calculaValorReceitaPersonalizada(
		@ApiParam(
    		name =  "ingrediente",
    		type = "String",
    		value = "O ingrediente a ser adicionado na receita, pode ser passado várias vezes",
    		example = "Alface",
    		required = true)
		@RequestParam("ingrediente") List<String> listaNomeIngredientes) {
		try {
			return ResponseEntity.ok().body(String.valueOf(receitaServico.calculaValorReceitaPersonalizada(listaNomeIngredientes)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}

}
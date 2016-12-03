package br.com.constantino.androidviagem.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.constantino.androidviagem.entities.Viagem;

@Component
public class ViagemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Viagem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors e) {

		ValidationUtils.rejectIfEmpty(e, "destino", "destinoEmpty", "Por favor, informe o destino.");
		ValidationUtils.rejectIfEmpty(e, "tipoViagem", "tipoViagemEmpty", "Por favor, informe o tipo da viagem.");
		ValidationUtils.rejectIfEmpty(e, "data_chegada", "dataChegadaEmpty", "Por favor, informe a data de chegada.");
		ValidationUtils.rejectIfEmpty(e, "data_saida", "dataSaidaEmpty", "Por favor, informe a data de saída.");
		ValidationUtils.rejectIfEmpty(e, "orcamento", "orcamentoEmpty", "Por favor, informe o orçamento.");
		ValidationUtils.rejectIfEmpty(e, "qtde_pessoas", "qtdePessoasEmpty", "Por favor, informe a qtde de pessoas.");
	}

}

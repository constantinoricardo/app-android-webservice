package br.com.constantino.androidviagem.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.constantino.androidviagem.entities.Gasto;

@Component
public class GastoValidator implements Validator {			

	public boolean supports(Class<?> clazz) {
		return Gasto.class.isAssignableFrom(clazz);
	}

	public void othersValidate(Object object, Errors e) {
		
		try {
			
			Gasto gasto = (Gasto) object;								
			
			if (gasto.getDescricao().length() < 3 || gasto.getDescricao().length() > 50) 
				e.rejectValue("descricao", "rangeDescricao", "Número de caracteres da descrição deve estar entre 3 e 50.");
			
			if (gasto.getLocal().length() < 3 || gasto.getLocal().length() > 50)
				e.rejectValue("local", "rangeLocal", "Número de caracteres do local deve estar entre 3 e 50.");
			
			
		} catch(NullPointerException ex) {
			ex.getStackTrace();
		}
	}

	public void validate(Object object, Errors e) {					
		
		Gasto gasto = (Gasto) object;	
		
		ValidationUtils.rejectIfEmpty(e, "categoria", "categoriaEmpty", "Por favor, informe a categoria.");
		ValidationUtils.rejectIfEmpty(e,"valor", "valorEmpty", "Por favor, informe o valor.");
		ValidationUtils.rejectIfEmpty(e, "data", "dataEmpty", "Por favor, informe a data.");
		ValidationUtils.rejectIfEmpty(e, "descricao", "descricaoEmpty", "Por favor, informe a descri��o.");
		ValidationUtils.rejectIfEmpty(e, "local", "localEmpty", "Por favor, informe o local.");
		
		this.othersValidate(object, e);				      
		
	}

}

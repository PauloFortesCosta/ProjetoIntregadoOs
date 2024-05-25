package br.com.ordemservico.infra.Exception;

public class AlertException extends RuntimeException{
	public AlertException(String ex) {
		super(ex);
	}
}

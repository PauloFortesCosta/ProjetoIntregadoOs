package br.com.ordemservico.model;

public enum TipoUsuario {
	ADMIN("ADMIN"),
	CLIENTE("CLIENTE");
	
	private String tipoUsuario;
	
	TipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}
}

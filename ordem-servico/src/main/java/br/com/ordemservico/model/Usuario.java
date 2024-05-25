package br.com.ordemservico.model;

public class Usuario {
	private Long id;
	private String login;
	private String senha;
	private TipoUsuario tipo_usuario;
	
	public Usuario() {
		
	}
	
	public Usuario(Long id, String login, String senha, TipoUsuario tipoUsuario) {
		if(id != null) {			
			this.id = id;
		}	
		
		this.login = login;
		this.senha = senha;
		this.tipo_usuario = tipoUsuario;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public TipoUsuario getTipo_usuario() {
		return tipo_usuario;
	}
	
	public Long getId() {
		return id;
	}

	public void alterar(Long id, String senha) {
		this.id = id;
		
		if(senha != null && senha != "") {			
			this.senha = senha;
		}		
	}
	
	public boolean validaSenha(String login, String senha) {
		if(!this.login.equals(login) || !this.senha.equals(senha)) {
			return false;
		}
		return true;
	}
	
	
}

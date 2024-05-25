package br.com.ordemservico.model;

public class Cliente {
	private Long id;
	private Usuario usuario;
	private String nome;
	private String numero;
	private String cpf;
	private String cep;
	
	public Cliente() {}
	
	public Cliente(String nome, String numero, String cpf, String cep, Usuario usuario) {
		this.nome = nome;
		this.numero = numero;
		this.cpf = cpf;
		this.cep = cep;
		this.usuario = usuario;
	}
	
	public void alterar(Long id, String nome, String numero, String cpf, String cep, Usuario usuario) {
		this.id = id;
		
		if(nome != null && nome != "") {			
			this.nome = nome;
		}
		if(numero != null && numero != "") {			
			this.numero = numero;
		}
		if(cpf != null && cpf != "") {			
			this.cpf = cpf;
		}
		if(cep != null && cep != "") {			
			this.cep = cep;
		}		
		if(usuario != null) {
			this.usuario = usuario;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getNumero() {
		return numero;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
		
}

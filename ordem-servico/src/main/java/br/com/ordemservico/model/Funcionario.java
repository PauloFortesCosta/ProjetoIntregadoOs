package br.com.ordemservico.model;

public class Funcionario {
	private Long id;
	private Usuario usuario;
	private String nome;
	private String email;
	private Integer idade;
	
	public Funcionario() {}
	
	public Funcionario(String nome, String email, Integer idade, Usuario usuario) {
		this.nome = nome;
		this.email = email;
		this.idade = idade;
		this.usuario = usuario;
	}
	
	public void alterar(Long id, String nome, String email, Integer idade, Usuario usuario) {
		this.id = id;
		
		if(nome != null && nome != "") {			
			this.nome = nome;
		}
		if(email != null && email != "") {			
			this.email = email;
		}
		if(idade != null) {			
			this.idade = idade;
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

	public String getEmail() {
		return email;
	}

	public Integer getIdade() {
		return idade;
	}
	
	
}

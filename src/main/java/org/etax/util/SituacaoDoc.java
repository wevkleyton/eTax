package org.etax.util;

public enum SituacaoDoc {

	ATIVO("Ativo"), INATIVO("Inativo");

	private String descricao;

	SituacaoDoc(String situacao) {
		this.descricao = situacao;
	}

	public String getDescricao() {
		return descricao;
	}
}

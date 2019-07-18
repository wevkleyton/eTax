package org.etax.util;

public enum SituacaoDoc {

	ATIVO("Ativo"), INATIVO("Inativo");

	private String situacao;

	SituacaoDoc(String situacao) {
		this.situacao = situacao;
	}

	public String getSituacao() {
		return situacao;
	}
}

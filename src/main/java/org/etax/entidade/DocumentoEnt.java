package org.etax.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.etax.util.SituacaoDoc;

@Entity
public class DocumentoEnt implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nomeDocumento;
	private String descricaoDocumento;
	private SituacaoDoc situacaoaDocumento;

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public String getDescricaoDocumento() {
		return descricaoDocumento;
	}

	public void setDescricaoDocumento(String descricaoDocumento) {
		this.descricaoDocumento = descricaoDocumento;
	}

	public SituacaoDoc getSituacaoaDocumento() {
		return situacaoaDocumento;
	}

	public void setSituacaoaDocumento(SituacaoDoc situacaoaDocumento) {
		this.situacaoaDocumento = situacaoaDocumento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentoEnt other = (DocumentoEnt) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

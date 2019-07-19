package org.etax.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.etax.dao.DaoGeneric;
import org.etax.entidade.DocumentoEnt;
import org.etax.util.Menssagens;
import org.etax.util.SituacaoDoc;
import org.primefaces.model.SelectableDataModel;

@ViewScoped
@ManagedBean(name = "documentoBean")
public class DocumentoBean implements SelectableDataModel<DocumentoEnt>, Serializable {

	private static final long serialVersionUID = 1L;
	private DocumentoEnt documento = new DocumentoEnt();
	private DaoGeneric<DocumentoEnt> daoGeneric = new DaoGeneric<DocumentoEnt>();
	private List<DocumentoEnt> listaDoc = new ArrayList<DocumentoEnt>();
	private List<SituacaoDoc> sitdoc, sitdocEd;
	private String nomePesquisa;
	private SituacaoDoc situacaoPesquisa;
	private DocumentoEnt documentoEdicao;

	@PostConstruct
	public void init() {
		sitdoc = Arrays.asList(SituacaoDoc.values());
		sitdocEd = Arrays.asList(SituacaoDoc.values());
	}

	public void salvar() {
		daoGeneric.salvar(documentoEdicao);
		documentoEdicao = new DocumentoEnt();
	}

	public void editar() {
		daoGeneric.merge(documentoEdicao);
		documentoEdicao = new DocumentoEnt();
	}

	public void exibe() {
		documentoEdicao = documento;

	}

	public String pesquisar() throws Exception {
		String query = "SELECT d FROM DocumentoEnt d where  d.situacaoaDocumento = :situacaoadocumento and d.nomeDocumento like :nomedocumento ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nomedocumento", "%" + (nomePesquisa == null ? "" : nomePesquisa) + "%");
		params.put("situacaoadocumento", situacaoPesquisa);
		listaDoc = daoGeneric.listObject(DocumentoEnt.class, query, params);

		if (listaDoc == null || listaDoc.size() == 0) {
			Menssagens.msgError("Error", "Sem Registro ");
		} else {
			Menssagens.msgInfo("INFORMAÇÂO", "Pesquisa realizada com sucesso.");
		}
		return "";
	}

	public void carregaDoc() {
		listaDoc = daoGeneric.getListEntity(DocumentoEnt.class);
	}

	public String novo() {
		documento = new DocumentoEnt();
		return "";
	}

	public String deletar() {
		daoGeneric.deleteById(documento);
		return "";
	}

	private void carregaDocumento() {
		documento = (DocumentoEnt) daoGeneric.getListEntity(DocumentoEnt.class);
	}

	public DocumentoEnt getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoEnt documento) {
		this.documento = documento;
	}

	public List<DocumentoEnt> getListaDoc() {
		return listaDoc;
	}

	public void setListaDoc(List<DocumentoEnt> listaDoc) {
		this.listaDoc = listaDoc;
	}

	public List<SituacaoDoc> getSitdoc() {
		return sitdoc;
	}

	@Override
	public Object getRowKey(DocumentoEnt object) {
		return object.getId();
	}

	@Override
	public DocumentoEnt getRowData(String rowKey) {
		return null;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public SituacaoDoc getSituacaoPesquisa() {
		return situacaoPesquisa;
	}

	public void setSituacaoPesquisa(SituacaoDoc situacaoPesquisa) {
		this.situacaoPesquisa = situacaoPesquisa;
	}

	public DocumentoEnt getDocumentoEdicao() {
		return documentoEdicao;
	}

	public void setDocumentoEdicao(DocumentoEnt documentoEdicao) {
		this.documentoEdicao = documentoEdicao;
	}

	public List<SituacaoDoc> getSitdocEd() {
		return sitdocEd;
	}

	public void setSitdocEd(List<SituacaoDoc> sitdocEd) {
		this.sitdocEd = sitdocEd;
	}

}

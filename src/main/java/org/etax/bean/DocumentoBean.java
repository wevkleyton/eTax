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
	private List<SituacaoDoc> situacaoDocumento;
	private List<SituacaoDoc> situacaoDocumentoEdicao;
	private String nomePesquisa;
	private SituacaoDoc situacaoPesquisa, situacaoEdicao;
	private DocumentoEnt documentoEdicao;

	@PostConstruct
	public void init() {
		situacaoDocumento = Arrays.asList(SituacaoDoc.values());
	}

	public void salvar() {
		documento.setSituacaoaDocumento(situacaoPesquisa);
		daoGeneric.salvar(documento);
		Menssagens.msgInfo("msg_inclusao", "Informação", "Documento Salvo com sucesso.");
	}

	public void editar() {
		documentoEdicao.setSituacaoaDocumento(situacaoEdicao);
		daoGeneric.merge(documentoEdicao);
		documentoEdicao = new DocumentoEnt();
		Menssagens.msgInfo("msg_edicao", "Informação", "Pesquisa realizada com sucesso.");
	}

	public void exibe() {
		situacaoDocumentoEdicao = Arrays.asList(SituacaoDoc.values());
		documentoEdicao = documento;

	}

	public void incluir() {
		documento = new DocumentoEnt();

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
			Menssagens.msgInfo("Informação", "Pesquisa realizada com sucesso.");
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

	public String deletar() throws Exception {
		daoGeneric.deleteById(documento);
		Menssagens.msgInfo("Informação", "Arquivo Excluido com sucesso.");
		pesquisar();
		return "";
	}

	@SuppressWarnings("unused")
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
		return situacaoDocumento;
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

	public List<SituacaoDoc> getSituacaoDocumento() {
		return situacaoDocumento;
	}

	public void setSituacaoDocumento(List<SituacaoDoc> situacaoDocumento) {
		this.situacaoDocumento = situacaoDocumento;
	}

	public List<SituacaoDoc> getSituacaoDocumentoEdicao() {
		return situacaoDocumentoEdicao;
	}

	public void setSituacaoDocumentoEdicao(List<SituacaoDoc> situacaoDocumentoEdicao) {
		this.situacaoDocumentoEdicao = situacaoDocumentoEdicao;
	}

	public SituacaoDoc getSituacaoEdicao() {
		return situacaoEdicao;
	}

	public void setSituacaoEdicao(SituacaoDoc situacaoDocEdicao) {
		this.situacaoEdicao = situacaoDocEdicao;
	}

}
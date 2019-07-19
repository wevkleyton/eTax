package org.etax.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.etax.util.JPAUtil;


public class DaoGeneric<T> {

	public void salvar(T entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(entidade);
		entityTransaction.commit();
		entityManager.close();
	}

	public T merge(T entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		T retorno = entityManager.merge(entidade);
		entityTransaction.commit();
		entityManager.close();
		return retorno;
	}

	public void delete(T entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(entidade);
		entityTransaction.commit();
		entityManager.close();
	}

	public void deleteById(T entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Object id = JPAUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id)
				.executeUpdate();
		entityTransaction.commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<T> getListEntity(Class<T> entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		List<T> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();
		entityTransaction.commit();
		entityManager.close();
		return retorno;
	}

	public T byName(String sql) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		@SuppressWarnings("unchecked")
		T retorno =  (T) entityManager.createNativeQuery(sql).getResultList();
		entityTransaction.commit();
		entityManager.close();
		return retorno;
		
	}
	
	  @SuppressWarnings("unchecked")
	  public T findObject(Class c, String hql, Map<String,Object> param) throws Exception {
		  try {
			  EntityManager entityManager = JPAUtil.getEntityManager();
			  Query query = (Query) entityManager.createQuery(hql);
			  T t = null;
			  for (String chave : param.keySet()) {
				  query.setParameter(chave, param.get(chave));
			  }
			  t = (T) query.getSingleResult();
			  return t;
		  } catch (NoResultException e) {
			  e.printStackTrace();
			  return null;
		  } catch ( NonUniqueResultException e) {
			  e.printStackTrace();
			  throw new Exception("Existem mais de 1 usuario");
		  }
	  }
	  
	  public List<T> listObject(Class c, String hql, Map<String,Object> param) throws Exception {
		  try {
			  EntityManager entityManager = JPAUtil.getEntityManager();
			  Query query = (Query) entityManager.createQuery(hql);
			  List<T> lista = null;
			  for (String chave : param.keySet()) {
				  query.setParameter(chave, param.get(chave));
			  }
			  lista = (List<T>) query.getResultList();
			  return lista;
		  } catch (NoResultException e) {
			  e.printStackTrace();
			  return null;
		  } catch ( NonUniqueResultException e) {
			  e.printStackTrace();
			  throw new Exception("Existem mais de 1 usuario");
		  }
	  }
	  
	  
}

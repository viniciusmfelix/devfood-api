package com.devfood.devfoodapi.infrastructure.repository;

import static com.devfood.devfoodapi.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.devfood.devfoodapi.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.devfood.devfoodapi.domain.model.Restaurante;
import com.devfood.devfoodapi.domain.repository.CustomizedRestauranteRepository;
import com.devfood.devfoodapi.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements CustomizedRestauranteRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	@Lazy
	private RestauranteRepository restauranteRepository;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
		}
		if(taxaFreteInicial != null) {
			predicates.add(builder
					.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}
		if(taxaFreteFinal != null) {
			predicates.add(builder
					.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		return query.getResultList();
		
		/* JPQL
		 * var jpql = new StringBuilder();
		var parametros = new HashMap<String, Object>();
		jpql.append("FROM Restaurante WHERE 0 = 0 ");
		if(StringUtils.hasLength(nome)) {
			jpql.append("AND nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}
		if(taxaFreteInicial != null) {
			jpql.append("AND taxaFrete >= :taxaFreteInicial ");
			parametros.put("taxaFreteInicial", taxaFreteInicial);
		}
		if(taxaFreteFinal != null) {
			jpql.append("AND taxaFrete <= :taxaFreteFinal ");
			parametros.put("taxaFreteFinal", taxaFreteFinal);
		}
		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		return query.getResultList();*/
	}

	@Override
	public List<Restaurante> buscarPorFreteGratis(String nome) {
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}
}

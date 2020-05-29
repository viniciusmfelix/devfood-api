package com.devfood.devfoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devfood.devfoodapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, CustomizedRestauranteRepository, JpaSpecificationExecutor<Restaurante>{

	@Query("FROM Restaurante r JOIN FETCH r.cozinha")
	List<Restaurante> findAll();
	
	List<Restaurante> findRestaurantesByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	Optional<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);
	
	List<Restaurante> findTop2RestauranteByNomeContaining(String nome);
	
	boolean existsByNome(String nome);

}

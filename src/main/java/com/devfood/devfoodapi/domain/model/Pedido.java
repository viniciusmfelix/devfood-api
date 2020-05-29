package com.devfood.devfoodapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Pedido {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private BigDecimal subtotal;

	@Column(nullable = false)
	private BigDecimal taxaFrete;

	@Column(nullable = false)
	private BigDecimal valorTotal;

	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime dataCriacao;
	
	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataCancelamento;
	private LocalDateTime dataEntrega;
	
	private StatusPedido statusPedido;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario cliente;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<>();
	
	@Embedded
	private Endereco enderecoEntrega;
}
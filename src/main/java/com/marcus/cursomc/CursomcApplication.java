package com.marcus.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcus.cursomc.domain.Categoria;
import com.marcus.cursomc.domain.Cidade;
import com.marcus.cursomc.domain.Cliente;
import com.marcus.cursomc.domain.Endereco;
import com.marcus.cursomc.domain.Estado;
import com.marcus.cursomc.domain.ItemPedido;
import com.marcus.cursomc.domain.Pagamento;
import com.marcus.cursomc.domain.PagamentoComBoleto;
import com.marcus.cursomc.domain.PagamentoComCartao;
import com.marcus.cursomc.domain.Pedido;
import com.marcus.cursomc.domain.Produto;
import com.marcus.cursomc.domain.enums.EstadoPagamento;
import com.marcus.cursomc.domain.enums.TipoCliente;
import com.marcus.cursomc.repositories.CategoriaRepository;
import com.marcus.cursomc.repositories.CidadeRepository;
import com.marcus.cursomc.repositories.ClienteRepository;
import com.marcus.cursomc.repositories.EnderecoRepository;
import com.marcus.cursomc.repositories.EstadoRepository;
import com.marcus.cursomc.repositories.ItemPedidoRepository;
import com.marcus.cursomc.repositories.PagamentoRepository;
import com.marcus.cursomc.repositories.PedidoRepository;
import com.marcus.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository repo;
	@Autowired
	private ProdutoRepository repo2;
	@Autowired
	private CidadeRepository repo3;
	@Autowired
	private EstadoRepository repo4;
	@Autowired
	private EnderecoRepository repo7;
	@Autowired
	private ClienteRepository repo8;
	@Autowired
	private PedidoRepository repo9;
	@Autowired
	private PagamentoRepository repo10;
	@Autowired
	private ItemPedidoRepository repo11;
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria (null, "Informatica");
		Categoria cat2 = new Categoria (null, "Teste");
		Produto p1 = new Produto (null, "PC", 2.440);
		Produto p2 = new Produto (null, "Mouse", 22.0);
		Produto p3 = new Produto (null, "Monitor", 300.0);
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		repo.saveAll(Arrays.asList(cat1, cat2));
		repo2.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado (null, "Minas Gerais"); 
		Estado est2 = new Estado (null, "SP"); 
		Cidade c1 = new Cidade(null, "Avaré", est2);
		Cidade c2 = new Cidade(null, "Ouro Preto", est1);
		Cidade c3 = new Cidade(null, "Arandu", est2);
		est1.getCidades().addAll(Arrays.asList(c2));
		est2.getCidades().addAll(Arrays.asList(c1, c3));
		repo4.saveAll(Arrays.asList(est1, est2));
		repo3.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "12341234123",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("1234","12341234"));
		Endereco end1 = new Endereco(null, "Rua dos perdedores", "300", "nada", "teste", "1234", cli1, c1);
		Endereco end2 = new Endereco(null, "Rua dos perdedores 2", "3320", "nadda", "testee", "1234789", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		repo8.saveAll(Arrays.asList(cli1));
		repo7.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("12-11-1999 10:30"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("22-11-1999 10:30"), cli1, end2);
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20-10-2017 00:00"), null );
		ped2.setPagamento(pagto2);
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		repo9.saveAll(Arrays.asList(ped1, ped2));
		repo10.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p2.getItens().addAll(Arrays.asList(ip2));
		
		repo11.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}

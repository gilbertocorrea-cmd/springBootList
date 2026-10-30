// Organizo este arquivo no pacote com.lab.mvc.springbootlist.repository.
package com.lab.mvc.springbootlist.repository;

// Importo a entidade que representa o produto na memória e no mapeamento do banco.
import com.lab.mvc.springbootlist.model.Product;

// Importo o contrato que me fornece as operações básicas de persistência.
import org.springframework.data.jpa.repository.JpaRepository;

// Declaro meu contrato de acesso aos produtos. Com extends, herdo as operações de JpaRepository.
// Com <Product, Long>, informo a entidade e o tipo de seu ID.
// Deixo o Spring Data fornecer um objeto que implemente esse contrato.
// Posso chamar save, findAll, findById e deleteById sem escrever suas implementações.
// Ao usar findById, recebo Optional: uma caixa com o produto ou vazia.
// Não executo consultas só por declarar a interface; preciso chamar seus métodos.
public interface ProductRepository extends JpaRepository<Product, Long> {

// Encerro a declaração deste tipo.
}

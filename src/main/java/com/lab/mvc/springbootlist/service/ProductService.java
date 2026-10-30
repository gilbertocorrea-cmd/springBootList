// Organizo este arquivo no pacote com.lab.mvc.springbootlist.service.
package com.lab.mvc.springbootlist.service;

// Importo List para trabalhar com uma lista de produtos.
import java.util.List;

// Importo a anotação com que identifico a camada de serviço.
import org.springframework.stereotype.Service;

// Importo a anotação com que defino a participação em transações.
import org.springframework.transaction.annotation.Transactional;

// Importo a ficha de entrada: nela recebo os dados para cadastrar ou editar o produto.
import com.lab.mvc.springbootlist.dto.ProductRequestDTO;

// Importo a ficha de saída: nela transporto os dados que vou exibir.
import com.lab.mvc.springbootlist.dto.ProductResponseDTO;

// Importo a entidade que representa o produto na memória e no mapeamento do banco.
import com.lab.mvc.springbootlist.model.Product;

// Importo o repositório a que peço as operações de acesso aos dados.
import com.lab.mvc.springbootlist.repository.ProductRepository;

// Importo a exceção que lanço quando não encontro o produto solicitado.
import jakarta.persistence.EntityNotFoundException;

// Peço ao Spring que gerencie este serviço e o disponibilize para outras classes.
@Service

// Defino transações para chamadas externas aos métodos públicos pelo objeto gerenciado pelo Spring.
// Agrupo as operações para que possam ser confirmadas juntas.
// Por padrão, se uma exceção não verificada escapar, tenho rollback das alterações da transação.
@Transactional

// Centralizo aqui as operações com produtos e a conversão entre DTOs e entidades.
public class ProductService {

    // Guardo o repositório que recebo no construtor.
    // Com private, restrinjo o acesso direto; com final, não posso trocar essa referência depois de atribuí-la.
    private final ProductRepository repository;

    // Recebo o repositório pelo construtor: isso é injeção de dependência.
    // Como só declaro um construtor, deixo o Spring fornecer essa dependência automaticamente.
    public ProductService(ProductRepository repository) {

        // Guardo o parâmetro repository no campo this.repository para usá-lo nos outros métodos.
        this.repository = repository;

    // Encerro este bloco.
    }

    // Declaro uma operação sem argumentos que devolve uma lista de fichas de saída.
    public List<ProductResponseDTO> listAll() {

        // Busco todas as entidades Product e devolvo o resultado da transformação abaixo.
        return repository.findAll()

                // Abro um fluxo para processar cada produto da lista.
                .stream()

                // Transformo o Product em um DTO chamando new ProductResponseDTO(produto).
                // Uso map sobre o fluxo na listagem e sobre o Optional na busca por ID.
                .map(ProductResponseDTO::new)

                // Reúno os DTOs em uma lista não modificável, que devolvo a quem chamou listAll.
                .toList();

    // Encerro este bloco.
    }

    // Recebo um ID e devolvo a ficha de saída do produto encontrado.
    public ProductResponseDTO get(Long id) {

        // Procuro pelo ID e recebo um Optional: penso nele como uma caixa com produto ou vazia.
        return repository.findById(id)

                // Transformo o Product em um DTO chamando new ProductResponseDTO(produto).
                // Uso map sobre o fluxo na listagem e sobre o Optional na busca por ID.
                .map(ProductResponseDTO::new)

                // Se a caixa estiver vazia, lanço o erro com o ID procurado; senão, devolvo o DTO.
                // Com () ->, só crio a exceção quando preciso dela. Com +, junto o ID à mensagem.
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + id));

    // Encerro este bloco.
    }

    // Recebo a ficha de entrada e preparo uma entidade para salvar.
    // Uso void porque não devolvo um valor. Só receber o DTO não aciona suas validações.
    public void save(ProductRequestDTO dto) {

        // Crio uma ficha Product em memória. Ainda não gravei nada no banco.
        Product product = new Product();

        // Leio dto.id() e chamo o setter do Lombok para colocar esse valor na entidade.
        // Neste modelo, o Spring Data normalmente identifica ID null como entidade nova.
        // Com ID preenchido, normalmente usa merge; não posso garantir UPDATE apenas pela presença do ID.
        product.setId(dto.id());

        // Leio o nome no DTO e chamo o setter para guardá-lo no Product.
        product.setNome(dto.nome());

        // Leio a marca no DTO e chamo o setter para guardá-la no Product.
        product.setMarca(dto.marca());

        // Leio o país no DTO e chamo o setter para guardá-lo no Product.
        product.setPaisFabricacao(dto.paisFabricacao());

        // Leio o preço no DTO e chamo o setter para guardá-lo no Product.
        product.setPreco(dto.preco());

        // Entrego a entidade ao repositório para persistência.
        // Não presumo SQL imediato: ele pode ser enviado no flush ou na confirmação da transação.
        repository.save(product);

    // Encerro este bloco.
    }

    // Recebo o ID do produto a excluir; com void, não devolvo um valor.
    public void delete(Long id) {

        // Solicito ao repositório a exclusão correspondente ao ID recebido.
        repository.deleteById(id);

    // Encerro este bloco.
    }

// Encerro a declaração deste tipo.
}

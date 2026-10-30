// Organizo este arquivo no pacote com.lab.mvc.springbootlist.controller.
package com.lab.mvc.springbootlist.controller;

import java.util.List;
// Importo a anotação que identifica o controlador das páginas web.
import org.springframework.stereotype.Controller;
// Importo Model: uso esse objeto como uma sacola de dados que entrego ao HTML.
import org.springframework.ui.Model;
// Importo o objeto em que consulto os erros de preenchimento e de validação do formulário.
import org.springframework.validation.BindingResult;
// Importo a anotação com que ligo um endereço GET a um método.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
// Importo a anotação com que leio um valor dentro do endereço, como o 5 em /edit/5.
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// Importo a anotação com que defino a parte inicial dos endereços do controlador.
import org.springframework.web.bind.annotation.RequestMapping;
// Importo o recurso com que envio mensagens temporárias depois de um redirecionamento.
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Importo a ficha de entrada: nela recebo os dados para cadastrar ou editar o produto.
import com.lab.mvc.springbootlist.dto.ProductRequestDTO;
import com.lab.mvc.springbootlist.dto.ProductResponseDTO;
// Importo o serviço a que peço as operações de listar, buscar, salvar e excluir.
import com.lab.mvc.springbootlist.service.ProductService;

import jakarta.validation.Valid;

// Marco esta classe como controlador MVC: aqui recebo pedidos do navegador e escolho as páginas.
@Controller
// Defino /products como início dos endereços atendidos por esta classe.
@RequestMapping("/products")
// Organizo aqui o atendimento das páginas. Delego o trabalho com os produtos ao Service.
public class ProductController {
    // Guardo o serviço que recebo no construtor.
    // Com private, restrinjo o acesso direto; com final, mantenho a mesma referência após a atribuição.
    private final ProductService service;

    // Recebo o serviço pelo construtor, em vez de criá-lo com new.
    // Como só declaro este construtor, deixo o Spring fornecer o serviço automaticamente.
    public ProductController(ProductService service) {
        // Guardo o parâmetro service no campo this.service para reutilizá-lo nos métodos abaixo.
        this.service = service;
    // Encerro este bloco.
    }

    // Atendo uma requisição GET. Combino este caminho com /products definido acima.
    // Como este @GetMapping não tem caminho adicional, uso /products definido na classe.
    @GetMapping
    // Recebo um Model para levar os dados à página e devolvo uma String com o nome da tela.
    public String listProducts(Model model) {
        // Peço todos os produtos ao serviço e guardo a lista de respostas.
        List<ProductResponseDTO> products = service.listAll();
        // Quero colocar a lista na sacola Model com a etiqueta listProducts.
        model.addAttribute("listProducts", products);
        // Escolho o template index.html para exibir os dados que coloquei no Model.
        return "index";
    // Encerro este bloco.
    }

    // Atendo uma requisição GET. Combino este caminho com /products definido acima.
    // Abro o formulário de cadastro em GET /products/new.
    @GetMapping("/new")
    // Preparo a tela de cadastro; neste método ainda não salvo nenhum produto.
    public String showCreateForm(Model model) {

        // Coloco uma ficha vazia no Model com o nome product, usado pelo formulário.
        // Passo null para ID e preço e textos vazios para nome, marca e país.
        model.addAttribute("product", new ProductRequestDTO(null, "", "", "", null));
        // Informo no Model que estou preparando um cadastro, não uma edição.
        model.addAttribute("isEdit", false);
        // Escolho o template new_product.html, onde vou preencher o cadastro.
        return "new_product";
    // Encerro este bloco.
    }

    // Recebo o envio do formulário em POST /products/save.
    // Uso PostMapping, que já importei, para tratar o envio por POST.
    @PostMapping("/save")
    // Recebo o formulário, verifico seus erros e, se estiver válido, peço ao serviço que salve.
    public String saveProduct(
            // Com ModelAttribute, vinculo os campos enviados ao DTO chamado product.
            // Com Valid, aciono as regras declaradas no DTO, como NotBlank e DecimalMin.
            // Uso Valid e ModelAttribute, que já importei, para validar e receber os dados do formulário.
            @Valid @ModelAttribute("product") ProductRequestDTO product,
            // Recebo o resultado da validação e da conversão dos campos.
            // Mantenho este parâmetro imediatamente depois do DTO validado para tratar seus erros.
            BindingResult result,
            // Quero receber o recurso para mensagens após o redirecionamento.
            // Recebo RedirectAttributes para levar a mensagem à próxima página.
            RedirectAttributes attr) {

        // Se encontro algum erro no formulário, entro neste bloco e não chamo o serviço para salvar.
        if (result.hasErrors()) {

            // Escolho a página para reapresentar o formulário com os erros.
            // Leio a expressão assim: se o ID é null, retorno new_product; caso contrário, edit_product.
            // Uso ? para o resultado verdadeiro e : para o resultado falso.
            return (product.id() == null) ? "new_product" : "edit_product";
        // Encerro este bloco.
        }
        // Entrego o DTO validado ao serviço para preparar e persistir o produto.
        service.save(product);
        // Guardo uma mensagem temporária de sucesso para aparecer depois do redirecionamento.
        attr.addFlashAttribute("success", "Registro salvo com sucesso!");
        // Peço ao navegador uma nova requisição GET para /products.
        // Após salvar, uso esse redirecionamento para evitar reenviar o POST ao atualizar a página.
        return "redirect:/products";
    // Encerro este bloco.
    }

    // Atendo uma requisição GET. Combino este caminho com /products definido acima.
    // Abro o formulário de edição em GET /products/edit/{id}.
    @GetMapping("/edit/{id}")
    // Leio o ID do endereço com PathVariable; em /products/edit/5, recebo o número 5.
    // Recebo também o Model para entregar o produto à página de edição.
    public String showEditProductPage(@PathVariable("id") Long id, Model model) {
        // Peço ao serviço o produto correspondente ao ID.
        // Se o produto não existir, o serviço lança a exceção de produto não encontrado.
        ProductResponseDTO response = service.get(id);

        // Monto a ficha de entrada já preenchida para editar os dados existentes.
        // Copio os valores do DTO de saída na ordem exigida pelo construtor do DTO de entrada.
        ProductRequestDTO product = new ProductRequestDTO(
                // Copio o ID para que o formulário identifique o produto que estou editando.
                response.id(),
                // Copio o nome atual para preencher o formulário.
                response.nome(),
                // Copio a marca atual para preencher o formulário.
                response.marca(),
                // Copio o país atual para preencher o formulário.
                response.paisFabricacao(),
                // Copio o preço atual e termino a criação da ficha de entrada.
                response.preco());

        // Entrego a ficha preenchida à página com o nome product, usado em th:object.
        model.addAttribute("product", product);
        // Escolho o template edit_product.html para mostrar o formulário de edição.
        return "edit_product";
    // Encerro este bloco.
    }

    // Atendo uma requisição GET. Combino este caminho com /products definido acima.
    // Atendo a exclusão do tutorial em GET /products/delete/{id}.
    @GetMapping("/delete/{id}")
    // Recebo o ID pelo endereço e o recurso de mensagens temporárias.
    // Neste exemplo do tutorial, atendo a exclusão por GET /products/delete/{id}.
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes attr) {
        // Peço ao serviço que exclua o produto identificado por esse ID.
        service.delete(id);
        // Preparo a mensagem de exclusão para exibi-la na próxima página.
        attr.addFlashAttribute("success", "Registro excluido com sucesso!");
        // Peço ao navegador uma nova requisição GET para /products.
        // Após excluir, volto à listagem para mostrar os dados atualizados e a mensagem temporária.
        return "redirect:/products";
    // Encerro este bloco.
    }

// Encerro a declaração deste tipo.
}

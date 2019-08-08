package br.com.sistema.controller;

import br.com.sistema.model.Cliente;
import br.com.sistema.model.Product;
import br.com.sistema.repository.ClienteRepository;
import br.com.sistema.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    private ProductRepository productRepository;
    
    private ClienteRepository clienteRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Autowired
    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/products/add", method = RequestMethod.GET)
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "edit";
    }

    @RequestMapping(path = "products", method = RequestMethod.POST)
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "redirect:/";
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @RequestMapping(path = "/products/edit/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("product", productRepository.findOne(id));
        return "edit";
    }

    @RequestMapping(path = "/products/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(name = "id") String id) {
        productRepository.delete(id);
        return "redirect:/products";
    }
    
    @RequestMapping(path = "/cadastroClientes", method = RequestMethod.GET)
    public String cadastroClientes(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastro_cliente";
    }
    
    @RequestMapping(path = "cadastrarCliente", method = RequestMethod.POST)
    public String cadastrarCliente(Cliente cliente) {
        
    	System.out.println("Teste gravacaoCliente");
    	clienteRepository.save(cliente);
        return "redirect:/";
    }

}

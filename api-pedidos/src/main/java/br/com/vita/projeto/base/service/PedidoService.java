package br.com.vita.projeto.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import br.com.vita.projeto.base.model.dto.ClienteReturn;
import br.com.vita.projeto.base.model.dto.PedidoDTO;
import br.com.vita.projeto.base.model.dto.ProdutoReturn;
import br.com.vita.projeto.base.model.entities.Pedido;
import br.com.vita.projeto.base.model.entities.PedidoMongo;
import br.com.vita.projeto.base.model.enums.PedidoStatus;
import br.com.vita.projeto.base.repository.PedidoRepository;
import br.com.vita.projeto.base.repository.PedidoRepositoryMongo;
@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;

    @Autowired
    PedidoRepositoryMongo repositoryMongo;

    public ResponseEntity<?> criarPedido(PedidoDTO pedidoDTO) {
        
        ClienteReturn cliente = null;
        try {
            cliente = ClienteRest.consultarCpf(pedidoDTO.getCpf());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(404).body("CPF informado invalido!!");
        }

        String ruaEntrega = "";
        try {
            ruaEntrega = EnderecoRest.buscarEndereco(pedidoDTO.getCepEntrega());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(404).body("CEP Invalido!");
        }

        ProdutoReturn produto = null;
        try {
            produto = ProdutoRest.buscarProduto(pedidoDTO.getIdProduto());
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(404).body("ID Produto invalido!!");
        }
        if(produto != null){
            if(produto.getQuantidadeEstoque() < pedidoDTO.getQuantidade()){
                return ResponseEntity.status(409).body("Quantidade de produto insuficiente!!");
            }
        }

        Pedido pedido = new Pedido(produto.getId(), cliente.getId(), ruaEntrega, produto.getPreco() * pedidoDTO.getQuantidade());
        repository.save(pedido);

        PedidoMongo pedidoMongo = new PedidoMongo(produto.getId(), cliente.getId(), ruaEntrega, produto.getPreco() * pedidoDTO.getQuantidade());
        repositoryMongo.save(pedidoMongo);

        return ResponseEntity.status(200).body("Pedido Gerado com Sucesso!");
    }

    public ResponseEntity<?> buscarPedido(Integer id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(404).body("Pedido não encontrado no sistema!");
        }
        return ResponseEntity.status(200).body(repository.findById(id));
    }

    public ResponseEntity<String> processarPedido(Integer id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(404).body("Pedido não encontrado no sistema!");
        }
        Pedido pedido = repository.findById(id).get();
        pedido.setStatus(PedidoStatus.PROCESSADO);
        repository.save(pedido);
        return ResponseEntity.status(200).body("Pedido processo com sucesso!");
    }
    
}

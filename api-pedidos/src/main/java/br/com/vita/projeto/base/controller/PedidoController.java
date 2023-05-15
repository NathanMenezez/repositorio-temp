package br.com.vita.projeto.base.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vita.projeto.base.model.dto.PedidoDTO;
import br.com.vita.projeto.base.service.PedidoService;

@RestController
public class PedidoController {
    @Autowired
    PedidoService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> criarPedido(@RequestBody @Valid PedidoDTO pedidoDTO){
        return service.criarPedido(pedidoDTO);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarPedido(@PathVariable Integer id){
        return service.buscarPedido(id);
    }

    @PutMapping("/atualizar/pedido/{id}")
    public ResponseEntity<String> processarPedido(@PathVariable Integer id){
        return service.processarPedido(id);
    }

}

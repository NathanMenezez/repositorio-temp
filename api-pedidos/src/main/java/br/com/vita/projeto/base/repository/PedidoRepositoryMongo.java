package br.com.vita.projeto.base.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.vita.projeto.base.model.entities.PedidoMongo;

@Repository
public interface PedidoRepositoryMongo extends MongoRepository<PedidoMongo, String>{
}

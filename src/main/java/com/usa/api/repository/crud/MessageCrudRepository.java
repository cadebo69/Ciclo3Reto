package com.usa.api.repository.crud;

import com.usa.api.model.Message;
import org.springframework.data.repository.CrudRepository;


public interface MessageCrudRepository extends CrudRepository<Message, Integer>{
    
}

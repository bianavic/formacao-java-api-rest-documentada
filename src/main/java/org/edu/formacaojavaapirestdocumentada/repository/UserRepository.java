package org.edu.formacaojavaapirestdocumentada.repository;

import org.edu.formacaojavaapirestdocumentada.handler.BusinessException;
import org.edu.formacaojavaapirestdocumentada.handler.RequiredFieldException;
import org.edu.formacaojavaapirestdocumentada.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public void save(User user){

        if (user.getLogin() == null) throw new RequiredFieldException("login");
        if (user.getPassword() == null) throw new RequiredFieldException("password");

        System.out.println("SAVE - Recebendo o usuário na camada de repositório");
        System.out.println(user);
    }

    public void update(User user){
        System.out.println("UPDATE - Recebendo o usuário na camada de repositório");
        System.out.println(user);
    }

    public void remove(Integer id){
        System.out.println(String.format("DELETE/id - Recebendo o id: %d para excluir um usuário", id));
        System.out.println(id);
    }

    public List<User> listAll(){
        System.out.println("LIST - Listando os usários do sistema");
        List<User> usuarios = new ArrayList<>();
        usuarios.add(new User("gleyson","password"));
        usuarios.add(new User("frank","masterpass"));
        return usuarios;
    }
    public User finById(Integer id){
        System.out.println(String.format("FIND/id - Recebendo o id: %d para localizar um usuário", id));
        return new User("gleyson","password");
    }

    public User findByName(String username) {
        System.out.println("FIND/username - Recebendo o username: %d para localizar um usuario" + username);
        return new User("gleyson","password");
    }

}

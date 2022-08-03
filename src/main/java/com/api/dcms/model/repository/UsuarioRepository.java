<<<<<<< HEAD
package com.api.dcms.model.repository;

import com.api.dcms.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin 
}
=======
package com.api.dcms.model.repository;

import com.api.dcms.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}
>>>>>>> cef587aa001fd2eeb9524b6453a24893acffd81d

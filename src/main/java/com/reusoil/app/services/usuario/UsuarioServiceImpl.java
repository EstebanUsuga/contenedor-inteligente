package com.reusoil.app.services.usuario;

import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.repository.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    final private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioEntity> obtenerUsuariosTodos() {
       return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UsuarioEntity guardarUsuario(UsuarioEntity usuarioEntity) {
        usuarioRepository.save(usuarioEntity);
        return usuarioEntity;
    }

    @Override
    public Optional<UsuarioEntity> obtenerUsuarioPorUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario);
    }

    @Override
    public void borradoLogico(Long id) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            UsuarioEntity entidad = usuario.get();
            entidad.setEstado(false);
            usuarioRepository.save(entidad);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioEntity> obtenerUsuariosPorEstado(boolean estado) {
        return usuarioRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorNombre(String nombreUsuario) {
        return usuarioRepository.findByUsuario(nombreUsuario).isPresent();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioEntity> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }


    public UsuarioEntity consultar(String email) {
        return usuarioRepository.findByUsuario(email).orElse(null);
    }


//    public UsuarioEntity registrarUsuario(UsuarioEntity usuarioEntity) {
//        return usuarioRepository.save(usuarioEntity);
//    }
//
//    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
//        return usuarioRepository.findAll();
//    }


//    public UsuarioEntity actualizarUsuario(UsuarioEntity usuarioEntityActualizado) {
//         Optional<UsuarioEntity> usuarioExistente = obtenerUsuarioPorId(usuarioEntityActualizado.getId());
//         if (usuarioExistente.isPresent()) {
//             UsuarioEntity usuarioEntity = usuarioExistente.get();
//             usuarioEntity.setUsuario(usuarioEntityActualizado.getUsuario());
//             usuarioEntity.setClave(usuarioEntityActualizado.getClave());
//             return usuarioEntity;
//         }
//         return null;
//    }


//    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
//         return usuarioEntities;
//    }


}

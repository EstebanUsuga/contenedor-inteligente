package com.reusoil.app.services.empresa;

import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.models.usuario.UsuarioEntity;
import com.reusoil.app.repository.empresa.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmpresaService {

     List<EmpresaEntity> obtenerEmpresas();
     Optional<EmpresaEntity> obtenerEmpresaPorId(Long id);
     void guardar(EmpresaEntity empresa);
     void eliminarEmpresaPorId(Long id);
     Optional<EmpresaEntity> obtenerEmpresaPorNombre(String nombre);

     List<EmpresaEntity> obtenerEmpresasPorEstado(boolean estado);

     void borradoLogico(Long id);


     Optional<EmpresaEntity> obtenerEmpresaPorCorreo(String correo);
}

package com.reusoil.app.services.empresa;

import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.repository.empresa.EmpresaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // Habilita Mockito
class EmpresaServiceImplTest {

    @Mock
    private EmpresaRepository empresaRepository;  // Simula la base de datos

    @InjectMocks
    private EmpresaServiceImpl empresaService;   // Servicio a probar

    @Test
    void obtenerEmpresas_ConRegistros_RetornaLista() {
        // Configura una empresa de prueba
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setNombre("Empresa Test");

        when(empresaRepository.findAll()).thenReturn(List.of(empresa));

        List<EmpresaEntity> resultado = empresaService.obtenerEmpresas();

        assertEquals(1, resultado.size());  // Verifica cantidad
        assertEquals("Empresa Test", resultado.get(0).getNombre());  // Verifica nombre
        verify(empresaRepository).findAll();  // Confirma la consulta
    }

    @Test
    void guardar_EmpresaNueva_GuardaCorrectamente() {
        EmpresaEntity nuevaEmpresa = new EmpresaEntity();
        nuevaEmpresa.setNombre("Nueva Empresa");

        empresaService.guardar(nuevaEmpresa);

        // Captura el objeto guardado para verificar sus datos
        ArgumentCaptor<EmpresaEntity> captor = ArgumentCaptor.forClass(EmpresaEntity.class);
        verify(empresaRepository).save(captor.capture());
        assertEquals("Nueva Empresa", captor.getValue().getNombre());  // Verifica nombre
    }

    @Test
    void eliminarEmpresaPorId_Existente_EliminaFisicamente() {
        Long id = 1L;

        empresaService.eliminarEmpresaPorId(id);

        verify(empresaRepository).deleteById(id);  // Confirma eliminación
    }

    @Test
    void obtenerEmpresaPorNombre_Existente_RetornaEmpresa() {
        String nombre = "Empresa Existente";
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setNombre(nombre);

        when(empresaRepository.findByNombre(nombre)).thenReturn(Optional.of(empresa));

        Optional<EmpresaEntity> resultado = empresaService.obtenerEmpresaPorNombre(nombre);

        assertTrue(resultado.isPresent());  // Verifica existencia
        assertEquals(nombre, resultado.get().getNombre());  // Verifica nombre
    }

    @Test
    void obtenerEmpresaPorId_Existente_RetornaEmpresa() {
        Long id = 1L;
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setId(id);

        when(empresaRepository.findById(id)).thenReturn(Optional.of(empresa));

        Optional<EmpresaEntity> resultado = empresaService.obtenerEmpresaPorId(id);

        assertTrue(resultado.isPresent());  // Verifica existencia
        assertEquals(id, resultado.get().getId());  // Verifica ID
    }

    @Test
    void obtenerEmpresaPorCorreo_Existente_RetornaEmpresa() {
        String correo = "test@empresa.com";
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setCorreo(correo);

        when(empresaRepository.findByCorreo(correo)).thenReturn(Optional.of(empresa));

        Optional<EmpresaEntity> resultado = empresaService.obtenerEmpresaPorCorreo(correo);

        assertTrue(resultado.isPresent());  // Verifica existencia
        assertEquals(correo, resultado.get().getCorreo());  // Verifica correo
    }

    @Test
    void obtenerEmpresaPorId_Inexistente_RetornaVacio() {
        when(empresaRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<EmpresaEntity> resultado = empresaService.obtenerEmpresaPorId(999L);

        assertTrue(resultado.isEmpty());  // Verifica que no existe
    }

    @Test
    void obtenerEmpresas_SinRegistros_RetornaListaVacia() {
        when(empresaRepository.findAll()).thenReturn(Collections.emptyList());

        List<EmpresaEntity> resultado = empresaService.obtenerEmpresas();

        assertTrue(resultado.isEmpty());  // Verifica lista vacía
    }
}
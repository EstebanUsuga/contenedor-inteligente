package com.reusoil.app.services.ciudad;

import com.reusoil.app.models.ciudad.CiudadEntity;
import com.reusoil.app.repository.ciudad.CiudadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CiudadServiceImplTest {

    @Mock
    private CiudadRepository ciudadRepository;  // Simula la base de datos

    @InjectMocks
    private CiudadServiceImpl ciudadService;    // Servicio a probar

    @Test
    void obtenerCiudades_ConEstadoActivo_RetornaLista() {
        // Configura ciudad de prueba
        CiudadEntity ciudad = new CiudadEntity();
        ciudad.setNombre("Medellin");
        when(ciudadRepository.findByEstado(true)).thenReturn(List.of(ciudad));

        List<CiudadEntity> resultado = ciudadService.obtenerCiudades();

        assertEquals(1, resultado.size());
        assertEquals("Medellin", resultado.get(0).getNombre());
    }

    @Test
    void guardarCiudad_NuevaCiudad_GuardaCorrectamente() {
        CiudadEntity ciudad = new CiudadEntity();
        ciudadService.guardarCiudad(ciudad);
        verify(ciudadRepository).save(ciudad);  // Verifica que se guardó
    }

    @Test
    void obtenerCiudadPorId_Existente_RetornaCiudad() {
        Long id = 1L;
        CiudadEntity ciudad = new CiudadEntity();
        ciudad.setId(id);
        when(ciudadRepository.findById(id)).thenReturn(Optional.of(ciudad));

        Optional<CiudadEntity> resultado = ciudadService.obtenerCiudadPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
    }

    @Test
    void obtenerCiudadPorNombre_Existente_RetornaCiudad() {
        String nombre = "Barranquilla";
        CiudadEntity ciudad = new CiudadEntity();
        ciudad.setNombre(nombre);
        when(ciudadRepository.findByNombre(nombre)).thenReturn(Optional.of(ciudad));

        Optional<CiudadEntity> resultado = ciudadService.obtenerCiudadPorNombre(nombre);

        assertTrue(resultado.isPresent());
        assertEquals(nombre, resultado.get().getNombre());
    }

    @Test
    void eliminarPorId_Existente_EliminaFisicamente() {
        Long id = 1L;
        ciudadService.eliminarPorId(id);
        verify(ciudadRepository).deleteById(id);  // Verifica eliminación
    }
}
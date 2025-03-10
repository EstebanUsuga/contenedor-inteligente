package com.reusoil.app.services.contenedor;

import com.reusoil.app.models.contenedor.ContenedorEntity;
import com.reusoil.app.repository.contenedor.ContenedorRepository;
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

@ExtendWith(MockitoExtension.class)  // Habilita Mockito
class ContenedorServiceImplTest {

    @Mock
    private ContenedorRepository contenedorRepository;  // Simula la base de datos

    @InjectMocks
    private ContenedorServiceImpl contenedorService;    // Servicio a probar

    @Test
    void obtenerContenedores() {
        // Configura un contenedor de prueba
        ContenedorEntity contenedor = new ContenedorEntity();
        List<ContenedorEntity> listaEsperada = Collections.singletonList(contenedor);

        when(contenedorRepository.findAll()).thenReturn(listaEsperada);

        List<ContenedorEntity> resultado = contenedorService.obtenerContenedores();

        assertEquals(1, resultado.size());  // Verifica la cantidad
        verify(contenedorRepository).findAll();  // Confirma que se llamó al método
    }

    @Test
    void obtenerContenedorPorId() {
        Long id = 1L;
        ContenedorEntity contenedor = new ContenedorEntity();

        when(contenedorRepository.findById(id)).thenReturn(Optional.of(contenedor));

        Optional<ContenedorEntity> resultado = contenedorService.obtenerContenedorPorId(id);

        assertTrue(resultado.isPresent());  // Verifica que existe
        verify(contenedorRepository).findById(id);  // Confirma la búsqueda por ID
    }

    @Test
    void guardarContenedor() {
        ContenedorEntity contenedor = new ContenedorEntity();

        contenedorService.guardar(contenedor);

        verify(contenedorRepository).save(contenedor);  // Verifica que se guardó
    }

    @Test
    void eliminarContenedorPorId() {
        Long id = 1L;

        contenedorService.eliminarContenedorPorId(id);

        verify(contenedorRepository).deleteById(id);  // Confirma la eliminación
    }
}
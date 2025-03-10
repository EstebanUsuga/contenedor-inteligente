// Declaración del paquete: Indica la ubicación de esta clase dentro del proyecto.
package com.reusoil.app.controller.contenedor;

// Importaciones de clases necesarias para el funcionamiento del controlador.
import com.reusoil.app.models.contenedor.ContenedorEntity;
import com.reusoil.app.services.contenedor.ContenedorService;
import com.reusoil.app.services.empresa.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// Controlador para manejar las acciones relacionadas con los contenedores.
// Incluye la lógica para guardar o actualizar un contenedor y para eliminarlo.
@RequiredArgsConstructor
@Controller
@RequestMapping("/contenedor")
public class ContenedorController {

    // Inyecta los servicios necesarios para manejar la lógica de contenedores y empresas.
    private final ContenedorService contenedorService;
    private final EmpresaService empresaService;

    /**
     * Guarda o actualiza la información de un contenedor.
     *
     * @param contenedor Es un Objeto que contiene los datos del contenedor obtenidos del formulario.
     * @param bindingResult Contiene el resultado de las validaciones realizadas en el formulario.
     * @param model Es el Objeto que permite enviar información a la vista.
     * @return Redirige a la lista de contenedores si todo es correcto
     *         en caso de errores, retorna la misma vista de formulario para corregirlos.
     */
    @PostMapping("/guardar")
    public String guardarContenedor(@Valid @ModelAttribute("contenedor") ContenedorEntity contenedor,
                                    BindingResult bindingResult, Model model) {
        // Verifica si existen errores de validación en los datos del formulario.
        if (bindingResult.hasErrors()) {
            // Si hay errores, se notifica al usuario y se reenvía al formulario,
            // junto con la lista de empresas y el contenedor que se intentaba guardar.
            model.addAttribute("error", "Por favor, corrija los errores en el formulario.");
            model.addAttribute("modoEdicion", true);
            model.addAttribute("contenedor", contenedor);
            model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
            return "vistas/contenedor/form_contenedor";
        }

        // Si no hay errores de validación, se guarda o actualiza el contenedor
        // en la base de datos a través del servicio.
        contenedorService.guardar(contenedor);

        // Redirige a la lista de contenedores tras la operación exitosa.
        return "redirect:/contenedor/listado-contenedores";
    }

    /**
     * Elimina un contenedor identificado por su ID.
     *
     * @param id Identificador único del contenedor a eliminar.
     * @return Redirige a la lista de contenedores tras completar la eliminación.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarContenedor(@PathVariable("id") Long id) {
        // Llama al servicio para eliminar el contenedor de la base de datos.
        contenedorService.eliminarContenedorPorId(id);
        // Retorna a la página que muestra el listado de contenedores.
        return "redirect:/contenedor/listado-contenedores";
    }
}
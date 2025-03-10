//alert("Integrado con JS");

document.addEventListener('DOMContentLoaded', function () {
    const container = document.getElementById('container');
    const registerBtn = document.getElementById('register');
    const loginBtn = document.getElementById('login');

    // Recupera la selección guardada en localStorage al cargar la página
    const selectedView = localStorage.getItem('selectedView');
    if (selectedView === 'register') {
        container.classList.add("active"); // Muestra la vista de registro
    } else {
        container.classList.remove("active"); // Muestra la vista de login (por defecto)
    }

    // Al hacer clic en el botón de "Registro", guarda la selección en localStorage
    registerBtn.addEventListener('click', () => {
        container.classList.add("active");
        localStorage.setItem('selectedView', 'register');
    });

    // Al hacer clic en el botón de "Login", guarda la selección en localStorage
    loginBtn.addEventListener('click', () => {
        container.classList.remove("active");
        localStorage.setItem('selectedView', 'login');
    });
});

function validar(msgErrorEtiquetaId) {
    let validado = true;
    let usuarioV = "usuario123";
    let claveV = "clave123";

    let msgs = document.querySelectorAll("small[id^='msg']");

    for (let mensaje of msgs) {
        mensaje.innerHTML = '';
    }

    let usuario = document.getElementById("usuarioLogin"); 
    let clave = document.getElementById("claveLogin");

    if(!validarVacio(usuario, "Usuario no puede estar vacio", msgErrorEtiquetaId)) { return false; }
    if(!validarVacio(clave, "Clave no puede estar vacia", msgErrorEtiquetaId)) { return false; }

    let msgError = document.querySelector(msgErrorEtiquetaId);

    if (usuario.value == '' && clave.value == '') {
        msgError.innerHTML += "Ingrese sus credenciales. <br/>";
        validado = false;
    }

    /*else if (usuario.value !== usuarioV) {
        msgError.innerHTML += "Usuario incorrecto. <br/>";
        validado = false;
    }

    else if (clave.value !== claveV) {
        msgError.innerHTML += "Contraseña incorrecta. <br/>";
        validado = false;
    }*/
    
    return validado;
}

//_____________________________________________________________________________________________________

function validarVacio(valor, mensajeError, etiquetaId) {
    let msgError = document.querySelector(etiquetaId);

    if (valor.value.trim() === '') { // trim elimina espacios en blanco al inicio y al final
        msgError.innerHTML = mensajeError;
        return false;
    }

    return true;
}

function validarFormulario(event, campoId, mensajeError) {
    event.preventDefault(); // Evita el envío del formulario por defecto

    const campo = document.getElementById(campoId);
    const errorLabel = document.querySelector(`#msgError${campoId}`);

    if (!validarVacio(campo, mensajeError, `#msgError${campoId}`)) {
        return false;
    }

    // Si el campo pasa la validación, envía el formulario
    event.target.submit();
}

//______________________________________________________________________________________________________

function validarFormulario(formId, campos) {
    let validado = true;

    // Limpia mensajes de error
    campos.forEach(campo => {
        const mensaje = document.getElementById(campo.msgId);
        mensaje.innerHTML = '';
    });

    // Validación por cada campo
    campos.forEach(campo => {
        const input = document.getElementById(campo.id);
        const msgError = document.getElementById(campo.msgId);

        // Validación de campo vacío
        if (input.value.trim() === '') {
            msgError.innerHTML = campo.errorVacio;
            validado = false;
            return; // Detiene la validación en este campo
        }

        // Validación para el campo de teléfono
        if (campo.id === 'telefono') {
            const telefonoRegex = /^\d{10}$/; // Expresión regular para 10 dígitos
            if (!telefonoRegex.test(input.value)) {
                msgError.innerHTML = 'El teléfono debe contener solo números y tener exactamente 10 dígitos.';
                validado = false;
            }
        }

        // Validación de formato de correo
        if (campo.tipo === 'email' && !validarEmail(input.value)) {
            msgError.innerHTML = campo.errorFormato;
            validado = false;
        }

        // Validación de contraseña
        if (campo.tipo === 'password' && input.value.length < 6) {
            msgError.innerHTML = campo.errorFormato;
            validado = false;
        }
    });

    return validado; // Devuelve true solo si todos los campos son válidos
}



// Validación del formulario de registro
function validarRegistro() {
    return validarFormulario('register-form', [
        { id: 'usuarioRegistro', msgId: 'msgErrorUsuarioRegistro', tipo: 'text', errorVacio: 'Usuario no puede estar vacío', errorFormato: 'Usuario inválido' },
        { id: 'claveRegistro', msgId: 'msgErrorClaveRegistro', tipo: 'password', errorVacio: 'Contraseña no puede estar vacía', errorFormato: 'Contraseña debe tener al menos 6 caracteres' }
    ]);
}

// Validación del formulario de login
function validarLogin() {
    return validarFormulario('login-form', [
        { id: 'usuarioLogin', msgId: 'msgErrorUsuarioLogin', tipo: 'text', errorVacio: 'Usuario no puede estar vacío', errorFormato: 'Usuario inválido' },
        { id: 'claveLogin', msgId: 'msgErrorClaveLogin', tipo: 'password', errorVacio: 'Contraseña no puede estar vacía', errorFormato: 'Contraseña debe tener al menos 6 caracteres' }
    ]);
}

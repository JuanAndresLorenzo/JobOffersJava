// $(document).ready(function () {

//     $("#form_usuario").validate({

//         rules: {

//             usuario: {
//                 required: true,
//                 maxLength: 50
//             },
//             clave: {
//                 required: true,
//                 minlength: 8,
//                 maxLength: 50
//             },

//         }

//     });

// });
$(document).ready(function () {
 document.getElementById('btn_ingresar').addEventListener('click',  function(e){
    try{
        let usrName = document.getElementById('usuario'); 
        let password = document.getElementById('clave');
        let errorUsrIncorrecto = document.getElementById('errorUsuarioIncorrecto');
        let errorUsrVacio = document.getElementById('errorUsuarioVacio');
        let errorClaveVacia = document.getElementById('errorClaveVacia');
        
        errorUsrVacio.style.visibility = ' hidden';
        errorClaveVacia.style.visibility = ' hidden';
        errorUsrIncorrecto.style.visibility = ' hidden';

        e.stopPropagation();
        if(usrName.value == ''){
            errorUsrVacio.style.visibility = ' visible';
            
        }else if(password.value == ''){
            errorClaveVacia.style.visibility = ' visible';
        }else{
            if(usrName.value == "lgarcia" && password.value == "awdrg543"){
                window.location.href = "./indexPostulante.html";
            }else if(usrName.value == "ecotech" && password.value == "qsxcdw43"){
                window.location.href = "./indexEmpresa.html";
            }else{
                if(errorUsrIncorrecto){
                    errorUsrIncorrecto.style.visibility = ' visible';
                }
            }
        }
    }catch(error){
        alert(error);
    }
 });
});
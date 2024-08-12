$(document).ready(function () {
    console.log("ready!");

    ocultar_campos_paquete();


    $("#usar_paquete_checkbox").change(function () {


        if(this.checked) {
            mostrar_campos_paquete();
        }else{
            ocultar_campos_paquete();
        }


    });

    $("#form_oferta").validate({

        rules: {

            nombre: {
                required: true,
                maxlength: 50
            },
            fecha_alta: {
                required: true,
                fecha: true
            },
            descripcion: {
                required: true,
                maxlength: 500
            },
            ciudad: {
                required: false,
                maxlength: 50
            },
            departamento: {
                required: false,
                maxlength: 50
            },
            horario: {
                required: true,
                maxlength: 50
            },
            remuneracion: {
                required: true,
                number: true,
                min: 0
            },
            tipo_publicacion: {
                required: true,
            },

        }

    });

    /*
    $("button[type='submit']").click(function () {

        alert("enviar");

        validar_clave();

    });*/


});

function ocultar_campos_paquete(){

    $("#div_paquete").hide();

}
function mostrar_campos_paquete(){

    $("#div_paquete").show();

}
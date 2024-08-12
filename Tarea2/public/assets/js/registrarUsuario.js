$(document).ready(function () {
    console.log("ready!");

    campos();
    ocultar_campos_clave();


    $("#tipo_usuario").change(function () {

        campos();
    });

    $("#btn_cambiar_clave_usuario").click(function () {

        mostrar_campos_clave();
    });

    // $("form").on('submit', function (e) {
    //
    //     validar_clave();
    //
    //     /*
    //     if (isvalid) {
    //         e.preventDefault();
    //         alert(getvalues("myform"));
    //     }*/
    //
    // });

    $("#form_registrar_usuario").validate({

        rules: {

            tipo_usuario: {
                required: true
            },
            nombre: {
                required: true,
                maxlength: 50
            },
            apellido: {
                required: true,
                maxlength: 50
            },
            nickname: {
                required: true,
                maxlength: 50
            },
            correo: {
                required: true,
                maxlength: 150,
                email: true
            },
            clave: {
                required: true,
                maxlength: 50,
                minlength: 8
            },
            clave1: {
                maxlength: 50,
                minlength: 8,
                equalTo: "#clave"
            },
            //postulante
            fecha_nacimiento: {
                required: {

                    depends: function (elem) {

                        return $("#tipo_usuario").val() == "postulante"

                    }

                },
                date: true
            },
            nacionalidad: {
                required: {

                    depends: function (elem) {

                        return $("#tipo_usuario").val() == "postulante"

                    }

                }, maxlength: 50
            },
            //empresa
            nombre_empresa: {
                required: {

                    depends: function (elem) {

                        return $("#tipo_usuario").val() == "empresa"

                    }

                }, maxlength: 50
            },
            sitio_web: {
                maxlength: 50
            },
            descripcion: {
                maxlength: 500
            },

        }

    });

    /*
    $("button[type='submit']").click(function () {

        alert("enviar");

        validar_clave();

    });*/


});

function ocultar_campos_clave() {

    $("#div_cambiar_clave_usuario").hide();

}

function mostrar_campos_clave() {

    $("#div_cambiar_clave_usuario").show();

}

function validar_clave() {

    var clave_campo = $("#clave");
    var clave_campo_valor = clave_campo.val();

    var clave1_campo = $("#clave1");
    var clave1_campo_valor = clave1_campo.val();

    if (clave_campo_valor != clave1_campo_valor) {

        alert("La clave y la clave de confirmación no coinciden");
    }

}


function campos() {


    var tipo_usuario_campo = $("#tipo_usuario");
    var tipo_usuario_campo_valor = tipo_usuario_campo.val();

//Campos empresa
//    var nombre_empresa_campo = $("#nombre_empresa");
    ///  var nombre_empresa_campo_valor = nombre_empresa_campo.val();

    var sitio_web_campo = $("#sitio_web");
    var sitio_web_campo_valor = sitio_web_campo.val();

    var descripcion_campo = $("#descripcion");
    var descripcion_campo_valor = descripcion_campo.val();

//Campos postulante

    var fecha_nacimiento_campo = $("#fecha_nacimiento");
    var fecha_nacimiento_campo_valor = fecha_nacimiento_campo.val();

    var nacionalidad_campo = $("#nacionalidad");
    var nacionalidad_campo_valor = nacionalidad_campo.val();


    if (tipo_usuario_campo_valor == "empresa") {


        $("#div_campos_empresa").show();
        $('#div_campos_postulante').find('input, textarea, select').val('');
        $("#div_campos_postulante").hide();

    } else if (tipo_usuario_campo_valor == "postulante") {


        $('#div_campos_empresa').find('input, textarea, select').val('');
        $("#div_campos_empresa").hide();
        $("#div_campos_postulante").show();


    } else {

        $("#div_campos_empresa").hide();
        $("#div_campos_postulante").hide();

    }


}
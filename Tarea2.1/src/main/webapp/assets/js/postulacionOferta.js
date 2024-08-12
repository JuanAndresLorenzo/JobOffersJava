$(document).ready(function () {
    console.log("ready!");

    $("#form_postulacion").validate({

        rules: {

            fecha: {
                required: true,
                fecha: true
            },
            cv_reducido: {
                required: true,
                maxlength: 500
            },
            motivacion: {
                required: true,
                maxlength: 500
            },

        }

    });

});
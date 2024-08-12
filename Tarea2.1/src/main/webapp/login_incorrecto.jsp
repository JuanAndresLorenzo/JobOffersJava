<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Ofertas Laborales - FING - TPROG 2023 - gr45</title>

    <!-- Plugins de terceros -->
    <link href="plugins/fontawesome-free-5.15.4-web/css/all.min.css" rel="stylesheet">
    <link href="plugins/bootstrap-5.3.1-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="plugins/bootstrap-5.3.1-dist/js/bootstrap.bundle.min.js"></script>
    <script src="plugins/jquery/jquery-3.7.1.min.js"></script>
    <script src="plugins/jquery/jquery-validation-1.19.5/dist/jquery.validate.min.js"></script>
    <script src="plugins/jquery/jquery-validation-1.19.5/dist/localization/messages_es_AR.min.js"></script>

    <!-- Fin Plugins de terceros -->

    <!-- Nuestros estilos -->
    <link href="assets/css/navbar.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <!-- Fin Nuestros estilos -->

    <!-- Nuestros scripts -->
    <script src="assets/js/navbar.js"></script>
    <script src="assets/js/loginUsuario.js"></script>
    <!-- Fin Nuestros scripts -->


</head>

<body class="d-flex flex-column min-vh-100 bg-dark">


<div class="container px-4 px-lg-5 mt-5">

    <div class="row gx-4 gx-lg-5 justify-content-center mb-5">

        <div class="col-5 row text-center p-5">

            <h2 class="text-center text-white">Acceso</h2>
            
            
	<form class="formulario_centrar main" action="iniciar-sesion" ACTION="POST">
		<div class="error">
		<b>La combinación de correo electrónico/contraseña es incorrecta.</b>
			<p>
				Asegurese que la entró correctamente y en el si de que estas seguro
				de que sea correcta, entonces alguien hackeo tu cuenta.
				En ese caso usa el login <code>tprog@tprog.com</code>
				con contraseña <code>manda</code>.
			</p>
		</div>

		<div class="fila_input">
			<label for="error_login">Correo: </label>
			<input id="error_login" type="text" name="login"/>
		</div>

		<div class="fila_input">
			<label for="error_password">Contraseña:</label>
			<input id="error_password" type="password" name="password"/>
		</div>

		<div class="fila_input">
			<input class="con_margen" type="button"
				   value="Entrar" onclick="submit()"/>
		</div>
	</form>

            <form class="bg-light" id="form_usuario" action="iniciar-sesion" method="POST">

                <div class="col-md-12 p-3">
                    <input type="text" class="form-control form-control-lg" id="usuario" name="usuario" placeholder="Usuario" value="">
                    <div id="errorUsuarioVacio">Por favor, ingrese un nombre de usuario</div>
                    <input type="password" class="form-control form-control-lg" id="clave" name="clave" placeholder="Clave" value="">
                    <div id="errorClaveVacia">Por favor ingrese una contraseÃ±a</div>
                    <div id="errorUsuarioIncorrecto">El nombre de usuario o la contraseÃ±a ingresados no son correctos</div>

                </div>

                <div class="col-md-12 p-3">

                    <a type="button" id="btn_cancelar" class="btn btn-lg btn-danger" name="Cancelar" href="index.html">
                        Cancelar
                    </a>
                    <button type="submit" id="btn_ingresar" class="btn btn-lg btn-primary" name="ingresar">
                        Ingresar
                    </button>

                </div>

            </form>

        </div>
    </div>
</div>


<footer class="bg-light text-center text-lg-start mt-auto">
    <!-- Copyright -->
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        Â© 2023 Copyright:
        <a class="text-dark" href="https://www.fing.edu.uy">FING - TPROG - GR45</a>
    </div>
    <!-- Copyright -->
</footer>

</body>

</html>
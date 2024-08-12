package validaciones;

public class Validador {

	public Validador() {

	}

	// Valido largo de campo: cantidad de caracteres
	public Boolean validarLargo(String valor, Integer largo) {

		if (valor.length() <= largo) {
			return true;
		}
		return false;

		// "Este campo no puede exceder los " + largo_max + " caracteres de largo");

	}

	// Valido que sólo acepte letras minúsculas, números, guión medio, guión bajo o
	// punto. Sin espacios, letras mayúsculas, etc.
	public Boolean validarUsuario(String valor) {

		if (valor.matches("^[a-z0-9._-]+$")) {

			return true;
		}

		return false;
		// "El usuario sólo puede contener letras minúsculas, números, guión medio,
		// guión bajo o punto.");

	}

	// Validar dirección de correo válido
	public Boolean validarCorreo(String valor) {

		if (valor.matches(
				"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")) {

			return true;
		}

		return false;
		// "El campo Email debe contener una dirección de email válida");

	}

	// Validar url
	public Boolean validarUrl(String valor) {

		String regex_url = "((http|https)://)?(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";
		if (valor.matches(regex_url)) {
			return true;
		}
		return false;

	}

	// Valido el formato de la fecha
	public Boolean validarFecha(String valor) {

		String datePattern = "\\d{4}-\\d{2}-\\d{2}";
		// String datePattern_conBarra = "\\d{4}/\\d{2}/\\d{2}";
		if (!valor.matches(datePattern)) {
			return false;
		}
		// e("Formato de fecha inválido. Utilice el formato dd/mm/yyyy");

		String[] dateParts = valor.split("-");
		int day = Integer.parseInt(dateParts[2]);
		int month = Integer.parseInt(dateParts[1]);
		int year = Integer.parseInt(dateParts[0]);

		if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900 || year > 9999) {

			return false;
		}

		return true;

	}

	// Valido que la fecha no sea mayor a hoy
	public Boolean validarFechaNacimiento(String valor) {

		return false;
	}

	// Valido que el valor sea un número
	public Boolean validarNumeros(String valor) {
		String numberPattern = "\\d+";

		if (valor.matches(numberPattern)) {

			return true;
		}
		return false;

		// "El teléfono sólo puede contener números");

	}

	// Valido que la clave sea fuerte.
	public Boolean validarClaveFuerte(String valor) {

		if (valor.matches("^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{6,20}$")) {

			return true;
		}
		return false;

		/*
		 * "La contraseña debe contener obligatoriamente para que sea fuerte: " +
		 * "entre 8 y 20 caracteres, " + "al menos un dígito, " +
		 * "al menos una minúscula, " + "al menos una mayúscula y " +
		 * "al menos un caracter no alfanumérico (_-.*).");
		 */

	}

}
/*Función para generar estructura básica de los test*/
function crearTest(arrayDatosAccordion){

	var aUno = '';

	if (arrayDatosAccordion[2] === null){
		aUno = '<a class="collapsed card-link" data-toggle="collapse" href="#' + arrayDatosAccordion[1] + '">' +
				' ' + arrayDatosAccordion[3] + ' ' +
			'</a>';
	} else {
		aUno = '<a class="collapsed card-link" data-toggle="collapse" href="#' + arrayDatosAccordion[1] + '"  onclick="' + arrayDatosAccordion[2] + '">' +
				' ' + arrayDatosAccordion[3] + ' ' +
			'</a>';
	}

	var cardHeaderUno = '<div class="card-header">' +
							aUno +
							'<img class="iconTab" id="' + arrayDatosAccordion[4] + ' src="images/failed.png" hidden>' +
						'</div>';

	var cardsUno = '';

	if (arrayDatosAccordion[2] === null){
		var arrayUno = arrayDatosAccordion[7];
		cardsUno = creaCards(arrayUno);
	} else {
		cardsUno = creaTableResponsive(arrayDatosAccordion[5], arrayDatosAccordion[6]);
	}

	var aDos = '';

	if (arrayDatosAccordion[9] === null){
		aDos = '<a class="collapsed card-link" data-toggle="collapse" href="#' + arrayDatosAccordion[8] + '">' +
				' ' + arrayDatosAccordion[10] + ' ' +
			'</a>';
	} else {
		aDos = '<a class="collapsed card-link" data-toggle="collapse" href="#' + arrayDatosAccordion[8] + '"  onclick="' + arrayDatosAccordion[9] + '">' +
				' ' + arrayDatosAccordion[10] + ' ' +
			'</a>';
	}

	var cardHeaderDos = '<div class="card-header">' +
							aDos +
							'<img class="iconTab" id="' + arrayDatosAccordion[11] + ' src="images/failed.png" hidden>' +
						'</div>';

	var cardsDos = '';

	if (arrayDatosAccordion[9] === null){
		var arrayDos = arrayDatosAccordion[14];
		cardsDos = creaCards(arrayDos);
	} else {
		cardsDos = creaTableResponsive(arrayDatosAccordion[12], arrayDatosAccordion[13]);
	}

	var contenidoTest = '<div id="' + arrayDatosAccordion[0] + '">' +
	      					'<div class="card">' +
							    cardHeaderUno +

							    '<div id="' + arrayDatosAccordion[1] + '" class="collapse" data-parent="#' + arrayDatosAccordion[0] + '">' +
							    	'<div class="card-body">' +
										cardsUno +
							    	'</div>' +
								'</div>' +

					  			cardHeaderDos +

					    		'<div id="' + arrayDatosAccordion[8] + '" class="collapse" data-parent="#' + arrayDatosAccordion[0] + '">' +
							    	'<div class="card-body">' +
								      		cardsDos +
							    	'</div>' +
								'</div>' +
					 		' </div>' +
						'</div>'; 

	return contenidoTest;
}

/*Función para crear los cards*/
function creaCards(arrayDatos){
	
	var cards = '';

	for (let step = 1; step < arrayDatos.length ; step++) {

		var array = arrayDatos[step];

	  	cards = cards + '<div class="card">' +
							'<div class="card-header">' +
							    '<a class="collapsed card-link" data-toggle="collapse" href="#' + array[0] + '" onclick="' + array[1] + '">' +
							        ' ' +array[2] + ' ' +
							    '</a>' +
							    '<img class="iconTab" id="' + array[3] + '" src="images/failed.png" hidden>' +
							'</div>' +

							'<div id="' + array[0] + '" class="collapse" data-parent="#' + arrayDatos[0] + '">' +
							    '<div class="card-body">' +
							        '<div class="table-responsive">' +
										'<table class="table table-bordered">' +
										    '<thead class="cabeceraTablasTest" id="' + array[4] + '"></thead>' +
										    '<tbody id="' + array[5] + '"></tbody>' +
										'</table>' +
			      					'</div>' +
							    '</div>' +
							'</div>' +
						'</div>';
	}

	var resultCards = 	'<div id="' + arrayDatos[0] + '">' +
	 						cards +
						'</div>';

	return resultCards;
}


/*Función que crea la tabla responsive si no tenemos subniveles*/
function creaTableResponsive(idCabecera, idCuerpo){
	var table = '<div class="table-responsive">' +
					'<table class="table table-bordered">' +
					    '<thead class="cabeceraTablasTest" id="' + idCabecera + '"></thead>' +
					    '<tbody id="' + idCuerpo + '"></tbody>' +
					'</table>' +
				'</div>';
	return table;
}


/*Función para cargar las opciones de Tests de Login*/
function cargarTestLogin(){

	$("#testLogin").html("");

	let arraySubAccordionUno = ["collapseLoginAtributosLogin", "javascript:testAtributosLogin()", "Login", "iconoTestLoginAtributosLogin", "cabeceraAtributosLogin", "cuerpoAtributosLogin"];
	let arraySubAccordionDos = ["collapseLoginAtributosRecuperarPass", "javascript:testAtributosRecuperarPass()", "Recuperar Contraseña", "iconoTestLoginAtributosRecuperarPass",
							   "cabeceraAtributosRecuperarPass", "cuerpoAtributosRecuperarPass"];
	let arrayAccordionUno = ["accordion3", arraySubAccordionUno, arraySubAccordionDos];

	let arraySubAccordionTres = ["collapseLoginAccionesLogin", "javascript:testAccionesLogin()", "Login", "iconoTestLoginAccionesLogin", "cabeceraAccionesLogin", "cuerpoAccionesLogin"];
	let arraySubAccordionCuatro = ["collapseLoginAccionesRecuperarPass", "javascript:testAccionesRecuperarPass()", "Recuperar Contraseña", "iconoTestLoginAccionesRecuperarPass", 
							      "cabeceraAccionesRecuperarPass", "cuerpoAccionesRecuperarPass"];
	let arrayAccordionDos = ["accordion4", arraySubAccordionTres, arraySubAccordionCuatro];

	let arrayAccordionTres = ["accordion2", "collapseLoginAtributos", null, "Atributos", "iconoTestLoginAtributos", null, null, arrayAccordionUno, "collapseLoginAcciones", null, "Acciones", 
							 "iconoTestLoginAcciones", null, null, arrayAccordionDos];

	var contenidoTest = crearTest(arrayAccordionTres);		

	$("#testLogin").append(contenidoTest);
}

/*Función para cargar las opciones de Tests de Registrar*/
function cargarTestRegistrar(){

	$("#testRegistrar").html("");

	let arrayAccordion = ["accordion5", "collapseRegistrarAtributos", "javascript:testAtributosRegistrar()", "Atributos", "iconoTestRegistrarAtributos", "cabeceraAtributosRegistrar", 
							  "cuerpoAtributosRegistrar", null, "collapseRegistrarAcciones", "javascript:testAccionesRegistrar()", "Acciones", "iconoTestRegistrarAcciones", 
							  "cabeceraAccionesRegistrar", "cuerpoAccionesRegistrar", null];

	var contenidoTest = crearTest(arrayAccordion);	

	$("#testRegistrar").append(contenidoTest);
}

/*Función para cargar las opciones de Tests de Roles*/
function cargarTestGestionRoles(){

	$("#testRol").html("");

	let arraySubAccordionUno = ["collapseRolAtributosGuardar", "javascript:testAtributosRolGuardar()", "Añadir Rol", "iconoTestRolAtributosGuardar", "cabeceraAtributosRolGuardar", "cuerpoAtributosRolGuardar"];
	let arraySubAccordionDos = ["collapseRolAtributoBuscar", "javascript:testAtributosRolBuscar()", "Buscar Rol", "iconoTestRolAtributosBuscar", "cabeceraAtributosRolBuscar", "cuerpoAtributosRolBuscar"];
	let arraySubAccordionTres = ["collapseRolAtributoModificar", "javascript:testAtributosRolModificar()", "Modificar Rol", "iconoTestRolAtributosModificar", "cabeceraAtributosRolModificar", "cuerpoAtributosRolModificar"];
	let arrayAccordionUno = ["accordion7", arraySubAccordionUno, arraySubAccordionDos, arraySubAccordionTres];

	let arraySubAccordionCuatro = ["collapseRolAccionesGuardar", "javascript:testAccionesRolGuardar()", "Añadir Rol", "iconoTestRolAccionesGuardar", "cabeceraAccionesRolGuardar", "cuerpoAccionesRolGuardar"];
	let arraySubAccordionCinco = ["collapseRolAccionesEliminar", "javascript:testAccionesRolEliminar()", "Eliminar Rol", "iconoTestRolAccionesEliminar", "cabeceraAccionesRolEliminar", "cuerpoAccionesRolEliminar"];
	let arraySubAccordionSeis = ["collapseRolAccionesModificar", "javascript:testAccionesRolModificar()", "Modificar Rol", "iconoTestRolAccionesModificar", "cabeceraAccionesRolModificar", "cuerpoAccionesRolModificar"];
	let arraySubAccordionSiete = ["collapseRolAccionesBuscar", "javascript:testAccionesRolBuscar()", "Buscar Rol", "iconoTestRolAccionesBuscar", "cabeceraAccionesRolBuscar", "cuerpoAccionesRolBuscar"];
	let arrayAccordionDos = ["accordion8", arraySubAccordionCuatro, arraySubAccordionCinco, arraySubAccordionSeis, arraySubAccordionSiete];

	let arrayAccordionTres = ["accordion6", "collapseRolAtributos", null, "Atributos", "iconoTestRolAtributos", null, null, arrayAccordionUno, "collapseRolAcciones", null, "Acciones", 
							 "iconoTestRolAcciones", null, null, arrayAccordionDos];

	var contenidoTest = crearTest(arrayAccordionTres);

	$("#testRol").append(contenidoTest);
}


/*Función para cargar las opciones de Tests de Funcionalidades*/
function cargarTestGestionFuncionalidades(){

	$("#testFuncionalidad").html("");

	let arraySubAccordionUno = ["collapseFuncionalidadAtributosGuardar", "javascript:testAtributosFuncionalidadGuardar()", "Añadir Funcionalidad", "iconoTestFuncionalidadAtributosGuardar", 
							   "cabeceraAtributosFuncionalidadGuardar", "cuerpoAtributosFuncionalidadGuardar"];
	let arraySubAccordionDos = ["collapseFuncionalidadAtributoBuscar", "javascript:testAtributosFuncionalidadBuscar()", "Buscar Funcionalidad", "iconoTestFuncionalidadAtributosBuscar", 
							   "cabeceraAtributosFuncionalidadBuscar", "cuerpoAtributosFuncionalidadBuscar"];
	let arraySubAccordionTres = ["collapseFuncionalidadAtributoModificar", "javascript:testAtributosFuncionalidadModificar()", "Modificar Funcionalidad", "iconoTestFuncionalidadAtributosModificar", 
							    "cabeceraAtributosFuncionalidadModificar", "cuerpoAtributosFuncionalidadModificar"];
	let arrayAccordionUno = ["accordion10", arraySubAccordionUno, arraySubAccordionDos, arraySubAccordionTres];

	let arraySubAccordionCuatro = ["collapseFuncionalidadAccionesGuardar", "javascript:testAccionesFuncionalidadGuardar()", "Añadir Funcionalidad", "iconoTestFuncionalidadAccionesGuardar", 
								  "cabeceraAccionesFuncionalidadGuardar", "cuerpoAccionesFuncionalidadGuardar"];
	let arraySubAccordionCinco = ["collapseFuncionalidadAccionesEliminar", "javascript:testAccionesFuncionalidadEliminar()", "Eliminar Funcionalidad", "iconoTestFuncionalidadAccionesEliminar", 
								 "cabeceraAccionesFuncionalidadEliminar", "cuerpoAccionesFuncionalidadEliminar"];
	let arraySubAccordionSeis = ["collapseFuncionalidadAccionesModificar", "javascript:testAccionesFuncionalidadModificar()", "Modificar Funcionalidad", "iconoTestFuncionalidadAccionesModificar", 
								"cabeceraAccionesFuncionalidadModificar", "cuerpoAccionesFuncionalidadModificar"];
	let arraySubAccordionSiete = ["collapseFuncionalidadAccionesBuscar", "javascript:testAccionesFuncionalidadBuscar()", "Buscar Funcionalidad", "iconoTestFuncionalidadAccionesBuscar", 
								 "cabeceraAccionesFuncionalidadBuscar", "cuerpoAccionesFuncionalidadBuscar"];
	let arraySubAccordionOcho = ["collapseFuncionalidadAccionesReactivar", "javascript:testAccionesFuncionalidadReactivar()", "Reactivar Funcionalidad", "iconoTestFuncionalidadAccionesReactivar", 
							    "cabeceraAccionesFuncionalidadReactivar", "cuerpoAccionesFuncionalidadReactivar"];
	let arrayAccordionDos = ["accordion11", arraySubAccordionCuatro, arraySubAccordionCinco, arraySubAccordionSeis, arraySubAccordionSiete, arraySubAccordionOcho];

	let arrayAccordionTres = ["accordion9", "collapseFuncionalidadAtributos", null, "Atributos", "iconoTestFuncionalidadAtributos", null, null, arrayAccordionUno, "collapseFuncionalidadAcciones", null, "Acciones", 
							 "iconoTestFuncionalidadAcciones", null, null, arrayAccordionDos];

	var contenidoTest = crearTest(arrayAccordionTres);

	$("#testFuncionalidad").append(contenidoTest);
}

/*Función para cargar las opciones de Tests de Acciones*/
function cargarTestGestionAcciones(){

	$("#testAccion").html("");

	let arraySubAccordionUno = ["collapseAccionAtributosGuardar", "javascript:testAtributosAccionGuardar()", "Añadir Acción", "iconoTestAccionAtributosGuardar", 
							   "cabeceraAtributosAccionGuardar", "cuerpoAtributosAccionGuardar"];
	let arraySubAccordionDos = ["collapseAccionAtributoBuscar", "javascript:testAtributosAccionBuscar()", "Buscar Acción", "iconoTestAccionAtributosBuscar", 
							   "cabeceraAtributosAccionBuscar", "cuerpoAtributosAccionBuscar"];
	let arraySubAccordionTres = ["collapseAccionAtributoModificar", "javascript:testAtributosAccionModificar()", "Modificar Acción", "iconoTestAccionAtributosModificar", 
							    "cabeceraAtributosAccionModificar", "cuerpoAtributosAccionModificar"];
	let arrayAccordionUno = ["accordion13", arraySubAccordionUno, arraySubAccordionDos, arraySubAccordionTres];

	let arraySubAccordionCuatro = ["collapseAccionAccionesGuardar", "javascript:testAccionesAccionGuardar()", "Añadir Acción", "iconoTestAccionAccionesGuardar", 
								  "cabeceraAccionesAccionGuardar", "cuerpoAccionesAccionGuardar"];
	let arraySubAccordionCinco = ["collapseAccionAccionesModificar", "javascript:testAccionesAccionModificar()", "Modificar Acción", "iconoTestAccionAccionesModificar", 
								 "cabeceraAccionesAccionModificar", "cuerpoAccionesAccionModificar"];
	let arraySubAccordionSeis = ["collapseAccionAccionesEliminar", "javascript:testAccionesAccionEliminar()", "Eliminar Acción", "iconoTestAccionAccionesEliminar", 
								"cabeceraAccionesAccionEliminar", "cuerpoAccionesAccionEliminar"];
	let arraySubAccordionSiete = ["collapseAccionAccionesBuscar", "javascript:testAccionesAccionBuscar()", "Buscar Acción", "iconoTestAccionAccionesBuscar", 
								 "cabeceraAccionesAccionBuscar", "cuerpoAccionesAccionBuscar"];
	let arraySubAccordionOcho = ["collapseAccionAccionesReactivar", "javascript:testAccionesAccionReactivar()", "Reactivar Acción", "iconoTestAccionAccionesReactivar", 
							    "cabeceraAccionesAccionReactivar", "cuerpoAccionesAccionReactivar"];
	let arraySubAccordionNueve = ["collapseAccionAccionesAsignar", "javascript:testAccionesAccionAsignar()", "Asignar Acción", "iconoTestAccionAccionesAsignar", 
							    "cabeceraAccionesAccionAsignar", "cuerpoAccionesAccionAsignar"];
	let arraySubAccordionDiez = ["collapseAccionAccionesRevocar", "javascript:testAccionesAccionRevocar()", "Revocar Acción", "iconoTestAccionAccionesRevocar", 
							    "cabeceraAccionesAccionRevocar", "cuerpoAccionesAccionRevocar"];
	let arrayAccordionDos = ["accordion14", arraySubAccordionCuatro, arraySubAccordionCinco, arraySubAccordionSeis, arraySubAccordionSiete, arraySubAccordionOcho, arraySubAccordionNueve, arraySubAccordionDiez];

	let arrayAccordionTres = ["accordion12", "collapseAccionAtributos", null, "Atributos", "iconoTestAccionAtributos", null, null, arrayAccordionUno, "collapseAccionAcciones", null, "Acciones", 
							 "iconoTestAccionAcciones", null, null, arrayAccordionDos];

	var contenidoTest = crearTest(arrayAccordionTres);

	$("#testAccion").append(contenidoTest);
}
/*Función para cargar las opciones de Tests de Usuario*/
function cargarTestGestionUsuarios(){

	$("#testUsuario").html("");

	let arraySubAccordionUno = ["collapseUsuarioAtributosModificarRolUsuario", "javascript:testAtributosUsuarioModificarRolUsuario()", "Modificar Rol Usuario", "iconoTestUsuarioAtributosModificarRolUsuario", 
							   "cabeceraAtributosUsuarioModificarRolUsuario", "cuerpoAtributosUsuarioModificarRolUsuario"];
	let arraySubAccordionDos = ["collapseUsuarioAtributoBuscar", "javascript:testAtributosUsuarioBuscar()", "Buscar Usuario", "iconoTestUsuarioAtributosBuscar", 
							   "cabeceraAtributosUsuarioBuscar", "cuerpoAtributosUsuarioBuscar"];
	let arrayAccordionUno = ["accordion16", arraySubAccordionUno, arraySubAccordionDos];

	let arraySubAccordionTres = ["collapseUsuarioAccionesBuscar", "javascript:testAccionesUsuarioBuscar()", "Buscar Usuario", "iconoTestUsuarioAccionesBuscar", 
								  "cabeceraAccionesUsuarioBuscar", "cuerpoAccionesUsuarioBuscar"];
	let arraySubAccordionCuatro = ["collapseUsuarioAccionesEliminar", "javascript:testAccionesUsuarioEliminar()", "Eliminar Usuario", "iconoTestUsuarioAccionesEliminar", 
								 "cabeceraAccionesUsuarioEliminar", "cuerpoAccionesUsuarioEliminar"];
	let arraySubAccordionCinco = ["collapseUsuarioAccionesModificarRolUsuario", "javascript:testAccionesUsuarioModificarRolUsuario()", "Modificar Rol Usuario", "iconoTestUsuarioAccionesModificarRolUsuario", 
								"cabeceraAccionesUsuarioModificarRolUsuario", "cuerpoAccionesUsuarioModificarRolUsuario"];
	let arraySubAccordionSeis = ["collapseUsuarioAccionesModificarPasswdUsuario", "javascript:testAccionesUsuarioModificarPasswdUsuario()", "Modificar Contraseña Usuario", "iconoTestUsuarioAccionesModificarPasswdUsuario", 
								 "cabeceraAccionesUsuarioModificarPasswdUsuario", "cuerpoAccionesUsuarioModificarPasswdUsuario"];
	let arraySubAccordionSiete = ["collapseUsuarioAccionesReactivar", "javascript:testAccionesUsuarioReactivar()", "Reactivar Usuario", "iconoTestUsuarioAccionesReactivar", 
							    "cabeceraAccionesUsuarioReactivar", "cuerpoAccionesUsuarioReactivar"];
	let arrayAccordionDos = ["accordion17", arraySubAccordionTres, arraySubAccordionCuatro, arraySubAccordionCinco, arraySubAccordionSeis, arraySubAccordionSiete];

	let arrayAccordionTres = ["accordion15", "collapseUsuarioAtributos", null, "Atributos", "iconoTestUsuarioAtributos", null, null, arrayAccordionUno, "collapseUsuarioAcciones", null, "Acciones", 
							 "iconoTestUsuarioAcciones", null, null, arrayAccordionDos];

	var contenidoTest = crearTest(arrayAccordionTres);

	$("#testUsuario").append(contenidoTest);
}

/*Función para cargar las opciones de Tests de Noticias*/
function cargarTestGestionNoticias(){

	$("#testNoticia").html("");

	let arraySubAccordionUno = ["collapseNoticiaAtributosGuardar", "javascript:testAtributosNoticiaGuardar()", "Añadir Noticia", "iconoTestNoticiaAtributosGuardar", 
							   "cabeceraAtributosNoticiaGuardar", "cuerpoAtributosNoticiaGuardar"];
	let arraySubAccordionDos = ["collapseNoticiaAtributoBuscar", "javascript:testAtributosNoticiaBuscar()", "Buscar Noticia", "iconoTestNoticiaAtributosBuscar", 
							   "cabeceraAtributosNoticiaBuscar", "cuerpoAtributosNoticiaBuscar"];
	let arraySubAccordionTres = ["collapseNoticiaAtributoModificar", "javascript:testAtributosNoticiaModificar()", "Modificar Noticia", "iconoTestNoticiaAtributosModificar", 
							    "cabeceraAtributosNoticiaModificar", "cuerpoAtributosNoticiaModificar"];
	let arrayAccordionUno = ["accordion19", arraySubAccordionUno, arraySubAccordionDos, arraySubAccordionTres];

	let arraySubAccordionCuatro = ["collapseNoticiaAccionesGuardar", "javascript:testAccionesNoticiaGuardar()", "Añadir Noticia", "iconoTestNoticiaAccionesGuardar", 
								  "cabeceraAccionesNoticiaGuardar", "cuerpoAccionesNoticiaGuardar"];
	let arraySubAccordionCinco = ["collapseNoticiaAccionesEliminar", "javascript:testAccionesNoticiaEliminar()", "Eliminar Noticia", "iconoTestNoticiaAccionesEliminar", 
								 "cabeceraAccionesNoticiaEliminar", "cuerpoAccionesNoticiaEliminar"];
	let arraySubAccordionSeis = ["collapseNoticiaAccionesModificar", "javascript:testAccionesNoticiaModificar()", "Modificar Noticia", "iconoTestNoticiaAccionesModificar", 
								"cabeceraAccionesNoticiaModificar", "cuerpoAccionesNoticiaModificar"];
	let arrayAccordionDos = ["accordion20", arraySubAccordionCuatro, arraySubAccordionCinco, arraySubAccordionSeis];

	let arrayAccordionTres = ["accordion18", "collapseNoticiaAtributos", null, "Atributos", "iconoTestNoticiaAtributos", null, null, arrayAccordionUno, "collapseNoticiaAcciones", null, "Acciones", 
							 "iconoTestNoticiaAcciones", null, null, arrayAccordionDos];

	var contenidoTest = crearTest(arrayAccordionTres);

	$("#testNoticia").append(contenidoTest);
} 

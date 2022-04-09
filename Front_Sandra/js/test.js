/*Función para añadir la cabecera de la tabla de atributos*/
function cabeceraTablaAtributosTest(){

	return '<tr>' +
       		'<th>Campo</th>' +
       		'<th>Prueba</th>' +
       		'<th>Valor</th>' +
       		'<th>Resultado Esperado</th>' +
       		'<th>Resultado Obtenido</th>' +
       		'<th>Tipo Prueba</th>' +
       		'</tr>';
}

/*Función para añadir la cabecera de la tabla de acciones*/
function cabeceraTablaAccionesTest(){

	return '<tr>' +
       		'<th>Prueba</th>' +
       		'<th>Valor</th>' +
       		'<th>Resultado Esperado</th>' +
       		'<th>Resultado Obtenido</th>' +
       		'<th>Tipo Prueba</th>' +
       		'</tr>';
}

/*Función que rellena las tablas de atributos*/
function cuerpoTablaAtributosTest(fila){

	var filaTabla = "";

	filaTabla = '<tr> <td>' + fila.campo + 
            	'</td> <td>' + fila.prueba +
            	'</td> <td>' + valorAcortado(fila.valor) +
            	'</td> <td>' + fila.resultadoEsperado +
            	'</td> <td>' + fila.resultadoObtenido +
            	'</td> <td>' + fila.tipoPrueba +
            	'</td> </tr>';

    return filaTabla;
}

/*Función que devuelve el valor de la prueba acortado si pasa de un tamaño*/
function valorAcortado(valor){

	if (valor.length > 45){
		return valor.substr(0, 45) + "...";
	}

	return valor;
}

/*Función que rellena las tablas de atributos*/
function cuerpoTablaAccionesTest(fila, atributos, entidad){

	var filaTabla = "";

	filaTabla = '<tr> <td>' + fila.prueba +
            	'</td> <td>' + obtenerValor(fila.valor, atributos, entidad) +
            	'</td> <td>' + fila.resultadoEsperado +
            	'</td> <td>' + fila.resultadoObtenido +
            	'</td> <td>' + fila.tipoPrueba +
            	'</td> </tr>';

    return filaTabla;
}

/*Función para devolver el contenido de la columna valor*/
function obtenerValor(valor, atributos, entidad){

	var resultadoValor = "";

	if (valor != null){

		switch(entidad){
			case 'Login':
				resultadoValor = '"' + atributos[0] + '" : "' + valor.usuario + '", "' + atributos[1] + '" : "' + valor.passwdUsuario +  '"';
			break;
			case 'RecuperarPass':
				resultadoValor = '"' + atributos[0] + '" : "' + valor.emailP + '", "' + atributos[1] + '" : "' + valor.usuario +  '"';
			break;
			case 'Registrar':
				resultadoValor = '"' + atributos[0] + '" : "' + valor.nombreP + '", "' + atributos[1] + '" : "' + valor.apellidosP + '", "' + atributos[2] + '" : "' + valor.fechaNacP  
							+ '", "' + atributos[3] + '" : "' + valor.telefonoEmpresa + '", "' + atributos[4] + '" : "' + valor.nombreEmpresa + '", "' + atributos[5] + '" : "' + valor.direccionP 
							+ '", "' + atributos[6] + '" : "' + valor.emailP + '", "' + atributos[7] + '" : "' + valor.cifEmpresa + '", "' + atributos[8] + '" : "' + valor.direccionEmpresa
							+ '", "' + atributos[9] + '" : "' + valor.seleccionarEmpresa + '", "' + atributos[10] + '" : "' + valor.telefonoP + '", "' + atributos[11] + '" : "' + valor.usuario
							+ '", "' + atributos[12] + '" : "' + valor.dniP + '", "' + atributos[13] + '" : "' + valor.passwdUsuario
							+ '"';
			break;
			case 'Rol':
				resultadoValor = '"' + atributos[0] + '" : "' + valor.rolDescription + '", "' + atributos[1] + '" : "' + valor.rolName +  '"';
			break;

			case 'Funcionalidad':
				resultadoValor = '"' + atributos[0] + '" : "' + valor.descripFuncionalidad + '", "' + atributos[1] + '" : "' + valor.nombreFuncionalidad +  '"';
			break;
		}

	} else {
		resultadoValor = null;
	}

	return resultadoValor;
}

/*Función para cargar las tablas de test*/
function cargarTablasTest(datos, idCabecera, idCuerpo, tipoTest, atributosValor, entidad){

	$("#" + idCabecera).html("");
	$("#" + idCuerpo).html("");
	var trCabecera = "";

	switch(tipoTest){
		case 'acciones':
			trCabecera = cabeceraTablaAccionesTest();

			for (var i = 0; i < datos.length; i++){
				var tr = cuerpoTablaAccionesTest(datos[i], atributosValor, entidad);
				$("#" + idCuerpo).append(tr);
			}    
		break;
		case 'atributos':
			trCabecera = cabeceraTablaAtributosTest();

			for (var i = 0; i < datos.length; i++){
				var tr = cuerpoTablaAtributosTest(datos[i]);
				$("#" + idCuerpo).append(tr);
			} 
		break;
	}

	$("#" + idCabecera).append(trCabecera);
	  			
}

/*Función para cargar los errores en la modal si falla la petición de los test*/
function cargarModalErroresTest(code){
	$("#modal-title").removeClass();
    $("#modal-title").addClass("ERROR");
    document.getElementById("modal-title").style.color = "#a50707";
    $(".imagenAviso").attr('src', 'images/failed.png');
    $("#modal-mensaje").removeClass();
    $("#modal-mensaje").addClass(code);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
}

/**Función que valida que no tengamos pruebas de test con valores nulos, en el caso de que los haya muestra un icono de error al lado del tipo de test*/
function validarDatosTabla(datos, idElementoList, tipoTest){

	for (var i = 0; i < datos.length; i++){

		switch(tipoTest){
			case 'acciones':
				if (datos[i].prueba === null || datos[i].valor === null || datos[i].resultadoEsperado === null || 
					datos[i].resultadoObtenido === null || datos[i].tipoPrueba === null ) {
					idElementoList.forEach( function (idElemento) {
						$("#"+ idElemento).prop('hidden', false);
					});	
					break;
				} else {
					idElementoList.forEach( function (idElemento) {
						$("#"+ idElemento).prop('hidden', true);
					});
					break;
				}
			break;
			case 'atributos':	
				if (datos[i].campo === null || datos[i].prueba === null || datos[i].valor === null || 
					datos[i].resultadoEsperado === null || datos[i].resultadoObtenido === null || datos[i].tipoPrueba === null ) {
					idElementoList.forEach( function (idElemento) {
						$("#"+ idElemento).prop('hidden', false);
					});	
					break;
				} else {
					idElementoList.forEach( function (idElemento) {
						$("#"+ idElemento).prop('hidden', true);
					});
					break;
				}
			break;
		}
	}

	
}
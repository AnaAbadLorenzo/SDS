/**Función para recuperar los test de atributo de login con ajax y promesas*/
function testLoginAtributos(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestLoginAtributos,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_LOGIN_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}

/**Función para recuperar los test de atributo de recuperar pass con ajax y promesas*/
function testRecuperarPassAtributos(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestRecuperarPassAtributos,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ATRIBUTOS_RECUPERARPASS_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}

/**Función para recuperar los test de acciones de login con ajax y promesas*/
function testLoginAcciones(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxTestLoginAcciones,
      contentType : "application/json",
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'TEST_ACCIONES_LOGIN_OK') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}

/*Función que obtiene los test de atributos de Login */
async function testAtributosLogin(){
	await testLoginAtributos()
	.then((res) => {
		$("#cabeceraAtributosLogin").html("");
		$("#cuerpoAtributosLogin").html("");
	  	var trCabecera = cabeceraTablaAtributosTest();
	  	$("#cabeceraAtributosLogin").append(trCabecera);
	  	for (var i = 0; i < res.data.datosPruebaAtributos.length; i++){
			var tr = cuerpoTablaAtributosTest(res.data.datosPruebaAtributos[i]);
			$("#cuerpoAtributosLogin").append(tr);
		}    	

	  }).catch((res) => {
	    $("#modal-title").removeClass();
	    $("#modal-title").addClass("ERROR");
	    document.getElementById("modal-title").style.color = "#a50707";
	    $(".imagenAviso").attr('src', 'images/failed.png');
	    $("#mensajeError").removeClass();
	    $("#mensajeError").addClass(res.code);
	    setLang(getCookie('lang'));
	    document.getElementById("modal").style.display = "block";
	});
}

/*Función que obtiene los test de atributos de Recuperar Pass */
async function testAtributosRecuperarPass(){
	await testRecuperarPassAtributos()
	.then((res) => {
		$("#cabeceraAtributosRecuperarPass").html("");
		$("#cuerpoAtributosRecuperarPass").html("");
	  	var trCabecera = cabeceraTablaAtributosTest();
	  	$("#cabeceraAtributosRecuperarPass").append(trCabecera);
	  	for (var i = 0; i < res.data.datosPruebaAtributos.length; i++){
			var tr = cuerpoTablaAtributosTest(res.data.datosPruebaAtributos[i]);
			$("#cuerpoAtributosRecuperarPass").append(tr);
		}    	

	  }).catch((res) => {
	    $("#modal-title").removeClass();
	    $("#modal-title").addClass("ERROR");
	    document.getElementById("modal-title").style.color = "#a50707";
	    $(".imagenAviso").attr('src', 'images/failed.png');
	    $("#mensajeError").removeClass();
	    $("#mensajeError").addClass(res.code);
	    setLang(getCookie('lang'));
	    document.getElementById("modal").style.display = "block";
	});
}

/*Función que obtiene los test de acciones de Login */
async function testAccionesLogin(){
	await testLoginAcciones()
	.then((res) => {
		$("#cabeceraAccionesLogin").html("");
		$("#cuerpoAccionesLogin").html("");
	  	var trCabecera = cabeceraTablaAccionesTest();
	  	$("#cabeceraAccionesLogin").append(trCabecera);
	  	let atributosValor = ["usuario", "passwdUsuario"];
	  	for (var i = 0; i < res.data.datosPruebaAcciones.length; i++){
			var tr = cuerpoTablaAccionesTest(res.data.datosPruebaAcciones[i], atributosValor, "Login");
			$("#cuerpoAccionesLogin").append(tr);
		}    	

	  }).catch((res) => {
	    $("#modal-title").removeClass();
	    $("#modal-title").addClass("ERROR");
	    document.getElementById("modal-title").style.color = "#a50707";
	    $(".imagenAviso").attr('src', 'images/failed.png');
	    $("#mensajeError").removeClass();
	    $("#mensajeError").addClass(res.code);
	    setLang(getCookie('lang'));
	    document.getElementById("modal").style.display = "block";
	});
}


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

	switch(entidad){
		case 'Login':
			resultadoValor = '"' + atributos[0] + '" : "' + valor.usuario +  '", "' + atributos[1] + '" : "' + valor.passwdUsuario +  '"';
		break;
	}


	return resultadoValor;
}
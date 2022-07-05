/**Función para añadir más objetivos y niveles*/
function addObjetivosNiveles(){
	var options = "";
	var objetivosNiveles = "";

	var listaBusquedasObjetivos = $.parseJSON(getCookie('objetivosSelect'));
	var idObjetivoNivel = parseInt(getCookie('numeroObjNivel')) + 1;
	objetivosNiveles = '<div id="objetivosNiveles' + idObjetivoNivel + '" class="objetivosNiveles">' +
			                '<label class="labelForm NOMBRE_OBJETIVO" id="labelNombreObjetivo" hidden></label>' +
	            	  		'<select id="selectObjetivos'+ idObjetivoNivel + '" name="objetivos" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">' + 
	            	  			'<option selected value=0><label class="OPCION_DEFECTO_OBJETIVO"></label></option>';        
					for(var i = 0; i<listaBusquedasObjetivos.length; i++){
						options += '<option value=' + (listaBusquedasObjetivos[i])[0] + '>' + (listaBusquedasObjetivos[i])[1]  + '</option>';
					}
	objetivosNiveles += options;        

	var objetivosNiveles2 =     '</select>' +
						  		'<div class="obligatorio tooltip2" id="obligatorioObjetivos">*' +
	          						'<span class="tooltiptext2 campoObligatorio CAMPO_OBLIGATORIO">Campo obligatorio</span>' +
	      			  			'</div>' +
		          			    '<div style="display:none" id="errorFormatoNombreObjetivoSelect"></div>' +  
							  	'<label class="labelForm NIVEL" id="labelNombrePlan" hidden></label>'+                           
	                  			'<input type="text" maxlength="48" size="48" placeholder="NIVEL" name="nivel" id="nivel" class="NIVEL" onblur=""/>' +
	                   			'<div class="obligatorio tooltip2" id="obligatorioNivel">*' +
	          						'<span class="tooltiptext2 campoObligatorio CAMPO_OBLIGATORIO">Campo obligatorio</span>' +
	          			  		'</div>' +
	                      		'<div style="display:none" id="errorFormatoNivel"></div>' +
	                      		'<div name="btnBorrar" value="ELiminar" onclick="javascript:eliminarObjetivosNiveles(\'objetivosNiveles2\')" class="tooltip6 borrarIcon">' +
	      			  				'<img class="iconoBorrar iconBorrar" src="images/delete3.png" alt="Eliminar" />' +
	        						'<span class="tooltiptext iconBorrar ICONO_ELIMINAR">Eliminar</span>' +
	      			  			'</div>' +
	      			  		'</div>';

	objetivosNiveles += objetivosNiveles2;

	$("#objetivosNiveles").append(objetivosNiveles);
}

/**Función que eliminar el contenido de un div de objetivos y niveles*/
function eliminarObjetivosNiveles(idObjetivosNiveles){
	$("#" + idObjetivosNiveles).remove();
}

/**Función que envía el fichero a back IMPORTANTE ES UNA FUNCIÓN DE PRUEBA QUE HABRÁ QUE CAMBIAR
 * PARA QUE QUEDE COMO LAS DEMÁS COMO UNA PROMESA, ESTO ES SÓLO PARA EL ENVIO DEL FICHERO CON 
 * PETICIÓN AJAX*/
function enviarRespuesta(idFile){
    var token = getCookie('tokenUsuario');
    var evidencia = $("#" + idFile).prop("files")[0];

    var formData = new FormData();
    formData.append("file", evidencia);
    formData.append("idProceso", 1);
    formData.append("idProcedimientoUsuario", 1);

    $.ajax({
      	method: "POST",
      	url: "http://localhost:8090/SDS/evidencia/guardaEvidencia",
      	contentType : "application/json",
      	data: formData,  
      	cache: false,
    	contentType: false,
    	processData: false,
      	headers: {'Authorization': token},
      	}).done(res => {
        	if (res.code != 'PLAN_GUARDADO') {
          	reject(res);
        	}
        	resolve(res);
      	}).fail( function( jqXHR ) {
        	errorFailAjax(jqXHR.status);
      	});
}

/**Función para cargar las respuestas posibles de BD **/
function cargarRespuestasPosiblesAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxListadoRespuestasPosiblesSinP,
      contentType : "application/json", 
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'RESPUESTAS_POSIBLES_LISTADAS') {
          reject(res);
        }
        resolve(res);
    }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para cargar los procedimientos de BD **/
function cargarProcedimientosAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxListadoProcedimientosSinP,
      contentType : "application/json", 
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PROCEDIMIENTOS_LISTADOS') {
          reject(res);
        }
        resolve(res);
    }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para cargar los objetivos de BD **/
function cargarObjetivosAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxListadoObjetivosSinP,
      contentType : "application/json", 
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'OBJETIVOS_LISTADOS') {
          reject(res);
        }
        resolve(res);
    }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}


/** Función para añadir procesos con ajax y promesas **/
function anadirProcesoAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var idsObjetivos = [];
    var idsProcedimientos = [];
    var procedimientos = [];
    var objetivos = [];
    var niveles = [];
    var idsRespuestas = [];
    var respuestasPosibles = [];

    var proceso = {
      idProceso : "",
      nombreProceso : $('#nombreProceso').val(),
      descripProceso : $('#descripcionProceso').val(),
      fechaProceso : $('#fechaProceso').val(),
      borradoProceso : 0
    }

    var selectProcedimiento = $('#selectProcedimientos');

  	$('#selectProcedimientos').children(':selected').each((idx, el) => {
    	idsProcedimientos.push(el.value)
  	});

  	for(var i = 0; i<idsProcedimientos.length; i++){
  		var procedimiento = {
	      idProcedimiento: idsProcedimientos[i],
	      nombreProcedimiento : '',
	      descripProcedimiento : '',
	      fechaProcedimiento : '',
	      checkUsuario : '',
	      plan : {
	        idPlan : '',
	        nombrePlan : '',
	        descripPlan : '',
	        fechaPlan : '',
	        borradoPlan : '',
	        objetivo : {
	          idObjetivo : '',
	          nombreObjetivo : '',
	          descripObjetivo : '',
	          borradoObjetivo : ''
	        }
	      },
	      borradoProcedimiento : ''
    	}

    	procedimientos.push(procedimiento);
  	}
    

    var selects = $('select[name="objetivos"]');
    var checksRespuestas = $('input[name=respuestaPosible]');

    for(var i = 0; i<selects.length; i++){
    	var select = $(selects[i]);
    	idsObjetivos.push($(select).children("option:selected").val());
    }

    $("input[name='nivel']").each(function() {
    	niveles.push($(this).val());
	});

	$("input[name='respuestaPosible']").each(function() {
		if($(this).is(':checked')){
           idsRespuestas.push($(this).val());
        }
	});

    for(var i = 0; i<idsObjetivos.length; i++){
    	var objetivo = {
    		idObjetivo : idsObjetivos[i],
    		nombreObjetivo : '',
    		descripObjetivo : '',
    		borradoObjetivo : '',
    	}
    	objetivos.push(objetivo);
    }

    for(var i = 0; i<idsRespuestas.length; i++){
    	var respuesta = {
    		 idRespuesta: idsRespuestas[i],
     		 textoRespuesta : '',
      		 borradoRespuesta : 0
    	}
    	respuestasPosibles.push(respuesta);
    }


    var data = {
      usuario : getCookie('usuario'),
      proceso : proceso,
      procedimientos : procedimientos,
      objetivos : objetivos,
      niveles : niveles,
      respuestasPosibles: respuestasPosibles
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxAddProceso,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PROCESO_GUARDADO') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Función para buscar procesos con ajax y promesas **/
function buscarProcesoAjaxPromesa(numeroPagina, tamanhoPagina, accion){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    if(accion == "buscarModal"){
      if($('#fechaProceso').val() == "1900-01-01"){
      	var fechaP = '';
      }else{
      	var fechaP = $('#fechaProceso').val();
      }

      var data = {
        nombreProceso : $('#nombreProceso').val(),
        descripProceso : $('#descripcionProceso').val(),
        fechaProceso : fechaP,
        inicio : calculaInicio(numeroPagina, tamanhoPaginaProceso),
        tamanhoPagina : tamanhoPaginaProceso
      }
    }

    if(accion == "buscarPaginacion"){
      if(getCookie('nombreProceso') == null || getCookie('nombreProceso') == ""){
        var nombreP = "";
      }else{
        var nombreP = getCookie('nombreProceso');
      }

      if(getCookie('descripProceso') == null || getCookie('descripProceso') == ""){
        var descripP = "";
      }else{
        var descripP = getCookie('descripProc');
      }

      if(getCookie('fechaProceso') == null || getCookie('fechaProceso') == "null" || getCookie('fechaProceso') == "" ){
        var fechaP= "";
        var fechaString = "";
      }else{
        var fechaP = getCookie('fechaProceso');
        var fechaString = convierteFecha(fechaP);
      }

      var data = {
        nombreProceso : nombreP,
        descripProceso : descripP,
        fechaProceso : fechaString,
        inicio : calculaInicio(numeroPagina, tamanhoPaginaProceso),
        tamanhoPagina : tamanhoPaginaProceso 
      }
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarProceso,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PROCESO_ENCONTRADO') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Función para recuperar los permisos de un usuario sobre el proceso **/
function cargarPermisosFuncProcesoAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var nombreUsuario = getCookie('usuario');
    var token = getCookie('tokenUsuario');
    
    var usuario = nombreUsuario;
  
    $.ajax({
      method: "GET",
      url: urlPeticionAjaxAccionesUsuario,
      contentType : "application/json",
      data: { usuario : usuario, funcionalidad : 'Gestión de procesos'},  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'ACCIONES_USUARIO_OK') {
          reject(res);
        }
        resolve(res);
    }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para editar una funcionalidad con ajax y promesas*/
function editarFuncionalidadAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var funcionalidadEntity = {
      idFuncionalidad : $("input[name=idFuncionalidad]").val(),
      nombreFuncionalidad : $('#nombreFuncionalidad').val(),
      descripFuncionalidad : $('#descripcionFuncionalidad').val(),
      borradoFuncionalidad : 0
    }
    
    var data = {
      usuario : getCookie('usuario'),
      funcionalidadEntity : funcionalidadEntity
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxEditFuncionalidad,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDAD_MODIFICADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para eliminar un rol un rol con ajax y promesas*/
function eliminarFuncionalidadAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var funcionalidadEntity = {
      idFuncionalidad : $("input[name=idFuncionalidad]").val(),
      nombreFuncionalidad : $('#nombreFuncionalidad').val(),
      descripFuncionalidad : $('#descripcionFuncionalidad').val(),
      borradoFuncionalidad : 1
    }
    
    var data = {
      usuario : getCookie('usuario'),
      funcionalidadEntity : funcionalidadEntity
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxDeleteFuncionalidad,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDAD_ELIMINADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/*Función que comprueba los permisos del usuario sobre la funcionalidad*/
async function cargarPermisosFuncProceso(){
  await cargarPermisosFuncProcesoAjaxPromesa()
  .then((res) => {
    gestionarPermisosProceso(res.data);
  }).catch((res) => {
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}

/*Función que carga las respuestas posibes*/
async function cargarRespuestasPosibles(){
  await cargarRespuestasPosiblesAjaxPromesa()
  .then((res) => {
  	$('#respuestasPosibles').html('');

  	for(var i = 0; i<res.data.listaBusquedas.length; i++){
  		var idCheckBox=res.data.listaBusquedas[i].textoRespuesta;
  		var value=res.data.listaBusquedas[i].idRespuesta;
  		var respuestasPosibles = '<div class="form-check form-check-inline">' + 
  									'<input class="form-check-input" name="respuestaPosible" type="checkbox" id="' + idCheckBox + '" value="' + value + '" />' + 
  									'<label class="form-check-label" for="' + idCheckBox + '">' + idCheckBox + '</label>' + 
  								 '</div>';
  		$('#respuestasPosibles').append(respuestasPosibles);
  	}
  	
  }).catch((res) => {
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}

/*Función que carga los procedimientos*/
async function cargarProcedimientos(){
  await cargarProcedimientosAjaxPromesa()
  .then((res) => {
  	var options = "";
  
    $('#selectProcedimientos').html('');

    var token = getCookie('tokenUsuario');
        for(var i = 0; i< res.data.listaBusquedas.length ; i++){
          options += '<option value=' + res.data.listaBusquedas[i].idProcedimiento + '>' + res.data.listaBusquedas[i].nombreProcedimiento + '</option>';
        }

        $('#selectProcedimientos').append(options);
  	
  }).catch((res) => {
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}


/*Función que carga los objetivos*/
async function cargarObjetivos(){
  await cargarObjetivosAjaxPromesa()
  .then((res) => {
  	var options = "";
  	var objetivos = [];
  
    $('#selectObjetivos').html('');

    var token = getCookie('tokenUsuario');

        options = '<option selected value=0><label class="OPCION_DEFECTO_OBJETIVO"></label></option>';
        for(var i = 0; i< res.data.listaBusquedas.length ; i++){
          var elementArray = [res.data.listaBusquedas[i].idObjetivo, res.data.listaBusquedas[i].nombreObjetivo];
          options += '<option value=' + res.data.listaBusquedas[i].idObjetivo + '>' + res.data.listaBusquedas[i].nombreObjetivo + '</option>';
          objetivos.push(elementArray);
        }

        $('#selectObjetivos').append(options);
        setCookie('objetivosSelect', JSON.stringify(objetivos));
  	
  }).catch((res) => {
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}

/** Función para recuperar los procesos con ajax y promesas **/
function cargarProcesosAjaxPromesa(numeroPagina, tamanhoPagina){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');

    var data = {
      inicio : calculaInicio(numeroPagina, tamanhoPaginaProceso),
      tamanhoPagina : tamanhoPaginaProceso
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarTodosProcesos,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PROCESOS_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para recuperar las funcionalidades eliminadas con ajax y promesas*/
function buscarEliminadosAjaxPromesa(numeroPagina, tamanhoPagina){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var data = {
      inicio : calculaInicio(numeroPagina, tamanhoPaginaFuncionalidad),
      tamanhoPagina : tamanhoPaginaFuncionalidad
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoFuncionalidadesEliminadas,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDADES_ELIMINADAS_LISTADAS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/**Función para ver en detalle una funcionalidad con ajax y promesas*/
function detalleFuncionalidadAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    var data = {
      nombreFuncionalidad : $('#nombreFuncionalidad').val(),
      descripFuncionalidad : $('#descripFuncionalidad').val(),
      inicio : 0,
      tamanhoPagina : 1
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarFuncionalidad,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDAD_ENCONTRADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}


/**Función para reactivar una funcionalidad con ajax y promesas*/
function reactivarFuncionalidadesAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');
    
    var funcionalidadEntity = {
      idFuncionalidad : $("input[name=idFuncionalidad]").val(),
      nombreFuncionalidad : $('#nombreFuncionalidad').val(),
      descripFuncionalidad : $('#descripcionFuncionalidad').val(),
      borradoFuncionalidad : 0
    }

    var data = {
      usuario: getCookie('usuario'),
      funcionalidadEntity : funcionalidadEntity
    }

      $.ajax({
      method: "POST",
      url: urlPeticionAjaxReactivarFuncionalidad,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDAD_REACTIVADA') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/* Función para obtener los procesos del sistema */
async function cargarProcesos(numeroPagina, tamanhoPagina, paginadorCreado){
	if(getCookie('rolUsuario') == "admin" || getCookie('rolUsuario') == "gestor"){
		await cargarProcesosAjaxPromesa(numeroPagina, tamanhoPagina)
	  .then((res) => {
	  	
     	 var numResults = res.data.numResultados + '';
	  	var totalResults = res.data.tamanhoTotal + '';
        var inicio = 0;
      if(res.data.listaBusquedas.length == 0){
        inicio = 0;
      }else{
        inicio = parseInt(res.data.inicio)+1;
      }
      var textPaginacion = inicio + " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults;

      if(res.data.listaBusquedas.length == 0){
        $('#itemPaginacion').attr('hidden',true);
      }else{
        $('#itemPaginacion').attr('hidden',false);
      }
	   	
      	$("#datosProceso").html("");
	   	$("#checkboxColumnas").html("");
	   	$("#paginacion").html("");
    		
      for (var i = 0; i < res.data.listaBusquedas.length; i++){
    			var tr = construyeFila('PROCESO', res.data.listaBusquedas[i]);
    			$("#datosProceso").append(tr);
    		}
    	
    	var div = createHideShowColumnsWindow({DESCRIPCION_PROCESO_COLUMN:2, DATE_COLUMN:3 });
      	$("#checkboxColumnas").append(div);
      	$("#paginacion").append(textPaginacion);
      	setLang(getCookie('lang'));

        if(paginadorCreado != 'PaginadorCreado'){
          paginador(totalResults, 'cargarProcesos', 'PROCESO');
        }
        
        if(numeroPagina == 0){
          $('#' + (numeroPagina+1)).addClass("active");
          var numPagCookie = numeroPagina+1;
        }else{
          $('#' + numeroPagina).addClass("active");
           var numPagCookie = numeroPagina;
        }

        setCookie('numeroPagina', numPagCookie);
	  
		}).catch((res) => {
		    respuestaAjaxKO(res.code);
		    document.getElementById("modal").style.display = "block";
		});
	}
}

/** Funcion añadir proceso **/
async function addProceso(){
  await anadirProcesoAjaxPromesa()
  .then((res) => {
    
    $("#form-modal").modal('toggle');
    respuestaAjaxOK("PROCESO_GUARDADO_OK", res.code);

    let idElementoList = ["nombreProceso", "descripcionProceso", "fechaProceso", "selectProcedimientos", "selectObjetivos", "nivel"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
    
    $('#nombreProceso').val(getCookie('nombreProceso'));
    $('#descripcionProceso').val(getCookie('descripProceso'));
    $('#fechaProceso').val(getCookie('fechaProceso'));
    buscarProceso(getCookie('numeroPagina'), tamanhoPaginaProceso, 'buscarPaginacion', 'PaginadorNo');

  }).catch((res) => {
      $("#form-modal").modal('toggle');

      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreProceso", "descripcionProceso", "fechaProceso", "selectProcedimientos", "selectObjetivos", "nivel"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";
  });
}


/** Funcion buscar proceso **/
async function buscarProceso(numeroPagina, tamanhoPagina, accion, paginadorCreado){
  await buscarProcesoAjaxPromesa(numeroPagina, tamanhoPagina,accion)
  .then((res) => {
      cargarPermisosFuncProceso();
      if($('#form-modal').is(':visible')) {
         $("#form-modal").modal('toggle');
      };
      guardarParametrosBusqueda(res.data.datosBusqueda);
      var numResults = res.data.numResultados + '';
      var totalResults = res.data.tamanhoTotal + '';
        var inicio = 0;
      if(res.data.listaBusquedas.length == 0){
        inicio = 0;
        document.getElementById('itemPaginacion').style.display="none";
      }else{
        inicio = parseInt(res.data.inicio)+1;
        document.getElementById('itemPaginacion').style.display="block";
      }
      var textPaginacion = inicio + " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults;

      $("#datosProceso").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFila('PROCESO', res.data.listaBusquedas[i]);
          $("#datosProceso").append(tr);
        }
      
      	var div = createHideShowColumnsWindow({DESCRIPCION_PROCESO_COLUMN:2, DATE_COLUMN:3 });
      	$("#checkboxColumnas").append(div);
      	$("#paginacion").append(textPaginacion);
      	setLang(getCookie('lang'));

      if(paginadorCreado != 'PaginadorCreado'){
          paginador(totalResults, 'buscarProceso', 'PROCESO');
      }

      if(numeroPagina == 0){
        $('#' + (numeroPagina+1)).addClass("active");
        var numPagCookie = numeroPagina+1;
      }else{
        $('#' + numeroPagina).addClass("active");
        var numPagCookie = numeroPagina;
      }
      setCookie('numeroPagina', numPagCookie);

  
  }).catch((res) => {
      cargarPermisosFuncProceso();
      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreProceso", "descripcionProceso", "fechaProceso", "selectProcedimientos", "selectObjetivos", "nivel"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";
  });
}

/*Función que refresca la tabla por si hay algún cambio en BD */
async function refrescarTabla(numeroPagina, tamanhoPagina){
  await cargarFuncionalidadesAjaxPromesa(numeroPagina, tamanhoPagina)
  .then((res) => {
      cargarPermisosFuncFuncionalidad();
      setCookie('nombreFuncionalidad', '');
      setCookie('descripFuncionalidad', '');
      var numResults = res.data.numResultados + '';
      var totalResults = res.data.tamanhoTotal + '';
      var inicio = 0;
      if(res.data.listaBusquedas.length == 0){
        inicio = 0;
      }else{
        inicio = parseInt(res.data.inicio)+1;
      }
      var textPaginacion = inicio + " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults;

      if(res.data.listaBusquedas.length == 0){
        $('#itemPaginacion').attr('hidden',true);
      }else{
        $('#itemPaginacion').attr('hidden',false);
      }

      document.getElementById('cabecera').style.display = "block";
      document.getElementById('cabeceraEliminados').style.display == "none";
      
      $("#datosFuncionalidad").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFila('FUNCIONALIDAD', res.data.listaBusquedas[i]);
          $("#datosFuncionalidad").append(tr);
        }
      
      var div = createHideShowColumnsWindow({FUNCIONALIDAD_DESCRIPTION_COLUMN:2});
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));

      setCookie('nombreFuncionalidad', '');
      setCookie('descripFuncionalidad', '');

      paginador(totalResults, 'cargarFuncionalidades', 'FUNCIONALIDAD');

      if(numeroPagina == 0){
        $('#' + (numeroPagina+1)).addClass("active");
        var numPagCookie = numeroPagina + 1 ;
      }else{
        $('#' + numeroPagina).addClass("active");
         var numPagCookie = numeroPagina;
      }

      setCookie('numeroPagina', numPagCookie);
      comprobarOcultos();
    
    }).catch((res) => {
      
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}

/*Función que busca los eliminados de la tabla de rol*/
async function buscarEliminados(numeroPagina, tamanhoPagina, paginadorCreado){
  await buscarEliminadosAjaxPromesa(numeroPagina, tamanhoPagina)
  .then((res) => {
      cargarPermisosFuncFuncionalidad();
      var numResults = res.data.numResultados + '';
      var totalResults = res.data.tamanhoTotal + '';
      var inicio = 0;
      if(res.data.listaBusquedas.length == 0){
        inicio = 0;
        $('#itemPaginacion').attr('hidden', true);
      }else{
        inicio = parseInt(res.data.inicio)+1;
        $('#itemPaginacion').attr('hidden', false);
      }
      var textPaginacion = inicio + " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults;

      
      if(res.data.listaBusquedas.length == 0){
        document.getElementById('cabecera').style.display = "none";
        document.getElementById('cabeceraEliminados').style.display = "block";    
      }

      $("#datosFuncionalidad").html("");
      $("#checkboxColumnas").html("");
      $("#paginacion").html("");
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
          var tr = construyeFilaEliminados('FUNCIONALIDAD', res.data.listaBusquedas[i]);
          $("#datosFuncionalidad").append(tr);
        }
      
      var div = createHideShowColumnsWindow({FUNCIONALIDAD_DESCRIPTION_COLUMN:2});
      $("#checkboxColumnas").append(div);
      $("#paginacion").append(textPaginacion);
      setLang(getCookie('lang'));

      setCookie('nombreFuncionalidad', '');
      setCookie('descripFuncionalidad', '');

      if(paginadorCreado != 'PaginadorCreado'){
         paginador(totalResults, 'buscarEliminadosFuncionalidad', 'FUNCIONALIDAD');
      }
     

      if(numeroPagina == 0){
        $('#' + (numeroPagina+1)).addClass("active");
      }else{
        $('#' + numeroPagina).addClass("active");
      }
    
    }).catch((res) => {
      
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  
  });
}

/** Función que visualiza una funcionalidad **/
async function detalleFuncionalidad(){
  await detalleFuncionalidadAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    $('#nombreFuncionalidad').val(getCookie('nombreFuncionalidad'));
    $('#descripcionFuncionalidad').val(getCookie('descripFuncionalidad'));

  }).catch((res) => {
      $("#form-modal").modal('toggle');

      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
      resetearFormulario("formularioGenerico", idElementoList);
      
      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";
  });
}

/** Función que edita un rol **/
async function editFuncionalidad(){
  await editarFuncionalidadAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    respuestaAjaxOK("FUNCIONALIDAD_EDITADA_OK", res.code);

    let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
    $('#nombreFuncionalidad').val(getCookie('nombreFuncionalidad'));
    $('#descripcionFuncionalidad').val(getCookie('descripFuncionalidad'));
    buscarFuncionalidad(getCookie('numeroPagina'), tamanhoPaginaFuncionalidad, 'buscarPaginacion', 'PaginadorCreado');

  }).catch((res) => {
    $("#form-modal").modal('toggle');

     respuestaAjaxKO(res.code);

    let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    resetearFormulario("formularioGenerico", idElementoList);

    setLang(getCookie('lang'));

    document.getElementById("modal").style.display = "block";


  });
}

/** Función que elimina una funcionalidad **/
async function deleteFuncionalidad(){
  await eliminarFuncionalidadAjaxPromesa()
  .then((res) => {
    $("#form-modal").modal('toggle');

    respuestaAjaxOK("FUNCIONALIDAD_ELIMINADA_OK", res.code);

    let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    resetearFormulario("formularioGenerico", idElementoList);
    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
   
    refrescarTabla(0, tamanhoPaginaFuncionalidad);

  }).catch((res) => {
     
     $("#form-modal").modal('toggle');
      respuestaAjaxKO(res.code);

      let idElementoList = ["nombreFuncionalidad", "descripcionFuncionalidad"];
      resetearFormulario("formularioGenerico", idElementoList);

      setLang(getCookie('lang'));

      document.getElementById("modal").style.display = "block";


  });
}

/*Función que reactiva los eliminados de la tabla de funcionalidades*/
async function reactivarFuncionalidad(){
  await reactivarFuncionalidadesAjaxPromesa()
  .then((res) => {

    cargarPermisosFuncFuncionalidad();

    $("#form-modal").modal('toggle');

    respuestaAjaxOK("FUNCIONALIDAD_REACTIVADA_OK", res.code);

    setLang(getCookie('lang'));
    document.getElementById("modal").style.display = "block";
      
    buscarEliminados(0, tamanhoPaginaFuncionalidad, 'PaginadorNo');
    
    }).catch((res) => {
      $("#form-modal").modal('toggle');
      respuestaAjaxKO(res.code);
      setLang(getCookie('lang'));
      document.getElementById("modal").style.display = "block";
  });
}

/** Funcion para mostrar el formulario para añadir un proceso **/
function showAddProcesos() {
  var idioma = getCookie('lang');
  cambiarFormulario('ADD_PROCESO', 'javascript:addProceso();', 'return comprobarAddProceso();');
  cambiarOnBlurCampos('return comprobarNombreProceso(\'nombreProceso\', \'errorFormatoNombreProceso\', \'nombreProceso\')', 
  'return comprobarDescripcionProceso(\'descripcionProceso\', \'errorFormatoDescripcionProceso\', \'descripcionProceso\')',
  'return comprobarFechaProceso(\'fechaProceso\', \'errorFormatoFechaProceso\', \'fechaProceso\')');
  cambiarIcono('images/add.png', 'ICONO_ADD', 'iconoAddProceso', 'Añadir');
  setLang(idioma);

  $('#subtitulo').attr('hidden', true);
  $('#labelNombreProceso').attr('hidden', true);
  $('#labelDescripcionProceso').attr('hidden', true);
  $('#labelFechaProceso').attr('hidden', true);
  $('#labelNombreProcedimiento').attr('hidden', true);
  $('#labelNivel').attr('hidden', true);
  $('#labelRespuestaPosible').attr('hidden', true);
  $('#selectProcedimientos').attr('hidden', false);
  $('#respuestasPosibles').attr('hidden', false);
  $('#btnAddObjetivoPlan').attr('hidden', false);
  $('#objetivosNiveles').attr('hidden', false);
  $('#formatoProcedimiento').attr('hidden', false);

  $('#selectObjetivos option[value=0]').attr('selected', true);
  $('#selectProcedimientos option[value=0]').attr('selected', true);

  let campos = ["nombreProceso", "descripcionProceso", "fechaProceso", "selectProcedimientos", "selectObjetivos", "nivel"];
  let obligatorios = ["obligatorioNombreProceso", "obligatorioDescripcionProceso", "obligatorioFechaProceso", "obligatorioProcedimiento", "obligatorioNivel", "obligatorioRespuestaPosible"];
  eliminarReadonly(campos);
  mostrarObligatorios(obligatorios);
  habilitaCampos(campos);

}

/** Funcion para buscar un proceso **/
function showBuscarProceso() {
  var idioma = getCookie('lang');

  cambiarFormulario('SEARCH_PROCESO', 'javascript:buscarProceso(0,' + tamanhoPaginaProceso + ', \'buscarModal\'' + ',\'PaginadorNo\');', 'return comprobarBuscarProceso();');
   cambiarOnBlurCampos('return comprobarNombreProcesoSearch(\'nombreProceso\', \'errorFormatoNombreProceso\', \'nombreProceso\')', 
  'return comprobarDescripcionProcesoSearch(\'descripcionProceso\', \'errorFormatoDescripcionProceso\', \'descripcionProceso\')',
  'return comprobarFechaProcesoSearch(\'fechaProceso\', \'errorFormatoFechaProceso\', \'fechaProceso\')');
  cambiarIcono('images/search.png', 'ICONO_SEARCH', 'iconoSearchProceso', 'Buscar');
  setLang(idioma);

  $('#subtitulo').attr('hidden', true);
  $('#labelNombreProceso').attr('hidden', true);
  $('#labelDescripcionProceso').attr('hidden', true);
  $('#labelFechaProceso').attr('hidden', true);
  $('#labelNombreProcedimiento').attr('hidden', true);
  $('#labelNivel').attr('hidden', true);
  $('#labelRespuestaPosible').attr('hidden', true);
  $('#selectProcedimientos').attr('hidden', true);
  $('#respuestasPosibles').attr('hidden', true);
  $('#btnAddObjetivoPlan').attr('hidden', true);
  $('#objetivosNiveles').attr('hidden', true);
  $('#formatoProcedimiento').attr('hidden', true);

  let campos = ["nombreProceso", "descripcionProceso", "fechaProceso", "selectProcedimientos", "selectObjetivos", "nivel"];
  let obligatorios = ["obligatorioNombreProceso", "obligatorioDescripcionProceso", "obligatorioFechaProceso", "obligatorioProcedimiento", "obligatorioNivel", "obligatorioRespuestaPosible"];
  
  eliminarReadonly(campos);
  ocultarObligatorios(obligatorios);
  habilitaCampos(campos);

}

/** Funcion para visualizar una funcionalidad **/
function showDetalle(nombreFuncionalidad, descripFuncionalidad) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('DETAIL_FUNCIONALITY', 'javascript:detalleFuncionalidad();', '');
    cambiarIcono('images/close2.png', 'ICONO_CERRAR', 'iconoCerrar', 'Detalle');
   
    setLang(idioma);
    
    $('#labelFuncionalidadName').removeAttr('hidden');
    $('#labelFuncionalidadDescription').removeAttr('hidden');
    $('#subtitulo').attr('hidden', '');

    rellenarFormulario(nombreFuncionalidad, descripFuncionalidad);

    let campos = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    let obligatorios = ["obligatorioFuncionalidadName", "obligatorioFuncionalidadDescription"];
    anadirReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}

/** Funcion para editar un proceso **/
function showEditar(nombreProceso, descripProceso, fechaProceso, idProceso, procedimientos, objetivos, respuestasPosibles) {
  var idioma = getCookie('lang');

    cambiarFormulario('EDIT_PROCESO', 'javascript:editProceso();', 'return comprobarEditProceso();');
    cambiarOnBlurCampos('return comprobarNombreProceso(\'nombreProceso\', \'errorFormatoNombreProceso\', \'nombreProceso\')', 
  'return comprobarDescripcionProceso(\'descripcionProceso\', \'errorFormatoDescripcionProceso\', \'descripcionProceso\')',
  'return comprobarFechaProceso(\'fechaProceso\', \'errorFormatoFechaProceso\', \'fechaProceso\')');
    cambiarIcono('images/edit.png', 'ICONO_EDIT', 'iconoEditarProceso', 'Editar');
   
    setLang(idioma);
    
  	$('#subtitulo').attr('hidden', true);
	$('#subtitulo').attr('hidden', true);
  	$('#labelNombreProceso').attr('hidden', true);
  	$('#labelDescripcionProceso').attr('hidden', true);
  	$('#labelFechaProceso').attr('hidden', true);
  	$('#labelNombreProcedimiento').attr('hidden', true);
  	$('#labelNivel').attr('hidden', true);
  	$('#labelRespuestaPosible').attr('hidden', true);
  	$('#selectProcedimientos').attr('hidden', true);
  	$('#respuestasPosibles').attr('hidden', true);
  	$('#btnAddObjetivoPlan').attr('hidden', false);
  	$('#objetivosNiveles').attr('hidden', false);
  	$('#formatoProcedimiento').attr('hidden', false);

    rellenarFormulario(nombreProceso, descripProceso, fechaProceso, procedimientos, objetivos, respuestasPosibles);
    insertacampo(document.formularioGenerico,'idProceso', idProceso);

    let campos = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    let obligatorios = ["obligatorioFuncionalidadName", "obligatorioFuncionalidadDescription"];
    eliminarReadonly(campos);
    mostrarObligatorios(obligatorios);
    habilitaCampos(campos);
    deshabilitaCampos(["nombreFuncionalidad"]);
    anadirReadonly(["nombreFuncionalidad"]);

}

/** Función para eliminar una funcionalidad **/
function showEliminar(nombreFuncionalidad, descripFuncionalidad, idFuncionalidad) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('DELETE_FUNCIONALITY', 'javascript:deleteFuncionalidad();', '');
    cambiarIcono('images/delete.png', 'ICONO_ELIMINAR', 'iconoEliminar', 'Eliminar');
   
    setLang(idioma);
    
    $('#labelFuncionalidadName').removeAttr('hidden');
    $('#labelFuncionalidadDescription').removeAttr('hidden');
    $('#subtitulo').removeAttr('class');
    $('#subtitulo').empty();
    $('#subtitulo').attr('class', 'SEGURO_ELIMINAR_FUNC');
    $('#subtitulo').attr('hidden', false);
    

    rellenarFormulario(nombreFuncionalidad, descripFuncionalidad);
    insertacampo(document.formularioGenerico,'idFuncionalidad', idFuncionalidad);

    let campos = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    let obligatorios = ["obligatorioFuncionalidadName", "obligatorioFuncionalidadDescription"];
    anadirReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}

/** Función para reactivar una funcionalidad **/
function showReactivar(nombreFuncionalidad, descripFuncionalidad , idFuncionalidad) {
  
    var idioma = getCookie('lang');

    cambiarFormulario('REACTIVATE_FUNC', 'javascript:reactivarFuncionalidad();', '');
    cambiarIcono('images/reactivar2.png', 'ICONO_REACTIVAR', 'iconoReactivar', 'Reactivar');
   
    setLang(idioma);
    
    $('#labelFuncionalidadName').removeAttr('hidden');
    $('#labelFuncionalidadDescription').removeAttr('hidden');
    $('#subtitulo').removeAttr('class');
    $('#subtitulo').empty();
    $('#subtitulo').attr('class', 'SEGURO_REACTIVAR_FUNC');
     $('#subtitulo').attr('hidden', false);
    

    rellenarFormulario(nombreFuncionalidad, descripFuncionalidad);
    insertacampo(document.formularioGenerico,'idFuncionalidad', idFuncionalidad);

    let campos = ["nombreFuncionalidad", "descripcionFuncionalidad"];
    let obligatorios = ["obligatorioFuncionalidadName", "obligatorioFuncionalidadDescription"];
    anadirReadonly(campos);
    ocultarObligatorios(obligatorios);
    deshabilitaCampos(campos);

}

/**Función para cambiar onBlur de los campos*/
function cambiarOnBlurCampos(onBlurNombreProceso, onBlurDescripcionProceso, onBlurFechaProceso) {
    
    if (onBlurNombreProceso != ''){
        $("#nombreProceso").attr('onblur', onBlurNombreProceso);
    }

    if (onBlurDescripcionProceso != ''){
        $("#descripcionProceso").attr('onblur', onBlurDescripcionProceso);
    }

    if (onBlurFechaProceso != ''){
        $("#fechaProceso").attr('onblur', onBlurFechaProceso);
    }
}

/**Función que rellenado los datos del formulario*/
function rellenarFormulario(nombreProceso, descripProceso, fechaProceso, procedimientos, objetivos, respuestasPosibles) {

    $("#nombreProceso").val(nombreProceso);
    $("#descripcionProceso").val(descripProceso); 
    $("#fechaProceso").val(fechaProceso); 

    var options = document.getElementById('selectProcedimientos').options;
    var options = document.getElementById('selectObjetivos').options;
    var options = document.getElementById('respuestasPosibles').options;

    for(var i=0;i<procedimientos.length; i++){
    	for(var j = 0; j<options.length; j++){
	      var text = options[j].text;
	      if(options[j].text == procedimientos[i].nombreProcedimiento){
	        options[j].selected = true;
	      }else{

	        options[j].selected = false;
	      }
	    }
    }

    for(var i = 0; i<respuestasPosibles.length; i++){
    	$('[name=respuestaPosible][value=' + respuestasPosibles[i].idRespuesta + ']' ).prop('checked', true);
    }

    var numeroObjetivos = objetivos.length;
    setCookie('numeroObjNivel', 1);

    for(var i=0; i<numeroObjetivos; i++){
    	addObjetivosNiveles();
    }

    for(var i = 0; i<numeroObjetivos.length; i++){
    	for(var j = 0; j<options.length; j++){
	      var text = options[j].text;
	      if(options[j].text == objetivos[i].nombreObjetivo){
	        options[j].selected = true;
	      }else{

	        options[j].selected = false;
	      }
	    }
    }

}

/** Función para gestionar los iconos dependiendo de los permisos de los usuarios **/
function gestionarPermisosProceso(idElementoList) {
  document.getElementById('cabecera').style.display = "none";
  document.getElementById('tablaDatos').style.display = "none";
  document.getElementById('filasTabla').style.display = "none";
  $('#itemPaginacion').attr('hidden', true);

  idElementoList.forEach( function (idElemento) {
    switch(idElemento){
      case "Añadir":
        $('#btnAddProceso').attr('src', 'images/add3.png');
        $('#btnAddProceso').css("cursor", "pointer");
        $('#divAddProceso').attr("data-toggle", "modal");
        $('#divAddProceso').attr("data-target", "#form-modal");
      break;

      case "Modificar" : 
        $('.editarPermiso').attr('src', 'images/edit3.png');
        $('.editarPermiso').css("cursor", "pointer");
        $('.editarPermiso').attr("data-toggle", "modal");
        $('.editarPermiso').attr("data-target", "#form-modal");
      break;

      case "Eliminar" :
        $('.eliminarPermiso').attr('src', 'images/delete3.png');
        $('.eliminarPermiso').css("cursor", "pointer");
        $('.eliminarPermiso').attr("data-toggle", "modal");
        $('.eliminarPermiso').attr("data-target", "#form-modal");
      break;

      case 'Listar' :
        $('#btnListarProcesos').attr('src', 'images/search3.png');
        $('#btnSearchDelete').attr('src', 'images/searchDelete3.png');
        $('#btnListarProcesos').css("cursor", "pointer");
        $('.iconoSearchDelete').css("cursor", "pointer");
        $('#divSearchDelete').attr("onclick", "javascript:buscarEliminados(0,\'tamanhoPaginaProceso\', \'PaginadorNo\')");
        $('#divListarProcesos').attr("data-toggle", "modal");
        $('#divListarProcesos').attr("data-target", "#form-modal");
        document.getElementById('cabecera').style.display = "block";
        document.getElementById('tablaDatos').style.display = "block";
        document.getElementById('filasTabla').style.display = "block";
         $('#itemPaginacion').attr('hidden', false);

        if(document.getElementById('cabeceraEliminados').style.display == "block"){
           document.getElementById('cabecera').style.display = "none";

           var texto = document.getElementById('paginacion').innerHTML;
           if(texto == "0 - 0 total 0"){
           $('#itemPaginacion').attr('hidden', true);
          }

        }

      break;

      case "Visualizar" :
        $('.detallePermiso').attr('src', 'images/detail3.png');
        $('.detallePermiso').css("cursor", "pointer");
        $('.detallePermiso').attr("data-toggle", "modal");
        $('.detallePermiso').attr("data-target", "#form-modal");
      break;

      case "Reactivar" : 
        $('.reactivarPermiso').attr('src', 'images/reactivar.png');
        $('.reactivarPermiso').css("cursor", "pointer");
        $('.reactivarPermiso').attr("data-toggle", "modal");
        $('.reactivarPermiso').attr("data-target", "#form-modal");
      break;

    } 
    }); 
}

$(document).ready(function() {
  $("#form-modal").on('hidden.bs.modal', function() {
    
    let idElementoErrorList = ["errorFormatoNombreProceso", "errorFormatoDescripcionProceso", "errorFormatoFechaProceso", "errorFormatoNombreProcedimientoSelect", "errorFormatoNombreObjetivoSelect", "errorFormatoNivel"];
    
    let idElementoList = ["nombreProceso", "descripcionProceso", "fechaProceso", "selectProcedimientos", "selectObjetivos", "nivel"];

    limpiarFormulario(idElementoList);
    eliminarMensajesValidacionError(idElementoErrorList, idElementoList);
    setLang(getCookie('lang'));
  });

});
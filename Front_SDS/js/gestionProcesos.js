/**Función para añadir más objetivos y niveles*/
function addObjetivosNiveles(){

	/**necesitamos darle un id nuevo cada vez que creemos uno porque si eliminamos un objetivo con su nivel deberemos hacer una función
	 * que elimine ese div*/
	objetivosNiveles = '<div id="objetivosNiveles2" class="objetivosNiveles">' +
		                    '<label class="labelForm NOMBRE_OBJETIVO" id="labelNombreObjetivo" hidden></label>' +
            	  			'<select id="selectObjetivos" class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">' +
            	  				'<option>Objetivo 1</option>' +
            	  				'<option>Objetivo 2</option>' +
            	  			'</select>' +
					  		'<div class="obligatorio tooltip2" id="obligatorioObjetivos">*' +
          						'<span class="tooltiptext2 campoObligatorio CAMPO_OBLIGATORIO">Campo obligatorio</span>' +
      			  			'</div>' +
	          			    '<div style="display:none" id="errorFormatoNombreObjetivoSelect"></div>' +  
						  	'<label class="labelForm NIVEL" id="labelNombrePlan" hidden></label>'+                           
                  			'<input type="text" maxlength="48" size="48" placeholder="NIVEL" name="NIVEL" id="nivel" class="NIVEL" onblur=""/>' +
                   			'<div class="obligatorio tooltip2" id="obligatorioNivel">*' +
          						'<span class="tooltiptext2 campoObligatorio CAMPO_OBLIGATORIO">Campo obligatorio</span>' +
          			  		'</div>' +
                      		'<div style="display:none" id="errorFormatoNivel"></div>' +
                      		'<div name="btnBorrar" value="ELiminar" onclick="javascript:eliminarObjetivosNiveles(\'objetivosNiveles2\')" class="tooltip6 borrarIcon">' +
      			  				'<img class="iconoBorrar iconBorrar" src="images/delete3.png" alt="Eliminar" />' +
        						'<span class="tooltiptext iconBorrar ICONO_ELIMINAR">Eliminar</span>' +
      			  			'</div>' +
      			  		'</div>';

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
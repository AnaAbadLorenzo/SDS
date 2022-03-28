/**Función para recuperar los roles con ajax y promesas*/
function cargarFuncionalidadesAjaxPromesa(){
  return new Promise(function(resolve, reject) {
  	var token = getCookie('tokenUsuario');

    var data = {
      inicio : 0,
      tamanhoPagina : 16,
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoFuncionalidades,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'FUNCIONALIDADES_LISTADAS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        errorFailAjax(jqXHR.status);
      });
  });
}


/* Función para obtener las funcionalidades del sistema */
async function cargarFuncionalidades(){
	await cargarFuncionalidadesAjaxPromesa()
	  .then((res) => {
	  	var numResults = res.data.numResultados + '';
	  	var totalResults = res.data.tamanhoTotal + '';
	  	var textPaginacion = "1 - " + numResults  + " de " + totalResults 
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
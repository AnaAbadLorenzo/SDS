/**Función para recuperar los roles con ajax y promesas*/
function cargarRolesAjaxPromesa(){
  return new Promise(function(resolve, reject) {
    var data = {
      inicio : 0,
      tamanhoPagina : 16,
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoRoles,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      }).done(res => {
        if (res.code != 'ROLES_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR, textStatus, errorThrown ) {
        alert( 'Error!!' );
      });
  });
}



/* Función para obtener los roles del sistema */
async function cargarRoles(){
	await cargarRolesAjaxPromesa()
	  .then((res) => {
	  	var numResults = res.data.numResultados + '';
	  	var totalResults = res.data.tamanhoTotal + '';
	  	var textPaginacion = "1 - " + numResults  + " de " + totalResults 
	   	$("#datosRol").html("");
	   	$("#ckeckboxColumnas").html("");
	   	$("#paginacion").html("");
    		for (var i = 0; i < res.data.listaBusquedas.length; i++){
    			var tr = construyeFila('ROL', res.data.listaBusquedas[i]);
    			$("#datosRol").append(tr);
    		}
    	
    	var div = createHideShowColumnsWindow({ROL_NAME_COLUMN:1, ROL_DESCRIPTION_COLUMN:2});
        $("#ckeckboxColumnas").append(div);
        $("#paginacion").append(textPaginacion);

        setLang(idioma);
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
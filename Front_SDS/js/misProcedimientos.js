function iniciarProcedimientoUsuario(){
	window.location.href = "GestionDeProcesos.html";
}

/* Función para obtener los procedimientos de un usuario del sistema*/
async function cargarProcedimientosUsuarioEjecutados(numeroPagina, tamanhoPagina, paginadorCreado){
  if(getCookie('rolUsuario') == "usuario"){
    await cargarMisProcedimientosAjaxPromesa(numeroPagina, tamanhoPagina)
    .then((res) => {
        document.getElementById('misProcedimientos').style.display = "block";
        var numResults = res.data.numResultados + '';
        var totalResults = res.data.tamanhoTotal + '';
          var inicio = 0;
        if(res.data.listaBusquedas.length == 0){
          inicio = 0;
           $('#itemPaginacion').attr('hidden',true);
        }else{
          inicio = parseInt(res.data.inicio)+1;
            $('#itemPaginacion').attr('hidden',false);
        }
        var textPaginacion = inicio + " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " total " + totalResults;
     
        $('#procedimientos').html('');
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
        	cargarMisProcedimientosUsuario(res.data.listaBusquedas[i]);
     
        }

        if(paginadorCreado != 'PaginadorCreado'){
          paginador(totalResults, 'cargarProcedimientosUsuarioEjecutados', 'PROCEDIMIENTOSEJECUTADOS');
      	}
      
      if(numeroPagina == 0){
        $('#' + (numeroPagina+1)).addClass("active");
      }else{
        $('#' + numeroPagina).addClass("active");
      }

      setLang(getCookie('lang'));


      }).catch((res) => {
          respuestaAjaxKO(res.code);
          document.getElementById("modal").style.display = "block";
      });
  
  	}
}


/** Función para obtener los procedimientos de un usuario con ajax y promesas**/
function cargarMisProcedimientosAjaxPromesa(numeroPagina, tamanhoPagina){
  return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var procedimiento = null;
    var usuario = {
    	dniUsuario : '',
    	usuario : getCookie('usuario'),
    	passwdUsuario : '',
    	borradoUsuario : 0
    }

    var data = {
    	puntuacionProcedimientoUsuario : '',
    	fechaProcedimientoUsuario : '',
    	usuario: usuario,
    	procedimiento : procedimiento,
    	inicio: calculaInicio(numeroPagina, tamanhoPaginaProcedimientoUsuario),
    	tamanhoPagina : tamanhoPaginaProcedimientoUsuario
    }

  	$.ajax({
      method: "POST",
      url: urlPeticionAjaxListarProcedimientoUsuario,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PROCEDIMIENTOS_USUARIO_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Función para obtener el número de procesos asociados al procedimiento **/
function cargarNumeroProcesosProcedimientos(idProcedimiento, idProceso){
	return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

   	var data = {
   		idProceso : idProceso,
   		idProcedimiento : idProcedimiento
   	}

  	$.ajax({
      method: "POST",
      url: urlPeticionAjaxListadoProcesosProcedimiento,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PROCESOS_PROCEDIMIENTOS_LISTADOS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

function showPlan(nombrePlan, descripPlan){

    $('#tituloFormsModalMostrarPlan').addClass('DETAIL_PLAN');
    $('#iconoAccionesMostrarPlan').attr('src', 'images/close2.png');
    $('#iconoAccionesMostrarPlan').removeClass();
    $('#iconoAccionesMostrarPlan').addClass('ICONO_DETALLE');
    $('#iconoAccionesMostrarPlan').addClass('iconoCerrar');
    $('#iconoAccionesMostrarPlan').attr('alt', 'Editar');
    $('#spanAccionesMostrarPlan').removeClass();
    $('#spanAccionesMostrarPlan').addClass('tooltiptext');
    $('#spanAccionesMostrarPlan').addClass('ICONO_DETALLE');
    $('#btnAccionesMostrarPlan').attr('value', 'Detalle');

    $('#labelNombrePlanMostrarPlan').attr('hidden', false);
    $('#labelDescripcionPlanMostrarPlan').attr('hidden', false);
    $('#nombrePlan').val(nombrePlan);
    $('#descripcionPlanMostrarPlan').val(descripPlan);

    deshabilitaCampos(['nombrePlan', 'descripcionPlanMostrarPlan']);
    anadirReadonly(['nombrePlan', 'descripcionPlanMostrarPlan']);
   
    setLang(getCookie('lang'));
}

/** Función para construir la pantalla de procedimientos - usuario **/
async function cargarMisProcedimientosUsuario(procedimientosUsuario){
	var procedimientoUsuario = "";
	await cargarNumeroProcesosProcedimientos(procedimientosUsuario.procedimiento.idProcedimiento, '')
		.then((res) =>{
				var atributosFunciones = ["'" + procedimientosUsuario.procedimiento.plan.nombrePlan + "'", "'" + procedimientosUsuario.procedimiento.plan.descripPlan + "'"]; 
				if(res.data.listaBusquedas.length == 0){
					numeroProcesos = 0;
				}else{
					numeroProcesos = res.data.tamanhoTotal;
				}

				var fechaProcedimientoUsuario = new Date(procedimientosUsuario.fechaProcedimientoUsuario);
				var fecha = convertirFecha(fechaProcedimientoUsuario.toString());
				procedimientoUsuario = '<div class="col-md-12 col-lg-12 col-xl-12 mb-12 paddingTop">' + 
								'<div class="card">' + 
									'<div class="card-body-plan">' + 
										'<div class="card-title">' + procedimientosUsuario.procedimiento.nombreProcedimiento + '</div>' + 
										'<div class="card-text">' + procedimientosUsuario.procedimiento.descripProcedimiento + '</div>' +
										'<div class="procesos">0/' + numeroProcesos + '</div>' + 
										'<div class="puntuacion">Puntuación: ' + procedimientosUsuario.puntuacionProcedimientoUsuario +'%</div>' +
										'<div class="fecha">' + fecha + '</div>' + 
										'<div id="iniciarProcedimiento" class="tooltip10 procedimientoIcon">' + 
											'<img id="iconoIniciarProcedimiento" class="iconoProcedimiento iconProcedimiento" src="images/iniciarProcedimiento.png" alt="Iniciar procedimiento" onclick="iniciarProcedimientoUsuario();"/>' +
											'<span class="tooltiptext iconProcedimiento ICON_INICIAR_PROCEDIMIENTO"></span>' + 
										'</div>' + 
										'<div id="continuarProcedimiento" class="tooltip11 continuarIcon">' + 
					        				'<img class="iconoContinuar iconContinuar" src="images/continuarProcedimiento2.png" alt="Continuar procedimiento" onclick=""/>' + 
											'<span class="tooltiptext iconContinuar ICON_CONTINUAR_PROCEDIMIENTO"></span>' + 
					    			'</div>' +
					    			'<div id="finalizadoProcedimiento" class="tooltip12 finalizadoIcon">' + 
					        				'<img class="iconoFinalizado iconFinalizado" src="images/procedimientoFinalizado2.png" alt="Procedimiento finalizado" onclick=""/>' + 
					        				'<span class="tooltiptext iconFinalizado ICON_PROCEDIMIENTO_FINALIZADO"></span>' + 
					    			'</div>' + 
                  '</div>' + 
	                '<div class="card-footer">' + 
	                    '<div class="tooltip8 planIcon">' + 
	                    		'<img class="iconoPlan iconPlan" src="images/plan.png" alt="Plan" data-toggle="modal" data-target="#modalMostrarPlan" onclick="showPlan(' + atributosFunciones + ')"/>' + 
	                    		'<span class="tooltiptext iconPlan ICON_PLAN"></span>' + 
	                    '</div>' + 
	                    '<div class="card-title-plan">Plan: ' + procedimientosUsuario.procedimiento.plan.nombrePlan  + '</div>' ;
	               '</div>' + 
                '</div>' + 
              '</div>';

         $('#procedimientos').append(procedimientoUsuario);
		}).catch((res) => {
          respuestaAjaxKO(res.code);
          document.getElementById("modal").style.display = "block";
      });
}

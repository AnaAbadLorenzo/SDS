function iniciarProcedimientoUsuario(){
	window.location.href = "GestionDeProcesos.html";
}

/* Función para obtener los procedimientos de un usuario del sistema*/
async function cargarProcedimientosUsuarioEjecutados(numeroPagina, tamanhoPagina, paginadorCreado){
  if(getCookie('rolUsuario') == "usuario"){
    await cargarMisProcedimientos(numeroPagina, tamanhoPagina)
    .then((res) => {
        document.getElementById('misProcedimientos').style.display = "block";
     
        $('#procedimientos').html('');
        for (var i = 0; i < res.data.listaBusquedas.length; i++){
            var tr = cargarMisProcedimientosUsuario(res.data.listaBusquedas[i]);
            $('#procedimientos').append(tr);
        }

        setLang(getCookie('lang'));


      }).catch((res) => {
          respuestaAjaxKO(res.code);
          document.getElementById("modal").style.display = "block";
      });
  
  	}
}

function cargarMisProcedimientosUsuario(procedimientosUsuario){
	var procedimientosUsuario = "";
	var fechaProcedimientoUsuario = new Date(procedimientosUsuario.fechaProcedimientoUsuario);

	procedimientosUsuario = '<div class="col-md-12 col-lg-12 col-xl-12 mb-12 paddingTop">' + 
								'<div class="card">' + 
									'<div class="card-body-plan">' + 
										'<div class="card-title">' + procedimientosUsuario.nombreProcedimiento + '</div>' + 
										'<div class="card-text">' + procedimientosUsuario.descripProcedimiento + '</div>' +
										'<div class="procesos">0/10</div>' + 
										'<div class="puntuacion" >Puntuación:' + procedimientosUsuario.puntuacionProcedimientoUsuario +'</div>' +
										'<div class="fecha">' + convertirFecha(fechaProcedimientoUsuario.toString()) + </div>' +
										'<div id="iniciarProcedimiento" class="tooltip10 procedimientoIcon">' + 
											'<img id="iconoIniciarProcedimiento" class="iconoProcedimiento iconProcedimiento" 
											src="images/iniciarProcedimiento.png" alt="Iniciar procedimiento" onclick="iniciarProcedimientoUsuario();"/>' +
											'<span class="tooltiptext iconProcedimiento ICON_INICIAR_PROCEDIMIENTO"></span>' + 
										'</div>' + 
										'<div id="continuarProcedimiento" class="tooltip11 continuarIcon">' + 
					        				'<img class="iconoContinuar iconContinuar" src="images/continuarProcedimiento2.png" alt="Continuar procedimiento" onclick=""/>' + 
											'<span class="tooltiptext iconContinuar ICON_CONTINUAR_PROCEDIMIENTO"></span>' + 
					    				'</div>' + 
					    				'<div id="finalizadoProcedimiento" class="tooltip12 finalizadoIcon">' + 
					        				'<img class="iconoFinalizado iconFinalizado" src="images/procedimientoFinalizado2.png" alt="Procedimiento finalizado" onclick=""/>' + 
					        				'<span class="tooltiptext iconFinalizado ICON_PROCEDIMIENTO_FINALIZADO"></span>'
					    				'</div>' + 
                    				'</div>' + 
	                    			'<div class="card-footer">' + 
	                    				'<div class="tooltip8 planIcon">' + 
	                    					'<img class="iconoPlan iconPlan" src="images/plan.png" alt="Plan"/>' + 
	                    					'<span class="tooltiptext iconPlan ICON_PLAN"></span>' + 
	                    				'</div>'+
	                    				'<div class="card-title-plan">Plan: ' + procedimientosUsuario.procedimiento.plan.nombrePlan: + '</div>' + 
	                    			'</div>' + 
                    			'</div>'
                    	'</div>';

    return procedimientosUsuario;
}


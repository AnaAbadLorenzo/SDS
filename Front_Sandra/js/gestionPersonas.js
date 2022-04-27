/** Función para cargar los datos de persona **/
async function cargarPersonas(numeroPagina, tamanhoPagina, paginadorCreado){
	if(getCookie('rolUsuario') == "usuario"){
		await cargarDatosPersonaAjaxPromesa()
		  .then((res) => {
		  	 $('#personaInfoParaAdmin').attr('hidden', true);
		  	 $('#personaInfoParaUsuario').atrr('hidden', false);
		     cargaDatosPersona(res.data.listaBusquedas);
		     cargaDatosUsuario(res.data.listaBusquedas);
		     cargaDatosEmpresa(res.data.listaBusquedas);
		  
		  }).catch((res) => {
		      respuestaAjaxKO(res.code);

		      setLang(getCookie('lang'));

		      document.getElementById("modal").style.display = "block";
		  });
	}else if(getCookie('rolUsuario') == "admin"){
		await cargarPersonasAjaxPromesa(numeroPagina, tamanhoPagina)
			.then((res) => {
                $('#personaInfoParaAdmin').attr('hidden', false);
                $('#personaInfoParaUsuario').atrr('hidden', true);
			    var numResults = res.data.numResultados + '';
                var totalResults = res.data.tamanhoTotal + '';
                var inicio = 0;
                if(res.data.listaBusquedas.length == 0){
                    inicio = 0;
                }else{
                    inicio = parseInt(res.data.inicio)+1;
                }
                var textPaginacion = inicio + " - " + (parseInt(res.data.inicio)+parseInt(numResults))  + " de " + totalResults;
        
                $("#datosPersona").html("");
                $("#checkboxColumnas").html("");
                $("#paginacion").html("");
            
                for (var i = 0; i < res.data.listaBusquedas.length; i++){
                    var tr = construyeFila('PERSONA', res.data.listaBusquedas[i]);
                    $("#datosPersona").append(tr);
                }
        
                var div = createHideShowColumnsWindow({NOMBRE_PERSONA_COLUMN:2, APELLIDOS_PERSONA_COLUMN:3, DIRECCION_PERSONA_COLUMN: 4,
                                                    FECHA_NACIMIENTO_PERSONA_COLUMN: 5, TELEFONO_COLUMN: 6, EMAIL_COLUMN: 7, ACTIVO_COLUMN: 8,
                                                    LOGIN_USUARIO_COLUMN:9, NOMBRE_EMPRESA_COLUMN: 10});
                $("#checkboxColumnas").append(div);
                $("#paginacion").append(textPaginacion);
                setLang(getCookie('lang'));

                if(paginadorCreado != 'PaginadorCreado'){
                  paginador(totalResults, 'cargarPersonas', 'PERSONA');
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

/* Función para obtener los datos de la persona desde BD con ajax y promesa */
function cargarDatosPersonaAjaxPromesa(){
    return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var data = {
      usuario : getCookie('usuario'),
      inicio : 0,
      tamanhoPagina : 1
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarPersonaPorUsuario,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PERSONAS_LISTADAS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}

/** Función para obtener todos los datos de la personas de la BD **/
function cargarPersonasAjaxPromesa(numeroPagina, tamanhoPagina){
    return new Promise(function(resolve, reject) {
    var token = getCookie('tokenUsuario');

    var data = {
      inicio : calculaInicio(numeroPagina, tamanhoPaginaPersona),
      tamanhoPagina : tamanhoPaginaPersona
    }
    
    $.ajax({
      method: "POST",
      url: urlPeticionAjaxListarTodasPersonas,
      contentType : "application/json",
      data: JSON.stringify(data),  
      dataType : 'json',
      headers: {'Authorization': token},
      }).done(res => {
        if (res.code != 'PERSONAS_LISTADAS') {
          reject(res);
        }
        resolve(res);
      }).fail( function( jqXHR ) {
        errorFailAjax(jqXHR.status);
      });
  });
}


function cargaDatosPersona(datos){
	$('#cardPersona').html('');

     var fecha = (datos[0].fechaNacP).split('T');

     var cardPersona = '<img class="card-img-top" src="images/users.png" alt="Card image" style="width:100%">' + 
     						'<div class="card-body">' + 
     							'<div class="nombreApellidosInfo">' + 
     								'<img class="nombreApellidosImg" src="images/users.png" alt="nombreApellidos">' + 
     									'<h4 class="card-title nombreApellidos">' + datos[0].nombreP + " " + datos[0].apellidosP + '</h4>' +
     							'</div>' + 
     							'<div class="dniPInfo">' +
     								'<img class="dniPImg" src="images/dni.png" alt="dniP">' + 
     								'<p class="card-text dniP">' +  datos[0].dniP + '</p>' + 
     							'</div>' + 
     							'<div class="fechaNacimientoInfo">' + 
     								'<img class="fechaNacimientoImg" src="images/cumpleanhos.png" alt="fechaNacimiento">' + 
     								'<p class="card-text fechaNacimiento">'+ fecha[0] + '</p>' + 
     							'</div>' + 
     							'<div class="direccionInfo">' + 
     								'<img class="direccionImg" src="images/direccion.png" alt="direccion">' + 
     								'<p class="card-text direccion">' + datos[0].direccionP + '</p>' + 
     							'</div>' + 
     							'<div class="telefonoInfo">' + 
     								'<img class="telefonoImg" src="images/telefono.png" alt="telefono">' + 
     								'<p class="card-text telefono">' + datos[0].telefonoP + '</p>' + 
     							'</div>' +
     							'<div class="emailInfo">' + 
     								'<img class="emailImg" src="images/email.png" alt="email">' + 
     								'<p class="card-text email">' + datos[0].emailP + '</p>' + 
     							'</div>' + 
     							'<div class="tooltip">' + 
     								'<img class="editar editarPermiso" src="images/edit.png" data-toggle="" data-target="" onclick="" alt="Editar"/>' + 
     								'<span class="tooltiptext iconEditUser ICONO_EDIT">Editar</span>' + 
     							'</div>' + 
     						'</div>';

     	$('#cardPersona').append(cardPersona);
}

function cargaDatosUsuario(datos){
	$('#cardUsuario').html('');

     var cardUsuario = '<img class="card-img-top" src="images/user.png" alt="Card image">' + 
     						'<div class="card-body">' + 
     							'<div class="userInfo">' + 
     								'<img class="userImg" src="images/user.png" alt="usuario">' + 
     								'<h4 class="card-title user">' + datos[0].usuario.usuario + '</h4>' + 
     							'</div>' + 
     							'<div class="dniInfo">' + 
     								'<img class="dniImg" src="images/dni.png" alt="dni">' + 
     								'<p class="card-text dni">' + datos[0].usuario.dniUsuario + '</p>' + 
     							'</div>' +
     							'<div class="passInfo">' + 
     								'<img class="passImg" src="images/pass.png" alt="pass">' + 
     								'<p class="card-text pass">************</p>' + 
     							'</div>' + 
     							'<div class="rolInfo">' + 
     								'<img class="rolImg" src="images/rol.png" alt="rol">'  +
     								'<p class="card-text rol">' + datos[0].usuario.rol.rolName + '</p>' +
     							'<div>' + 
     						'<div>';


     $('#cardUsuario').append(cardUsuario);
}

function cargaDatosEmpresa(datos){
	 $('#cardEmpresa').html('');

     var cardEmpresa = '<img class="card-img-top" src="images/empresa2.png" alt="Card image">' + 
     						'<div class="card-body">' + 
     							'<div class="nombreEInfo">' + 
     								'<img class="nombreEImg" src="images/empresa2.png" alt="nombreE">' + 
     								'<h4 class="card-title nombreE">' + datos[0].empresa.nombreEmpresa + '</h4>' + 
     							'</div>' + 
     							'<div class="cifInfo">' + 
     								'<img class="cifImg" src="images/cif.png" alt="cif">' + 
     								'<p class="card-text cif">' + datos[0].empresa.cifEmpresa + '</p>' + 
     							'</div>' + 
     							'<div class="direccionEInfo">' + 
     								'<img class="direccionEImg" src="images/direccion.png" alt="direccionE">' + 
     								'<p class="card-text direccionE">' + datos[0].empresa.direccionEmpresa + '</p>' + 
     							'</div>' +
     							'<div class="telefonoEInfo">' +
     								'<img class="telefonoEImg" src="images/telefono.png" alt="telefonoE">' + 
     								'<p class="card-text telefonoE">' + datos[0].empresa.telefonoEmpresa + '</p>' +
     							'</div>' + 
     							'<div class="tooltip">' + 
     								'<img class="editar editarPermiso" src="images/edit.png" data-toggle="" data-target="" onclick="" alt="Editar"/>' + 
     								'<span class="tooltiptext iconEditUser ICONO_EDIT">Editar</span>' + 
     							'</div>' + 
     						'</div>';


     $('#cardEmpresa').append(cardEmpresa);
}











/**Función para cambiar las banderas de idiomas*/
function cargarIdioma(idioma){
	if (idioma == "spanish"){
		document.getElementById("imagenIdioma").src = "images/Spain.png";
		setLang('ES');
	} else if (idioma == "english"){
		document.getElementById("imagenIdioma").src = "images/United-Kingdom.png";
		setLang('EN');
	} else if (idioma == "galego"){
		document.getElementById("imagenIdioma").src = "images/Galicia.png";
		setLang('GA');
	}
}

/**Función para incluir el footer*/
function includeFooter() {
	$("#footer").html("");

	var footer = '<footer class="fixed-bottom page-footer font-small footer">' +
				'<div class="footer-copyright text-center py-3">© 2021 Copyright:' +
				'<a href="#"> Sustainable Development Society</a>' +
				'</div>' +
				'</footer>' ;

	$("#footer").append(footer);
}

/**Función para incluir el menú superior*/
function includeTopMenu() {
	$("#topMenu").html("");

	var topMenu = '<nav class="fixed-top navbar navbar-expand-md navbar-dark menuIdioma">' + 
				'<a class="navbar-brand" href="#">' +
    			'<img src="images/iconoIndex.png" alt="Logo" class="imagenLogo">' +
  				'</a>' +
				'<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">' + 
				'<span class="navbar-toggler-icon"></span>' +
				'</button>' +
				'<div class="collapse navbar-collapse" id="collapsibleNavbar">' +
				'<ul class="navbar-nav">' +
				'<li class="nav-item dropdown">' +
				'<a class="nav-link dropdown-toggle" href="#" id="navbardrop" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
				'<img class="nav-link dropdown-toggle" id="imagenIdioma" src=""/>' +
				'</a>' +
				'<div class="dropdown-menu" aria-labelledby="navbarDropdown">' +
				'<a class="dropdown-item" href="#" onclick="cargarIdioma(\'spanish\');">' + 
				'<input type="submit" name="btnEspanol" id="btnEspanol" value="" onclick="cargarIdioma(\'spanish\');" />' +
				'</a>' +
				'<div class="dropdown-divider"></div>' +
				'<a class="dropdown-item" href="#" onclick="cargarIdioma(\'english\');">' +
				'<input type="submit" name="btnIngles" id="btnIngles" value="" onclick="cargarIdioma(\'english\');" />' +
				'</a>' +
				'<div class="dropdown-divider"></div>' +
				'<a class="dropdown-item" href="#" onclick="cargarIdioma(\'galego\');">' +
				'<input type="submit" name="btnGallego" id="btnGallego" value="" onclick="cargarIdioma(\'galego\');" />' +
				'</a>' +
				'</div>' +
				'</li>' +
				'<li class="nav-item dropdown">' +
				'<a class="nav-link dropdown-toggle" href="#" id="navbardrop3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
				'<img id="imagenHome" src="images/home.png"/>' +
				'<div class="home">Menú</div>' +
				'</a>' + 
				'<div class="dropdown-menu" id="listadoFuncionalidades">' +
				'</div>' +
				'</li>' +	
				'<li class="nav-item dropdown">' +					
				'<a class="nav-link dropdown-toggle" href="#" id="navbardrop2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
				'<img id="imagenUsuario" src="images/usuario.png"/>' +
				'<div class="usuarioConectado">' + getCookie('usuario') + '</div>' +
				'</a>' +
				'<div class="dropdown-menu">' +
				'<a class="dropdown-item" href="#" data-toggle="modal" data-target="#changePass-modal" onclik="javascript:modalCambioPass()">Cambiar Contraseña</a>' +
				'<div class="dropdown-divider"></div>' +
				'<a class="dropdown-item" href="index.html" onclick="javascript:desconectar()">Desconectar</a>' +
				'</div>' +
				'</li>' +
				'</ul>' +
				'</div>' +
				'</nav>';

	$("#topMenu").append(topMenu);

}

function modalCambioPass(){
	
	$("#changePass-modal").html("");

	var contenidoModal = 
    	'<div class="modal-dialog">' +
			'<div class="changePassmodal-container">' +
				'<h1 class="CAMBIAR_CONTRASEÑA"></h1><br>' +
				'<form name="formularioChangePass" id="formularioChangePass" action="javascript:changePass()" onsubmit="return comprobarChangePass()">' +
					'<input type="password" name="PASS_USUARIO_NUEVA" class="PASS_USUARIO_NUEVA" maxlength="45" size="45" id="passChangePass1" placeholder="Usuario" placeholder="Contraseña" onKeyPress="capLock(event,\'bloqueoMayusculasChangePass\');" onblur="return comprobarPass(\'passChangePass1\', \'errorFormatoChangePass1\', \'passwordChange\')";>' +
					'<div style="display:none" id="errorFormatoChangePass1"></div>' +
					'<input type="password" name="CONFIRMAR_PASS_USUARIO" class="CONFIRMAR_PASS_USUARIO" id="passChangePass2" maxlength="45" size="45" placeholder="Contraseña" onKeyPress="capLock(event,\'bloqueoMayusculasChangePass\');" onblur="return comprobarPass(\'passChangePass2\', \'errorFormatoChangePass2\', \'passwordChange\')">' +
					'<div style="display:none" class="BLOQUEO_MAYUSCULAS alert alert-warning" id="bloqueoMayusculasChangePass"></div>' +  
          			'<div style="display:none" id="errorFormatoChangePass2" class="alert alert-danger ocultar"></div>' +  
					'<div id="error" class="alert alert-danger ocultar" role="alert" class="CONTRASEÑAS_NO_COINCIDEN"></div>' +
					'<button type="submit" name="btnChangePass" value="Cambiar contraseña" class="btnChangePass tooltip3">' +
                        '<img class=iconoResetPass iconResetPass" src="images/resetPass.png" alt="CAMBIAR_CONTRASEÑA" />' +
                        '<span class="tooltiptext3 ICONO_RESET_PASS"></span>' +
                	'</button>' +

				'</form>' +	

			'</div>' +
		'</div>';


    $("#changePass-modal").append(contenidoModal);

}

/**Función para ordenar una tabla por columna*/
function sortTable(n , idTable) {

	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;

  	table = document.getElementById(idTable);
  	switching = true;

  	dir = "asc"; 

	while (switching) {

    	switching = false;
    	rows = table.rows;

    	for (i = 1; i < (rows.length - 1); i++) {
      		
      		shouldSwitch = false;
      		
      		x = rows[i].getElementsByTagName("TD")[n];
      		y = rows[i + 1].getElementsByTagName("TD")[n];
      
      		if (dir == "asc") {
        		if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          		
          			shouldSwitch= true;
         			break;
        		}
      		} else if (dir == "desc") {
        		
        		if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {

          			shouldSwitch = true;
          			break;
        		}
      		}
    	}
    
    	if (shouldSwitch) {
      		
      		rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      		switching = true;
      
      		switchcount ++;      
    	
    	} else {

      		if (switchcount == 0 && dir == "asc") {
        		dir = "desc";
        		switching = true;
      		}
    	}
  	}
 }

/*Función para establecer el valor de la cookie*/
function setCookie(name, value, days) {

    var expires = "";

    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }

    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}

/*Función para obtener el valor de la cookie*/
function getCookie(name) {

    var nameEQ = name + "=";
    var ca = document.cookie.split(';');

    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }

    return null;

}
 
function capLock(e, idElemento){
    kc=e.keyCode?e.keyCode:e.which;
    sk=e.shiftKey?e.shiftKey:((kc==16)?true:false);
    if(((kc>=65&&kc<=90)&&!sk)||((kc>=97&&kc<=122)&&sk )){
        document.getElementById(idElemento).style.display = "block";
    } else {
        document.getElementById(idElemento).style.display = "none";
    }
}

/**Función que resetear los valores del formulario*/
function resetearFormulario(idFormulario, idElementoList) {

	document.getElementById(idFormulario).reset();

	idElementoList.forEach( function (idElemento) {
		document.getElementById(idElemento).style.borderColor = "#c8c8c8";
	});	

}

/**Función para cerrar las ventanas modales*/
function cerrarModal(idElement){
	document.getElementById(idElement).style.display = "none";
}

/**Función para cerrar las ventanas modales*/
function cerrarModalNoToken(idElement){
	document.getElementById(idElement).style.display = "none";
	window.location.href = "index.html";
}        

/**Función para desconectar la aplicación */
function desconectar(){
	setCookie('tokenUsuario', '');
	setCookie('usuario', '');
	setCookie('rolUsuario', '');
	setCookie('lang', '');
}

/**Función para eliminar los mensajes de validación de error*/
function eliminarMensajesValidacionError(idElementoErrorList, idElementoList){
	
	idElementoErrorList.forEach( function (idElementoError) {
		$("#"+idElementoError).removeClass();
		$("#"+idElementoError).html('');
		$("#"+idElementoError).css("display", "none");
	});	
	
	idElementoList.forEach( function (idElemento){
		$("#"+idElemento).removeAttr("style");
		$("#"+idElemento).css("border", "1px solid #D1C4E9");
		$("#"+idElemento).css("borderTop",  "2px solid #B39DDB");
	});
}

/**Función para limpiar los campos de un formulario*/
function limpiarFormulario(idElementoList){
	
	idElementoList.forEach( function (idElemento){
		$("#"+idElemento).val('');
	});
}

/**Función para encriptar la pass en md5*/
function encriptar(idElemento){
	document.getElementById(idElemento).value = hex_md5(document.getElementById(idElemento).value);
  	return true;

}

/**Función que va a rellena los select **/
function rellenaSelect(select, datos){

	var lista = datos;

	for(var i = 0; i<lista.length; i++){
		var option = document.createElement("option");
		option.setAttribute("value", datos[i].idEmpresa);
		option.setAttribute("label", datos[i].nombreEmpresa);

		select.append(option);
	}

	var option = document.createElement("option");
	option.setAttribute("value", "default");
	option.setAttribute("label", " --- Añadir nueva empresa --- ");

	select.append(option);

}

/**Función que va a eliminar los options de los select **/
function limpiaSelect(select){

	select.empty();
}

/**Función para comprobar que el token del usuario se encuentra en las cookies**/
function comprobarTokenUsuario(){
	var token = getCookie('tokenUsuario');
	var idioma = getCookie('lang');

	if(token === null || token === ""){
		errorAutenticado("ERROR_AUTENTICACION", idioma);
	}else{
		return true;
		
	}
}

/*Función que muestra el error de acceso por no estar autenticado**/
function errorAutenticado(codigoResponse, idioma){
	$("#modal-title").removeClass();
    $("#modal-title").addClass("STOP");
	document.getElementById("modal-title").style.color = "#a50707";
	$("#mensajeError").removeClass();
    $("#mensajeError").addClass(codigoResponse);   
    $(".imagenAviso").attr('src', "images/stop.png");   
    setLang(idioma);
    document.getElementById("modal").style.display = "block";
}

/**Función que construye cada línea que se va a rellenar en la tabla*/
function construyeFila(entidad, fila) {
	let atributosFunciones="";
	var filaTabla = "";

	var celdaAccionesDetalle = '<div class="tooltip"><img class="detalle" src="images/detail3.png" onclick="showDetalleUsuario(' + atributosFunciones + 
                               ')" alt="Detalle Usuario"/><span class="tooltiptext iconDetailUser">Detalle</span></div>';
    var celdaAccionesEditar = '<div class="tooltip"><img class="editar" src="images/edit3.png" onclick="showEditarUsuario(' + atributosFunciones + 
                               ')" alt="Editar Usuario"/><span class="tooltiptext iconEditUser">Editar</span></div>';
    var celdaAccionesEliminar = '<div class="tooltip"><img class="eliminar" src="images/delete3.png" onclick="showEliminarUsuario(' + atributosFunciones + 
                               ')" alt="Eliminar Usuario"/><span class="tooltiptext iconDeleteUser">Eliminar</span></div>';


	var celdaAcciones = celdaAccionesDetalle + celdaAccionesEditar + celdaAccionesEliminar;

	switch(entidad){
		case 'ROL':
			atributosFunciones = ["'" + fila.rolName + "'", "'" + fila.rolDescription + "'"];
			filaTabla = '<tr class="impar"> <td>' + fila.rolName + 
                '</td> <td>' + fila.rolDescription + 
                '</td> <td class="acciones">' + celdaAcciones +  
                '</td> </tr>';
        break;
	}

    return filaTabla;
}

/**Función que crea según las columnas que le pasemos un div con checkbox para marcar y así ocultar las columnas*/
function createHideShowColumnsWindow(arrayColumnas) {
    var checkbox = "";

    for (var clave in arrayColumnas){
        checkbox = checkbox + "<input type='checkbox' id='" + clave + 
        "' name='" + clave + "' value='" + clave + "' onclick='hideShow(" + clave + ", " + arrayColumnas[clave] + 
        ")'><label for='" + clave.replace('_COLUMN', '') + "'></label><br>";
    }
    return checkbox;
 
}

/**Función para cargar los HREF de cada pagina*/
function cargarHref(dato){
	var href=""

	switch(dato){
		case 'Gestión de roles':
			href="GestionDeRoles.html";
		break;
	}

	return href;
}

/**Función para cargar los class de cada pagina **/

function cargarClass(dato){
	var clase=""

	switch(dato){
		case 'Gestión de roles':
			clase="GESTION_ROLES";
		break;
	}

	return clase;
}


$(document).ready(function(){
  $('.iconCerrar').on('click', function(){
    if($("#modal-title").attr('class') === "STOP" ){
    	cerrarModalNoToken('modal');
    }else{
    	cerrarModal('modal');
    }
  })
})
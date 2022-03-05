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
				'<div class="dropdown-menu">' +
				'<a class="dropdown-item" href="#">Gestión Usuarios</a>' +
				'<div class="dropdown-divider"></div>' +
				'<a class="dropdown-item" href="Index.html">Gestión Personas</a>' +
				'<div class="dropdown-divider"></div>' +
				'<a class="dropdown-item" href="Index.html">Gestión Empresas</a>' +
				'<div class="dropdown-divider"></div>' +
				'<a class="dropdown-item" href="Index.html">Gestión Roles</a>' +
				'<div class="dropdown-divider"></div>' +
				'<a class="dropdown-item" href="Index.html">Gestión Acciones</a>' +
				'<div class="dropdown-divider"></div>' +
				'<a class="dropdown-item" href="Index.html">Gestión Funcionalidades</a>' +
				'<div class="dropdown-divider"></div>' +
				'<a class="dropdown-item" href="Index.html">Test</a>' +
				'<div class="dropdown-divider"></div>' +
				'<a class="dropdown-item" href="Index.html">Logs</a>' +
				'</div>' +
				'</li>' +
				'<li class="nav-item dropdown">' +
				'<a class="nav-link dropdown-toggle" href="#" id="navbardrop2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
				'<img id="imagenUsuario" src="images/usuario.png"/>' +
				'<div class="usuarioConectado">admin</div>' +
				'</a>' +
				'<div class="dropdown-menu">' +
				'<a class="dropdown-item" href="#">Cambiar Contraseña</a>' +
				'<div class="dropdown-divider"></div>' +
				'<a class="dropdown-item" href="index.html" onclick="javascript:desconectar()">Desconectar</a>' +
				'</div>' +
				'</li>' +
				'</ul>' +
				'</div>' +
				'</nav>';

	$("#topMenu").append(topMenu);
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
 
function capLock(e){
    kc=e.keyCode?e.keyCode:e.which;
    sk=e.shiftKey?e.shiftKey:((kc==16)?true:false);
    if(((kc>=65&&kc<=90)&&!sk)||((kc>=97&&kc<=122)&&sk )){
        document.getElementById("bloqueoMayusculas").style.display = "block";
    } else {
        document.getElementById("bloqueoMayusculas").style.display = "none";
    }
}

/**Función que resetear los valores del formulario*/
function resetearFormulario(idFormulario, idElementoList) {

	document.getElementById(idFormulario).reset();

	idElementoList.forEach( function (idElemento) {
		document.getElementById(idElemento).style.borderColor = "#c8c8c8";
	});	

}

function cerrarModal(){
	$('#modal').modal('toggle'); 
}       

function desconectar(){
	setCookie('tokenUsuario', '');
	setCookie('usuario', '');
	setCookie('rolUsuario', '');
	setCookie('lang', '');
}


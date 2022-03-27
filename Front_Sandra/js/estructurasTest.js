/*Función para cargar las opciones de Tests de Login*/
function cargarTestLogin(){

	$("#testLogin").html("");

	var contenidoTest = '<div id="accordion2">' +
	      				'<div class="card">' +
					    '<div class="card-header">' +
					    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseLoginAtributos">' +
				        'Atributos ' +
				      	'</a>' +
					    '</div>' +
					    '<div id="collapseLoginAtributos" class="collapse" data-parent="#accordion2">' +
				      	'<div class="card-body">' +				        
				      	'<div id="accordion3">' +
			      		'<div class="card">' +
					    '<div class="card-header">' +
				      	'<a class="collapsed card-link" data-toggle="collapse" href="#collapseLoginAtributosLogin" onclick="javascript:testAtributosLogin()">' +
				        'Login' +
				      	'</a>' +
					    '</div>' +
					    '<div id="collapseLoginAtributosLogin" class="collapse" data-parent="#accordion3">' +
					    '<div class="card-body">' +
				        '<div class="table-responsive">' +
					    '<table class="table table-bordered">' +
					    '<thead class="cabeceraTablasTest" id="cabeceraAtributosLogin"></thead>' +
					    '<tbody id="cuerpoAtributosLogin"></tbody>' +
					    '</table>' +
      					'</div>' +
					    '</div>' +
					    '</div>' +
					  	'</div>' +
					  	'<div class="card">' +
					    '<div class="card-header">' +
					    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseLoginAtributosRecuperarPass" onclick="javascript:testAtributosRecuperarPass()">' +
					    'Recuperar Contraseña' +
					    '</a>' +
					    '</div>' +
					    '<div id="collapseLoginAtributosRecuperarPass" class="collapse" data-parent="#accordion3">' +
					    '<div class="card-body">' +
				      	'<div class="table-responsive">' +
					    '<table class="table table-bordered">' +
					    '<thead class="cabeceraTablasTest" id="cabeceraAtributosRecuperarPass"></thead>' +
					    '<tbody id="cuerpoAtributosRecuperarPass"></tbody>' +
					    '</table>' +
      					'</div>' +
					    '</div>' +
					    '</div>' +
						'</div>' +
					    '</div>' +
					   	'</div>' +
					  	'</div>' +
					  	'<div class="card-header">' +
					    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseLoginAcciones" onclick="javascript:testAccionesLogin()">' +
					    'Acciones' +
					    '</a>' +
					    '</div>' +
					    '<div id="collapseLoginAcciones" class="collapse" data-parent="#accordion2">' +
					    '<div class="card-body">' +
					    '<div class="table-responsive">' +
						'<table class="table table-bordered">' +
						'<thead class="cabeceraTablasTest" id="cabeceraAccionesLogin"></thead>' +
						'<tbody id="cuerpoAccionesLogin"></tbody>' +
						'</table>' +
	      				'</div>' +
					    '</div>' +
					    '</div>' +
					 	'</div>' +
						'</div>';

	$("#testLogin").append(contenidoTest);
}
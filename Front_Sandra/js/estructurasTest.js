/*Función para cargar las opciones de Tests de Login*/
function cargarTestLogin(){

	$("#testLogin").html("");

	var contenidoTest = '<div id="accordion2">' +
	      					'<div class="card">' +
							    '<div class="card-header">' +
							    	'<a class="collapsed card-link" data-toggle="collapse" href="#collapseLoginAtributos">' +
							        	' Atributos ' +
							      	'</a>' +
							      	'<img class="iconTab" id="iconoTestLoginAtributos" src="images/failed.png" hidden>' +
							    '</div>' +

							    '<div id="collapseLoginAtributos" class="collapse" data-parent="#accordion2">' +
							    	'<div class="card-body">' +
							        
								      	'<div id="accordion3">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseLoginAtributosLogin" onclick="javascript:testAtributosLogin()">' +
												        ' Login ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestLoginAtributosLogin" src="images/failed.png" hidden>' +
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
												        ' Recuperar Contraseña ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestLoginAtributosRecuperarPass" src="images/failed.png" hidden>' +
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
					      			'<a class="collapsed card-link" data-toggle="collapse" href="#collapseLoginAcciones">' +
					        			' Acciones ' +
					      			'</a>' +
					      			'<img class="iconTab" id="iconoTestLoginAcciones" src="images/failed.png" hidden>' +
					    		'</div>' +

					    		'<div id="collapseLoginAcciones" class="collapse" data-parent="#accordion2">' +
							    	'<div class="card-body">' +
							        
								      	'<div id="accordion4">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseLoginAccionesLogin" onclick="javascript:testAccionesLogin()">' +
												        ' Login ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestLoginAccionesLogin" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseLoginAccionesLogin" class="collapse" data-parent="#accordion4">' +
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

											'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseLoginAccionesRecuperarPass" onclick="javascript:testAccionesRecuperarPass()">' +
												        ' Recuperar Contraseña ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestLoginAccionesRecuperarPass" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseLoginAccionesRecuperarPass" class="collapse" data-parent="#accordion4">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesRecuperarPass"></thead>' +
															   '<tbody id="cuerpoAccionesRecuperarPass"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +
								      	'</div>' +

							    	'</div>' +
								'</div>' +
					 		' </div>' +
						'</div>';

	$("#testLogin").append(contenidoTest);
}

/*Función para cargar las opciones de Tests de Registrar*/
function cargarTestRegistrar(){

	$("#testRegistrar").html("");

	var contenidoTest = '<div id="accordion5">' +
	      					'<div class="card">' +
							    '<div class="card-header">' +
							    	'<a class="collapsed card-link" data-toggle="collapse" href="#collapseRegistrarAtributos" onclick="javascript:testAtributosRegistrar()">' +
							        	' Atributos ' +
							      	'</a>' +
							      	'<img class="iconTab" id="iconoTestRegistrarAtributos" src="images/failed.png" hidden>' +
							    '</div>' +

							    '<div id="collapseRegistrarAtributos" class="collapse" data-parent="#accordion5">' +
							    	'<div class="card-body">' +							       
								        '<div class="table-responsive">' +
											'<table class="table table-bordered">' +
											    '<thead class="cabeceraTablasTest" id="cabeceraAtributosRegistrar"></thead>' +
											    '<tbody id="cuerpoAtributosRegistrar"></tbody>' +
											'</table>' +
				      					'</div>' +
							    	'</div>' +
								'</div>' +

					  			'<div class="card-header">' +
					      			'<a class="collapsed card-link" data-toggle="collapse" href="#collapseRegistrarAcciones" onclick="javascript:testAccionesRegistrar()">' +
					        			' Acciones ' +
					      			'</a>' +
					      			'<img class="iconTab" id="iconoTestRegistrarAcciones" src="images/failed.png" hidden>' +
					    		'</div>' +

					    		'<div id="collapseRegistrarAcciones" class="collapse" data-parent="#accordion5">' +
							    	'<div class="card-body">' +								      	
								        '<div class="table-responsive">' +
											'<table class="table table-bordered">' +
											    '<thead class="cabeceraTablasTest" id="cabeceraAccionesRegistrar"></thead>' +
											    '<tbody id="cuerpoAccionesRegistrar"></tbody>' +
											'</table>' +
				      					'</div>' +								
							    	'</div>' +
								'</div>' +
					 		' </div>' +
						'</div>';

	$("#testRegistrar").append(contenidoTest);
}

/*Función para cargar las opciones de Tests de Roles*/
function cargarTestGestionRoles(){

	$("#testRol").html("");

	var contenidoTest = '<div id="accordion6">' +
	      					'<div class="card">' +
							    '<div class="card-header">' +
							    	'<a class="collapsed card-link" data-toggle="collapse" href="#collapseRolAtributos">' +
							        	' Atributos ' +
							      	'</a>' +
							      	'<img class="iconTab" id="iconoTestRolAtributos" src="images/failed.png" hidden>' +
							    '</div>' +

							    '<div id="collapseRolAtributos" class="collapse" data-parent="#accordion6">' +
							    	'<div class="card-body">' +
							        
								      	'<div id="accordion7">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseRolAtributosGuardar" onclick="javascript:testAtributosRolGuardar()">' +
												        ' Añadir Rol ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestRolAtributosGuardar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseRolAtributosGuardar" class="collapse" data-parent="#accordion7">' +
												    '<div class="card-body">' +
												        '<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															    '<thead class="cabeceraTablasTest" id="cabeceraAtributosRolGuardar"></thead>' +
															    '<tbody id="cuerpoAtributosRolGuardar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
											'</div>' +

											'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseRolAtributoBuscar" onclick="javascript:testAtributosRolBuscar()">' +
												        ' Buscar Rol ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestRolAtributosBuscar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseRolAtributoBuscar" class="collapse" data-parent="#accordion7">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAtributosRolBuscar"></thead>' +
															   '<tbody id="cuerpoAtributosRolBuscar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseRolAtributoModificar" onclick="javascript:testAtributosRolModificar()">' +
												        ' Modificar Rol ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestRolAtributosModificar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseRolAtributoModificar" class="collapse" data-parent="#accordion7">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAtributosRolModificar"></thead>' +
															   '<tbody id="cuerpoAtributosRolModificar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +
								      	'</div>' +

							    	'</div>' +
								'</div>' +

					  			'<div class="card-header">' +
					      			'<a class="collapsed card-link" data-toggle="collapse" href="#collapseRolAcciones">' +
					        			' Acciones ' +
					      			'</a>' +
					      			'<img class="iconTab" id="iconoTestRolAcciones" src="images/failed.png" hidden>' +
					    		'</div>' +

					    		'<div id="collapseRolAcciones" class="collapse" data-parent="#accordion6">' +
							    	'<div class="card-body">' +
							        
								      	'<div id="accordion8">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseRolAccionesGuardar" onclick="javascript:testAccionesRolGuardar()">' +
												        ' Añadir Rol ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestRolAccionesGuardar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseRolAccionesGuardar" class="collapse" data-parent="#accordion8">' +
												    '<div class="card-body">' +
												        '<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															    '<thead class="cabeceraTablasTest" id="cabeceraAccionesRolGuardar"></thead>' +
															    '<tbody id="cuerpoAccionesRolGuardar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
											'</div>' +

											'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseRolAccionesEliminar" onclick="javascript:testAccionesRolEliminar()">' +
												        ' Eliminar Rol ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestRolAccionesEliminar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseRolAccionesEliminar" class="collapse" data-parent="#accordion8">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesRolEliminar"></thead>' +
															   '<tbody id="cuerpoAccionesRolEliminar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseRolAccionesModificar" onclick="javascript:testAccionesRolModificar()">' +
												        ' Modificar Rol ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestRolAccionesModificar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseRolAccionesModificar" class="collapse" data-parent="#accordion8">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesRolModificar"></thead>' +
															   '<tbody id="cuerpoAccionesRolModificar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseRolAccionesBuscar" onclick="javascript:testAccionesRolBuscar()">' +
												        ' Buscar Rol ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestRolAccionesBuscar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseRolAccionesBuscar" class="collapse" data-parent="#accordion8">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesRolBuscar"></thead>' +
															   '<tbody id="cuerpoAccionesRolBuscar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +
								      	'</div>' +

							    	'</div>' +
								'</div>' +
					 		' </div>' +
						'</div>';

	$("#testRol").append(contenidoTest);
}

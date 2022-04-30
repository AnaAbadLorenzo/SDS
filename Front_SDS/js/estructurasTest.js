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

								      	'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseRolAccionesReactivar" onclick="javascript:testAccionesRolReactivar()">' +
												        ' Reactivar Rol ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestRolAccionesReactivar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseRolAccionesReactivar" class="collapse" data-parent="#accordion8">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesRolReactivar"></thead>' +
															   '<tbody id="cuerpoAccionesRolReactivar"></tbody>' +
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


/*Función para cargar las opciones de Tests de Funcionalidades*/
function cargarTestGestionFuncionalidades(){

	$("#testFuncionalidad").html("");

	var contenidoTest = '<div id="accordion9">' +
	      					'<div class="card">' +
							    '<div class="card-header">' +
							    	'<a class="collapsed card-link" data-toggle="collapse" href="#collapseFuncionalidadAtributos">' +
							        	' Atributos ' +
							      	'</a>' +
							      	'<img class="iconTab" id="iconoTestFuncionalidadAtributos" src="images/failed.png" hidden>' +
							    '</div>' +

							    '<div id="collapseFuncionalidadAtributos" class="collapse" data-parent="#accordion9">' +
							    	'<div class="card-body">' +
							        
								      	'<div id="accordion10">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseFuncionalidadAtributosGuardar" onclick="javascript:testAtributosFuncionalidadGuardar()">' +
												        ' Añadir Funcionalidad ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestFuncionalidadAtributosGuardar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseFuncionalidadAtributosGuardar" class="collapse" data-parent="#accordion10">' +
												    '<div class="card-body">' +
												        '<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															    '<thead class="cabeceraTablasTest" id="cabeceraAtributosFuncionalidadGuardar"></thead>' +
															    '<tbody id="cuerpoAtributosFuncionalidadGuardar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
											'</div>' +

											'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseFuncionalidadAtributoBuscar" onclick="javascript:testAtributosFuncionalidadBuscar()">' +
												        ' Buscar Funcionalidad ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestFuncionalidadAtributosBuscar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseFuncionalidadAtributoBuscar" class="collapse" data-parent="#accordion10">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAtributosFuncionalidadBuscar"></thead>' +
															   '<tbody id="cuerpoAtributosFuncionalidadBuscar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseFuncionalidadAtributoModificar" onclick="javascript:testAtributosFuncionalidadModificar()">' +
												        ' Modificar Funcionalidad ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestFuncionalidadAtributosModificar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseFuncionalidadAtributoModificar" class="collapse" data-parent="#accordion10">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAtributosFuncionalidadModificar"></thead>' +
															   '<tbody id="cuerpoAtributosFuncionalidadModificar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +
								      	'</div>' +

							    	'</div>' +
								'</div>' +

					  			'<div class="card-header">' +
					      			'<a class="collapsed card-link" data-toggle="collapse" href="#collapseFuncionalidadAcciones">' +
					        			' Acciones ' +
					      			'</a>' +
					      			'<img class="iconTab" id="iconoTestFuncionalidadAcciones" src="images/failed.png" hidden>' +
					    		'</div>' +

					    		'<div id="collapseFuncionalidadAcciones" class="collapse" data-parent="#accordion9">' +
							    	'<div class="card-body">' +
							        
								      	'<div id="accordion11">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseFuncionalidadAccionesGuardar" onclick="javascript:testAccionesFuncionalidadGuardar()">' +
												        ' Añadir Funcionalidad ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestFuncionalidadAccionesGuardar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseFuncionalidadAccionesGuardar" class="collapse" data-parent="#accordion11">' +
												    '<div class="card-body">' +
												        '<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															    '<thead class="cabeceraTablasTest" id="cabeceraAccionesFuncionalidadGuardar"></thead>' +
															    '<tbody id="cuerpoAccionesFuncionalidadGuardar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
											'</div>' +

											'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseFuncionalidadAccionesEliminar" onclick="javascript:testAccionesFuncionalidadEliminar()">' +
												        ' Eliminar Funcionalidad ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestFuncionalidadAccionesEliminar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseFuncionalidadAccionesEliminar" class="collapse" data-parent="#accordion11">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesFuncionalidadEliminar"></thead>' +
															   '<tbody id="cuerpoAccionesFuncionalidadEliminar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseFuncionalidadAccionesModificar" onclick="javascript:testAccionesFuncionalidadModificar()">' +
												        ' Modificar Funcionalidad ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestFuncionalidadAccionesModificar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseFuncionalidadAccionesModificar" class="collapse" data-parent="#accordion11">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesFuncionalidadModificar"></thead>' +
															   '<tbody id="cuerpoAccionesFuncionalidadModificar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseFuncionalidadAccionesBuscar" onclick="javascript:testAccionesFuncionalidadBuscar()">' +
												        ' Buscar Funcionalidad ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestFuncionalidadAccionesBuscar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseFuncionalidadAccionesBuscar" class="collapse" data-parent="#accordion11">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesFuncionalidadBuscar"></thead>' +
															   '<tbody id="cuerpoAccionesFuncionalidadBuscar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +
								      	'</div>' +

								      	'<div class="card">' +
											'<div class="card-header">' +
												'<a class="collapsed card-link" data-toggle="collapse" href="#collapseFuncionalidadAccionesReactivar" onclick="javascript:testAccionesFuncionalidadReactivar()">' +
												    ' Reactivar Funcionalidad ' +
												'</a>' +
												'<img class="iconTab" id="iconoTestFuncionalidadAccionesReactivar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseFuncionalidadAccionesReactivar" class="collapse" data-parent="#accordion11">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesFuncionalidadReactivar"></thead>' +
															   '<tbody id="cuerpoAccionesFuncionalidadReactivar"></tbody>' +
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

	$("#testFuncionalidad").append(contenidoTest);
}

/*Función para cargar las opciones de Tests de Acciones*/
function cargarTestGestionAcciones(){

	$("#testAccion").html("");

	var contenidoTest = '<div id="accordion12">' +
	      					'<div class="card">' +
							    '<div class="card-header">' +
							    	'<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAtributos">' +
							        	' Atributos ' +
							      	'</a>' +
							      	'<img class="iconTab" id="iconoTestAccionAtributos" src="images/failed.png" hidden>' +
							    '</div>' +

							    '<div id="collapseAccionAtributos" class="collapse" data-parent="#accordion12">' +
							    	'<div class="card-body">' +
							        
								      	'<div id="accordion13">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAtributosGuardar" onclick="javascript:testAtributosAccionGuardar()">' +
												        ' Añadir Acción ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestAccionAtributosGuardar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseAccionAtributosGuardar" class="collapse" data-parent="#accordion13">' +
												    '<div class="card-body">' +
												        '<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															    '<thead class="cabeceraTablasTest" id="cabeceraAtributosAccionGuardar"></thead>' +
															    '<tbody id="cuerpoAtributosAccionGuardar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
											'</div>' +

											'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAtributoBuscar" onclick="javascript:testAtributosAccionBuscar()">' +
												        ' Buscar Acción ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestAccionAtributosBuscar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseAccionAtributoBuscar" class="collapse" data-parent="#accordion13">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAtributosAccionBuscar"></thead>' +
															   '<tbody id="cuerpoAtributosAccionBuscar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAtributoModificar" onclick="javascript:testAtributosAccionModificar()">' +
												        ' Modificar Acción ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestAccionAtributosModificar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseAccionAtributoModificar" class="collapse" data-parent="#accordion13">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAtributosAccionModificar"></thead>' +
															   '<tbody id="cuerpoAtributosAccionModificar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +
								      	'</div>' +

							    	'</div>' +
								'</div>' +

					  			'<div class="card-header">' +
					      			'<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAcciones">' +
					        			' Acciones ' +
					      			'</a>' +
					      			'<img class="iconTab" id="iconoTestAccionAcciones" src="images/failed.png" hidden>' +
					    		'</div>' +

					    		'<div id="collapseAccionAcciones" class="collapse" data-parent="#accordion12">' +
							    	'<div class="card-body">' +
							        
								      	'<div id="accordion14">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAccionesGuardar" onclick="javascript:testAccionesAccionGuardar()">' +
												        ' Añadir Accion ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestAccionAccionesGuardar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseAccionAccionesGuardar" class="collapse" data-parent="#accordion14">' +
												    '<div class="card-body">' +
												        '<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															    '<thead class="cabeceraTablasTest" id="cabeceraAccionesAccionGuardar"></thead>' +
															    '<tbody id="cuerpoAccionesAccionGuardar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
											'</div>' +

											'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAccionesModificar" onclick="javascript:testAccionesAccionModificar()">' +
												        ' Modificar Acción ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestAccionAccionesModificar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseAccionAccionesModificar" class="collapse" data-parent="#accordion14">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesAccionModificar"></thead>' +
															   '<tbody id="cuerpoAccionesAccionModificar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAccionesEliminar" onclick="javascript:testAccionesAccionEliminar()">' +
												        ' Eliminar Acción ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestAccionAccionesEliminar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseAccionAccionesEliminar" class="collapse" data-parent="#accordion14">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesAccionEliminar"></thead>' +
															   '<tbody id="cuerpoAccionesAccionEliminar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAccionesBuscar" onclick="javascript:testAccionesAccionBuscar()">' +
												        ' Buscar Acción ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestAccionAccionesBuscar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseAccionAccionesBuscar" class="collapse" data-parent="#accordion14">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesAccionBuscar"></thead>' +
															   '<tbody id="cuerpoAccionesAccionBuscar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

								      		'<div class="card">' +
												'<div class="card-header">' +
													'<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAccionesReactivar" onclick="javascript:testAccionesAccionReactivar()">' +
													    ' Reactivar Acción ' +
													'</a>' +
													'<img class="iconTab" id="iconoTestAccionAccionesReactivar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseAccionAccionesReactivar" class="collapse" data-parent="#accordion14">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesAccionReactivar"></thead>' +
															   '<tbody id="cuerpoAccionesAccionReactivar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

								      		'<div class="card">' +
												'<div class="card-header">' +
													'<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAccionesAsignar" onclick="javascript:testAccionesAccionAsignar()">' +
													    ' Asignar Acción ' +
													'</a>' +
													'<img class="iconTab" id="iconoTestAccionAccionesAsignar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseAccionAccionesAsignar" class="collapse" data-parent="#accordion14">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesAccionAsignar"></thead>' +
															   '<tbody id="cuerpoAccionesAccionAsignar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

								      		'<div class="card">' +
												'<div class="card-header">' +
													'<a class="collapsed card-link" data-toggle="collapse" href="#collapseAccionAccionesRevocar" onclick="javascript:testAccionesAccionRevocar()">' +
													    ' Revocar Acción ' +
													'</a>' +
													'<img class="iconTab" id="iconoTestAccionAccionesRevocar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseAccionAccionesRevocar" class="collapse" data-parent="#accordion14">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesAccionRevocar"></thead>' +
															   '<tbody id="cuerpoAccionesAccionRevocar"></tbody>' +
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

	$("#testAccion").append(contenidoTest);
}
/*Función para cargar las opciones de Tests de Usuario*/
function cargarTestGestionUsuarios(){

	$("#testUsuario").html("");

	var contenidoTest = '<div id="accordion15">' +
	      					'<div class="card">' +
							    '<div class="card-header">' +
							    	'<a class="collapsed card-link" data-toggle="collapse" href="#collapseUsuarioAtributos">' +
							        	' Atributos ' +
							      	'</a>' +
							      	'<img class="iconTab" id="iconoTestUsuarioAtributos" src="images/failed.png" hidden>' +
							    '</div>' +

							    '<div id="collapseUsuarioAtributos" class="collapse" data-parent="#accordion15">' +
							    	'<div class="card-body">' +

								      	'<div id="accordion16">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseUsuarioAtributosModificarRolUsuario" onclick="javascript:testAtributosUsuarioModificarRolUsuario()">' +
												        ' Modificar Rol Usuario ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestUsuarioAtributosModificarRolUsuario" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseUsuarioAtributosModificarRolUsuario" class="collapse" data-parent="#accordion16">' +
												    '<div class="card-body">' +
												        '<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															    '<thead class="cabeceraTablasTest" id="cabeceraAtributosUsuarioModificarRolUsuario"></thead>' +
															    '<tbody id="cuerpoAtributosUsuarioModificarRolUsuario"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
											'</div>' +

											'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseUsuarioAtributoBuscar" onclick="javascript:testAtributosUsuarioBuscar()">' +
												        ' Buscar Usuario ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestUsuarioAtributosBuscar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseUsuarioAtributoBuscar" class="collapse" data-parent="#accordion16">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAtributosUsuarioBuscar"></thead>' +
															   '<tbody id="cuerpoAtributosUsuarioBuscar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +
										'</div>' +
							    	'</div>' +
								'</div>' +

					  			'<div class="card-header">' +
					      			'<a class="collapsed card-link" data-toggle="collapse" href="#collapseUsuarioAcciones">' +
					        			' Acciones ' +
					      			'</a>' +
					      			'<img class="iconTab" id="iconoTestUsuarioAcciones" src="images/failed.png" hidden>' +
					    		'</div>' +

					    		'<div id="collapseUsuarioAcciones" class="collapse" data-parent="#accordion15">' +
							    	'<div class="card-body">' +

								      	'<div id="accordion17">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseUsuarioAccionesBuscar" onclick="javascript:testAccionesUsuarioBuscar()">' +
												        ' Buscar Usuario ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestUsuarioAccionesBuscar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseUsuarioAccionesBuscar" class="collapse" data-parent="#accordion17">' +
												    '<div class="card-body">' +
												        '<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															    '<thead class="cabeceraTablasTest" id="cabeceraAccionesUsuarioBuscar"></thead>' +
															    '<tbody id="cuerpoAccionesUsuarioBuscar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
											'</div>' +

											'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseUsuarioAccionesEliminar" onclick="javascript:testAccionesUsuarioEliminar()">' +
												        ' Eliminar Usuario ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestUsuarioAccionesEliminar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseUsuarioAccionesEliminar" class="collapse" data-parent="#accordion17">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesUsuarioEliminar"></thead>' +
															   '<tbody id="cuerpoAccionesUsuarioEliminar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseUsuarioAccionesModificarRolUsuario" onclick="javascript:testAccionesUsuarioModificarRolUsuario()">' +
												        ' Modificar Rol Usuario ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestUsuarioAccionesModificarRolUsuario" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseUsuarioAccionesModificarRolUsuario" class="collapse" data-parent="#accordion17">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesUsuarioModificarRolUsuario"></thead>' +
															   '<tbody id="cuerpoAccionesUsuarioModificarRolUsuario"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseUsuarioAccionesModificarPasswdUsuario" onclick="javascript:testAccionesUsuarioModificarPasswdUsuario()">' +
												        ' Modificar Contraseña Usuario ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestUsuarioAccionesModificarPasswdUsuario" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseUsuarioAccionesModificarPasswdUsuario" class="collapse" data-parent="#accordion17">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesUsuarioModificarPasswdUsuario"></thead>' +
															   '<tbody id="cuerpoAccionesUsuarioModificarPasswdUsuario"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +
								      	'</div>' +

								      	'<div class="card">' +
											'<div class="card-header">' +
												'<a class="collapsed card-link" data-toggle="collapse" href="#collapseUsuarioAccionesReactivar" onclick="javascript:testAccionesUsuarioReactivar()">' +
												    ' Reactivar Usuario ' +
												'</a>' +
												'<img class="iconTab" id="iconoTestUsuarioAccionesReactivar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseUsuarioAccionesReactivar" class="collapse" data-parent="#accordion17">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesUsuarioReactivar"></thead>' +
															   '<tbody id="cuerpoAccionesUsuarioReactivar"></tbody>' +
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

	$("#testUsuario").append(contenidoTest);
}

/*Función para cargar las opciones de Tests de Noticias*/
function cargarTestGestionNoticias(){

	$("#testNoticia").html("");

	var contenidoTest = '<div id="accordion18">' +
	      					'<div class="card">' +
							    '<div class="card-header">' +
							    	'<a class="collapsed card-link" data-toggle="collapse" href="#collapseNoticiaAtributos">' +
							        	' Atributos ' +
							      	'</a>' +
							      	'<img class="iconTab" id="iconoTestNoticiaAtributos" src="images/failed.png" hidden>' +
							    '</div>' +

							    '<div id="collapseNoticiaAtributos" class="collapse" data-parent="#accordion18">' +
							    	'<div class="card-body">' +

								      	'<div id="accordion19">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseNoticiaAtributosGuardar" onclick="javascript:testAtributosNoticiaGuardar()">' +
												        ' Añadir Noticia ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestNoticiaAtributosGuardar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseNoticiaAtributosGuardar" class="collapse" data-parent="#accordion19">' +
												    '<div class="card-body">' +
												        '<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															    '<thead class="cabeceraTablasTest" id="cabeceraAtributosNoticiaGuardar"></thead>' +
															    '<tbody id="cuerpoAtributosNoticiaGuardar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
											'</div>' +

											'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseNoticiaAtributoBuscar" onclick="javascript:testAtributosNoticiaBuscar()">' +
												        ' Buscar Noticia ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestNoticiaAtributosBuscar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseNoticiaAtributoBuscar" class="collapse" data-parent="#accordion19">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAtributosNoticiaBuscar"></thead>' +
															   '<tbody id="cuerpoAtributosNoticiaBuscar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseNoticiaAtributoModificar" onclick="javascript:testAtributosNoticiaModificar()">' +
												        ' Modificar Noticia ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestNoticiaAtributosModificar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseNoticiaAtributoModificar" class="collapse" data-parent="#accordion19">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAtributosNoticiaModificar"></thead>' +
															   '<tbody id="cuerpoAtributosNoticiaModificar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +
								      	'</div>' +

							    	'</div>' +
								'</div>' +

					  			'<div class="card-header">' +
					      			'<a class="collapsed card-link" data-toggle="collapse" href="#collapseNoticiaAcciones">' +
					        			' Acciones ' +
					      			'</a>' +
					      			'<img class="iconTab" id="iconoTestNoticiaAcciones" src="images/failed.png" hidden>' +
					    		'</div>' +

					    		'<div id="collapseNoticiaAcciones" class="collapse" data-parent="#accordion19">' +
							    	'<div class="card-body">' +

								      	'<div id="accordion20">' +
								      		'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseNoticiaAccionesGuardar" onclick="javascript:testAccionesNoticiaGuardar()">' +
												        ' Añadir Noticia ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestNoticiaAccionesGuardar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseNoticiaAccionesGuardar" class="collapse" data-parent="#accordion20">' +
												    '<div class="card-body">' +
												        '<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															    '<thead class="cabeceraTablasTest" id="cabeceraAccionesNoticiaGuardar"></thead>' +
															    '<tbody id="cuerpoAccionesNoticiaGuardar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
											'</div>' +

											'<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseNoticiaAccionesEliminar" onclick="javascript:testAccionesNoticiaEliminar()">' +
												        ' Eliminar Noticia ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestNoticiaAccionesEliminar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseNoticiaAccionesEliminar" class="collapse" data-parent="#accordion20">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesNoticiaEliminar"></thead>' +
															   '<tbody id="cuerpoAccionesNoticiaEliminar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

										    '<div class="card">' +
												'<div class="card-header">' +
												    '<a class="collapsed card-link" data-toggle="collapse" href="#collapseNoticiaAccionesModificar" onclick="javascript:testAccionesNoticiaModificar()">' +
												        ' Modificar Noticia ' +
												    '</a>' +
												    '<img class="iconTab" id="iconoTestNoticiaAccionesModificar" src="images/failed.png" hidden>' +
												'</div>' +

												'<div id="collapseNoticiaAccionesModificar" class="collapse" data-parent="#accordion20">' +
													'<div class="card-body">' +
												      	'<div class="table-responsive">' +
															'<table class="table table-bordered">' +
															   '<thead class="cabeceraTablasTest" id="cabeceraAccionesNoticiaModificar"></thead>' +
															   '<tbody id="cuerpoAccionesNoticiaModificar"></tbody>' +
															'</table>' +
								      					'</div>' +
												    '</div>' +
												'</div>' +
										    '</div>' +

							    	'</div>' +
								'</div>' +
					 		' </div>' +
						'</div>';

	$("#testNoticia").append(contenidoTest);
} 

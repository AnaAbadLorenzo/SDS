/**Url común a todas*/
//var urlComun = "http://localhost:8090/SDS";
var urlComun = "http://localhost:8090";

/**Urls específicas por funcionalidad*/
var urlLogin = "/login";
var urlRecuperarPass = "/recuperarPass";
var urlRegistrar = "/registrar";
var urlEmpresa = "/empresa";
var urlObjetivo = "/objetivo";
var urlMenu = "/menu";
var urlUsuario = "/usuario";
var urlRol = "/rol";
var urlFuncionalidad = "/funcionalidad";
var urlAccion = "/accion";
var urlLog = "/log";
var urlNoticia = "/noticia";
var urlPersona = "/persona";
var urlTest = "/test";
var urlAtributos = "/atributos";
var urlAcciones = "/acciones";
var urlGuardar = "/guardar";
var urlBuscar = "/buscar";
var urlModificar ="/modificar";
var urlEliminar = "/eliminar";
var urlReactivar = "/reactivar";

/**Urls Login*/
var urlPeticionAjaxLogin = urlComun + urlLogin + urlLogin;
var urlPeticionAjaxRegistro = urlComun + urlRegistrar;
var urlPeticionAjaxRecuperarPass = urlComun + urlLogin + urlRecuperarPass;
var urlPeticionAjaxListadoEmpresas = urlComun + urlEmpresa + "/listarEmpresasSinP";

/**Urls Menu*/
var urlPeticionAjaxFuncionalidadesUsuario = urlComun + urlMenu + "/funcionalidadesUsuario?";
var urlPeticionAjaxAccionesUsuario = urlComun + urlMenu + "/accionesFuncionalidad?";
var urlPeticionAjaxCambiarContrasenaUsuario = urlComun + urlUsuario + "/cambiarContrasenaUsuario";

/**Urls roles*/
var urlPeticionAjaxListadoRoles = urlComun + urlRol + "/listarRoles";
var urlPeticionAjaxListadoRolesEliminados = urlComun + urlRol + "/listarRolesEliminados";
var urlPeticionAjaxObtenerTodos = urlComun + urlRol + "/obtenerTodos";
var urlPeticionAjaxListarRol = urlComun + urlRol + "/listarRol";
var urlPeticionAjaxAddRol = urlComun + urlRol + urlRol;
var urlPeticionAjaxEditRol = urlComun + urlRol + "/modificarRol";
var urlPeticionAjaxDeleteRol = urlComun + urlRol + "/eliminarRol";
var urlPeticionAjaxReactivarRol = urlComun + urlRol + "/reactivarRol";

/**Urls funcionalidades*/
var urlPeticionAjaxListadoFuncionalidades = urlComun + urlFuncionalidad + "/listarFuncionalidades";
var urlPeticionAjaxListadoFuncionalidadesSinP = urlComun + urlFuncionalidad + "/listarFuncionalidadesSinP";
var urlPeticionAjaxAddFuncionalidad = urlComun + urlFuncionalidad + urlFuncionalidad;
var urlPeticionAjaxListarFuncionalidad = urlComun + urlFuncionalidad + "/listarFuncionalidad";
var urlPeticionAjaxListadoFuncionalidadesEliminadas = urlComun + urlFuncionalidad + "/listarFuncionalidadesEliminadas";
var urlPeticionAjaxEditFuncionalidad = urlComun + urlFuncionalidad + "/modificarFuncionalidad";
var urlPeticionAjaxDeleteFuncionalidad = urlComun + urlFuncionalidad + "/eliminarFuncionalidad";
var urlPeticionAjaxReactivarFuncionalidad = urlComun + urlFuncionalidad + "/reactivarFuncionalidad";

/**Urls acciones*/
var urlPeticionAjaxAccionGuardar = urlComun + urlAccion + urlAccion;
var urlPeticionAjaxListarAccion = urlComun + urlAccion + "/listarAccion";
var urlPeticionAjaxEditAccion = urlComun + urlAccion + "/modificarAccion";
var urlPeticionAjaxDeleteAccion = urlComun + urlAccion + "/eliminarAccion";
var urlPeticionAjaxListadoAcciones = urlComun + urlAccion + "/listarAcciones";
var urlPeticionAjaxListadoAccionesEliminadas = urlComun + urlAccion + "/listarAccionesEliminadas";
var urlPeticionAjaxReactivarAccion = urlComun + urlAccion + "/reactivarAccion";
var urlPeticionAjaxCargarPermiso = urlComun + urlAccion + "/obtenerPermisos";
var urlPeticionAjarAsignarAccion = urlComun + urlAccion + "/asignarAccion";
var urlPeticionAjarDesasignarAccion = urlComun + urlAccion + "/desasignarAccion";

/**Urls logs*/
var urlPeticionAjaxListarTodosLogExcepciones = urlComun + urlLog + "/listarTodosLogsExcepciones";
var urlPeticionAjaxListarLogExcepciones = urlComun + urlLog + "/listarLogsExcepcionesUsuarioFecha";
var urlPeticionAjaxListarTodosLogAcciones = urlComun + urlLog + "/listarTodosLogsAcciones";
var urlPeticionAjaxListarLogAcciones = urlComun + urlLog + "/listarLogsAccionesUsuarioFecha";

/**Urls noticias*/
var urlPeticionAjaxListarTodasNoticias = urlComun + urlNoticia +"/listarNoticias";
var urlPeticionAjaxListarTodasNoticiasPaginacion = urlComun + urlNoticia +"/listarNoticiasPaginacion";
var urlPeticionAjaxListarNoticia = urlComun + urlNoticia + "/listarNoticia";
var urlPeticionAjaxBorrarNoticia = urlComun + urlNoticia + "/borrarNoticia";
var urlPeticionAjaxEditarNoticia = urlComun + urlNoticia + "/modificarNoticia";
var urlPeticionAjaxAddNoticia = urlComun + urlNoticia + "/noticia";

/**Urls empresas**/
var urlPeticionAjaxReactivarEmpresa = urlComun + urlEmpresa + "/reactivarEmpresa";
var urlPeticionAjaxListarEmpresa = urlComun + urlEmpresa + "/listarEmpresa";
var urlPeticionAjaxListarEmpresaEliminada = urlComun + urlEmpresa + "/listarEmpresasEliminadas";
var urlPeticionAjaxListarTodasEmpresas = urlComun + urlEmpresa + "/listarEmpresas";
var urlPeticionAjaxDeleteEmpresa = urlComun + urlEmpresa + "/eliminarEmpresa";
var urlPeticionAjaxEditarEmpresa = urlComun + urlEmpresa + "/modificarEmpresa";
var urlPeticionAjaxAnadirEmpresa = urlComun + urlEmpresa + "/empresa";

/**Urls usuarios*/
var urlPeticionAjaxListarUsuario = urlComun + urlUsuario + "/listarUsuario";
var urlPeticionAjaxListarTodosUsuarios = urlComun + urlUsuario + "/listarUsuarios";
var urlPeticionAjaxListarTodosUsuariosEliminados = urlComun + urlUsuario + "/listarUsuariosEliminados";
var urlPeticionAjaxEditarRolUsuario = urlComun + urlUsuario + "/modificarRolUsuario";
var urlPeticionAjaxEliminarUsuario = urlComun + urlUsuario + "/eliminarUsuario";
var urlPeticionAjaxReactivarUsuario = urlComun + urlUsuario + "/reactivarUsuario";

/**Urls personas*/
var urlPeticionAjaxListarPersonaPorUsuario = urlComun + urlPersona + "/listarPersonaPorUsuario";
var urlPeticionAjaxListarPersona = urlComun + urlPersona + "/listarPersona";
var urlPeticionAjaxListarTodasPersonas = urlComun + urlPersona + "/listarPersonas";
var urlPeticionAjaxListarPersonasEliminadas = urlComun + urlPersona + "/listarPersonasEliminadas";
var urlPeticionAjaxPersonaGuardar = urlComun + urlPersona + "/anadirPersona";
var urlPeticionAjaxEditPersona = urlComun + urlPersona + "/modificarPersona";
var urlPeticionAjaxDeletePersona = urlComun + urlPersona + "/eliminarPersona";
var urlPeticionAjaxAsociarPersonaEmpresa = urlComun + urlPersona + "/asociarPersonaEmpresa";

/**Urls objetivos*/
var urlPeticionAjaxReactivarObjetivo = urlComun + urlObjetivo + "/reactivarObjetivo";
var urlPeticionAjaxListarObjetivo = urlComun + urlObjetivo + "/listarObjetivo";
var urlPeticionAjaxListarObjetivosEliminados = urlComun + urlObjetivo + "/listarObjetivosEliminados";
var urlPeticionAjaxListadoObjetivos = urlComun + urlObjetivo  + "/listarObjetivos";
var urlPeticionAjaxEditarObjetivo = urlComun + urlObjetivo  + "/modificarObjetivo";
var urlPeticionAjaxAddObjetivo = urlComun + urlObjetivo  + "/objetivo";
var urlPeticionAjaxDeleteObjetivo = urlComun + urlObjetivo  + "/eliminarObjetivo";


/**Urls de las peticiones Ajax de test de atributos*/
var urlPeticionAjaxTestLoginAtributos = urlComun + urlTest + urlLogin + urlAtributos;
var urlPeticionAjaxTestRecuperarPassAtributos =  urlComun + urlTest + urlRecuperarPass + urlAtributos;
var urlPeticionAjaxTestRegistrarAtributos = urlComun + urlTest + urlRegistrar + urlAtributos;

var urlPeticionAjaxTestRolAtributosAccionGuardar =  urlComun + urlTest + urlRol + urlAtributos + urlGuardar;
var urlPeticionAjaxTestRolAtributosAccionBuscar = urlComun + urlTest + urlRol + urlAtributos + urlBuscar;
var urlPeticionAjaxTestRolAtributosAccionModificar = urlComun + urlTest + urlRol + urlAtributos + urlModificar;

var urlPeticionAjaxTestFuncionalidadAtributosAccionGuardar = urlComun + urlTest + urlFuncionalidad + urlAtributos + urlGuardar;
var urlPeticionAjaxTestFuncionalidadAtributosAccionBuscar = urlComun + urlTest + urlFuncionalidad + urlAtributos + urlBuscar;
var urlPeticionAjaxTestFuncionalidadAtributosAccionModificar = urlComun + urlTest + urlFuncionalidad + urlAtributos + urlModificar;

var urlPeticionAjaxTestAccionAtributosAccionGuardar = urlComun + urlTest + urlAccion + urlAtributos + urlGuardar;
var urlPeticionAjaxTestAccionAtributosAccionBuscar = urlComun + urlTest + urlAccion + urlAtributos + urlBuscar;
var urlPeticionAjaxTestAccionAtributosAccionModificar = urlComun + urlTest + urlAccion + urlAtributos + urlModificar;

var urlPeticionAjaxTestUsuarioAtributosAccionModificarRolUsuario = urlComun + urlTest + urlUsuario + urlAtributos + "/modificarRol";
var urlPeticionAjaxTestUsuarioAtributosAccionBuscar = urlComun + urlTest + urlUsuario + urlAtributos + urlBuscar;

var urlPeticionAjaxTestNoticiaAtributosAccionGuardar = urlComun + urlTest + urlNoticia + urlAtributos + urlGuardar;
var urlPeticionAjaxTestNoticiaAtributosAccionBuscar = urlComun + urlTest + urlNoticia + urlAtributos + urlBuscar;
var urlPeticionAjaxTestNoticiaAtributosAccionModificar = urlComun + urlTest + urlNoticia + urlAtributos + urlModificar;


/**Urls de las peticiones Ajax de test de acciones*/
var urlPeticionAjaxTestLoginAcciones = urlComun + urlTest + urlLogin + urlAcciones;
var urlPeticionAjaxTestRecuperarPassAcciones = urlComun + urlTest + urlRecuperarPass + urlAcciones;
var urlPeticionAjaxTestRegistrarAcciones = urlComun + urlTest + urlRegistrar + urlAcciones;

var urlPeticionAjaxTestRolAccionGuardar = urlComun + urlTest + urlRol + urlAccion + urlGuardar;
var urlPeticionAjaxTestRolAccionEliminar = urlComun + urlTest + urlRol + urlAccion + urlEliminar;
var urlPeticionAjaxTestRolAccionBuscar = urlComun + urlTest + urlRol + urlAccion + urlBuscar;
var urlPeticionAjaxTestRolAccionModificar = urlComun + urlTest + urlRol + urlAccion + urlModificar;
var urlPeticionAjaxTestRolAccionReactivar = urlComun + urlTest + urlRol + urlAccion + urlReactivar;

var urlPeticionAjaxTestFuncionalidadAccionGuardar = urlComun + urlTest + urlFuncionalidad + urlAccion + urlGuardar;
var urlPeticionAjaxTestFuncionalidadAccionEliminar = urlComun + urlTest + urlFuncionalidad + urlAccion + urlEliminar;
var urlPeticionAjaxTestFuncionalidadAccionBuscar = urlComun + urlTest + urlFuncionalidad + urlAccion + urlBuscar;
var urlPeticionAjaxTestFuncionalidadAccionModificar = urlComun + urlTest + urlFuncionalidad + urlAccion + urlModificar;
var urlPeticionAjaxTestFuncionalidadAccionReactivar = urlComun + urlTest + urlFuncionalidad + urlAccion + urlReactivar;

var urlPeticionAjaxTestAccionAccionGuardar = urlComun + urlTest + urlAccion + urlAccion + urlGuardar;
var urlPeticionAjaxTestAccionAccionModificar = urlComun + urlTest + urlAccion + urlAccion + urlModificar;
var urlPeticionAjaxTestAccionAccionEliminar = urlComun + urlTest + urlAccion + urlAccion + urlEliminar;
var urlPeticionAjaxTestAccionAccionBuscar = urlComun + urlTest + urlAccion + urlAccion + urlBuscar;
var urlPeticionAjaxTestAccionAccionReactivar = urlComun + urlTest + urlAccion + urlAccion + urlReactivar;
var urlPeticionAjaxTestAccionAccionAsignar = urlComun + urlTest + urlAccion + urlAccion + "/asignar";
var urlPeticionAjaxTestAccionAccionRevocar = urlComun + urlTest + urlAccion + urlAccion + "/revocar";

var urlPeticionAjaxTestUsuarioAccionBuscar = urlComun + urlTest + urlUsuario + urlAccion + urlBuscar;
var urlPeticionAjaxTestUsuarioAccionEliminar = urlComun + urlTest + urlUsuario + urlAccion + urlEliminar;
var urlPeticionAjaxTestUsuarioAccionModificarRolUsuario = urlComun + urlTest + urlUsuario + urlAccion + "/modificarRolUsuario";
var urlPeticionAjaxTestUsuarioAccionModificarPasswdUsuario = urlComun + urlTest + urlUsuario + urlAccion + "/modificarPasswdUsuario";
var urlPeticionAjaxTestUsuarioAccionReactivar = urlComun + urlTest + urlUsuario + urlAccion + urlReactivar;

var urlPeticionAjaxTestNoticiaAccionGuardar = urlComun + urlTest + urlNoticia + urlAccion + urlGuardar;
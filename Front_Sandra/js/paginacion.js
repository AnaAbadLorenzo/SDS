var paginador;            
var totalPaginas;         
var itemsPorPagina = 10;   
var numerosPorPagina = 3; 
var posicionActualPaginador=1;


function creaPaginador(totalItems){           

    paginador = $(".pagination");

    totalPaginas = Math.ceil(totalItems/itemsPorPagina);

    //Enlace que lleva a la página previa
    $('<li class="page_item prev_link"> <a href="#" aria-label="Previous" class="prev_link"> <span aria-hidden="true">&laquo;</span> </a> </li>').appendTo(paginador);

    var pagina = 0;
    while(totalPaginas > pagina){
        //Enlace que lleva a la página seleccionada 
        $('<li class="page_item a"><a href="#" class="page_link">'+(pagina+1)+'</a></li>').appendTo(paginador);
        pagina++;
    }   
    //Enlace que lleva a la página siguiente
    $('<li class="page_item next_link"> <a href="#" aria-label="Next" class="next_link"> <span aria-hidden="true">&raquo;</span> </a> </li>').appendTo(paginador);

    
    paginador.find(".page_link:first").addClass("active");
    
    paginador.find(".page_link:first").parents("li").addClass("active");

    
    paginador.find(".prev_link").hide();

   
    paginador.find("li .page_link").click(function(){
        var irpagina =$(this).html().valueOf()-1;
        cargaPagina(irpagina);
        return false;
    });

    //Carga la página previa
    paginador.find("li .prev_link").click(function(){
        var irpagina =parseInt(paginador.data("pag")) -1;
        cargaPagina(irpagina);
        return false;
    });

    //Carga la siguiente página
    paginador.find("li .next_link").click(function()
    {
        var irpagina =parseInt(paginador.data("pag")) +1;
        cargaPagina(irpagina);
        return false;
    });


    cargaPagina(0);


    $(".page_link").on("click", function(e){       
        e.preventDefault();
        //Quita la clase "active" de todos los elementos
        $(".page_link").removeClass("active");
        //Pone la clase "active" en el elemento cliqueado
        $(this).addClass("active");

        posicionActualPaginador=$(".page_link").index(this)+1; 
    });

    $(".first_link").on("click", function(e)
    {
        e.preventDefault();
        //Quita la clase "active" de todos los elementos
        $(".page_link").removeClass("active");  
        $(".page_link:eq(0)").addClass("active");       
        posicionActualPaginador=1;
    });

    $(".last_link").on("click", function(e)
    {
        e.preventDefault();
        //Quita la clase "active" de todos los elementos
        $(".page_link").removeClass("active");  
        var n = $(".page_link").length;
        $(".page_link:eq(" + (n-1)  + ")").addClass("active");          
        posicionActualPaginador=n;
    });

    $(".next_link").on("click", function(e)
    {
        e.preventDefault();
        //Quita la clase "active" de todos los elementos
        $(".page_link").removeClass("active");              
        $(".page_link:eq(" + current_paginator_item  + ")").addClass("active");         
        posicionActualPaginador++;       
    });



    $(".prev_link").on("click", function(e)
    {
        e.preventDefault();
        //Quita la clase "active" de todos los elementos
        $(".page_link").removeClass("active");  
        posicionActualPaginador--;           
        $(".page_link:eq(" + (posicionActualPaginador-1)  + ")").addClass("active");     

    }); 

}

function calcularInicioPagina(paginaActual){
    var desde = paginaActual * items*pagina;

    return desde;
}


function cargaPagina(paginaCargar) {

    calcularInicioPagina(paginaCargar);

    if(paginaCargar >= 1){
        paginador.find(".prev_link").show();
    }else{
        paginador.find(".prev_link").hide();
    }


    if(paginaCargar < totalPaginas - 1){
        paginador.find(".next_link").show();
    }else{
        paginador.find(".next_link").hide();;
    }

    
    paginador.data("pag",paginaCargar);

    if(numerosPorPagina>1)
    {
        //oculta todos los enlces
        $(".page_link").hide();
        $(".a").hide();

        if(paginaCargar < (totalPaginas - numerosPorPagina))
        {
            $(".page_link").slice(paginaCargar,numerosPorPagina + paginaCargar).show();
            $(".a").slice(paginaCargar,numerosPorPagina + paginaCargar).show();
        }
        else
        {           
            ///alert("2");
            if(totalPaginas > numerosPorPagina)
            {
                $(".page_link").slice(totalPaginas - numerosPorPagina).show();
                $(".a").slice(totalPaginas - numerosPorPagina).show();
            }

        }
    }


}

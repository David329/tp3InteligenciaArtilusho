<%-- 
    Document   : Home
    Created on : 24-jun-2017, 0:30:01
    Author     : win7
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>IA TB3</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="Content/css/materialize.min.css" type="text/css" media="screen,projection">
        <link rel="stylesheet" href="Content/css/bootstrap.min.css" type="text/css">
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="container">
        <div class="col-lg-12" style="height: 150px;"></div>
        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
        <div class="card col-lg-6 col-md-6 col-sm-6 col-xs-12" style="vertical-align: middle; height: 300px;">
            <h3>Clasificador</h3>
            <div class="row">

                <div class="input-field col-lg-12">
                    <span>Algoritmo: </span>
                </div>
                <div class="input-field col-lg-8">
                    <select id="algoritmos">
                      <option value="" disabled selected>Elija una opción</option>
                      <option value="1">J48</option>
                      <option value="2">RandomForest</option>
                      <option value="3">RandomTree</option>
                      <option value="4">Todos</option>
                    </select>
                </div>
                
                <div class="col-lg-4">
                    <button class="btn waves-effect waves-light" type="button" id="btnProcesar" onclick="procesar();">Procesar</button>
                </div>
                
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <span id = "resutlado"></span>
                </div>
                
            </div>
            
            
        </div>
        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
        </div>
        
        <script src="Content/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="Content/js/materialize.min.js" type="text/javascript"></script>
        <script>
            $(document).ready(function() {
                $('select').material_select();
            });
            
            $('select').material_select('destroy');
            
             function procesar(){
                 var valor = $( "#algoritmos option:selected" ).val();
                 
                 $.ajax({
                    type: 'GET',
                    url: 'HomeController',
                    data: { 
                        'algoritmo': valor
                    },
                    success: function(result){
                        $("#resutlado").text(result);
                    }
                });
             }
            
        </script>
    </body>
</html>

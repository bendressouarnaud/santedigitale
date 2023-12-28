<html>

    <head>
      <link rel="stylesheet" href="https://themesbrand.com/veltrix/layouts/plugins/chartist/css/chartist.min.css">
      <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/metismenu.min.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/icons.css' />" rel="stylesheet" type="text/css">
      <link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">

	  <!-- Responsive datatable examples -->
	  <link href="<c:url value='/css/responsive.bootstrap4.css' />" rel="stylesheet" type="text/css">

	  <!-- Plugins css -->
	  <link href="<c:url value='/css/bootstrap-touchspin.min.css' />" rel="stylesheet" type="text/css">
    </head>

    <body>


        <div id="wrapper">
            <div class="content-page">
                <!-- Start content -->
                <div class="content">
                   <div class="container-fluid">

                        <div class="row">
                          <div class="col-sm-6 col-md-3 m-t-30">
                                <div class="modal fade bs-example-modal-center" tabindex="-1"
                                    role="dialog" aria-labelledby="mySmallModalLabel"
                                    aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title mt-0">Info</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                            </div>
                                            <div class="modal-body">
                                                <div id="modifinfo"></div>
                                            </div>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal-dialog -->
                                </div><!-- /.modal -->
                            </div>
                        </div>

                        <div class="row">
                            <div id="map" style="width: 100vw; height: 100vh;">
                        </div>


                   </div>
                </div>
            </div>
        </div>




        <!--<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>-->

	  <script src="<c:url value='/js/jquery.min.js' />"></script>
	  <script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>
	  <script src="<c:url value='/js/metisMenu.min.js' />"></script>
	  <script src="<c:url value='/js/jquery.slimscroll.js' />"></script>
	  <script src="<c:url value='/js/waves.min.js' />"></script>
      <script src="<c:url value='/js/bootstrap-touchspin.min.js' />"></script>
      <script src="<c:url value='/js/bootstrap-filestyle.min.js' />"></script>
      <script src="<c:url value='/js/bootstrap-maxlength.min.js' />"></script>


	  <!-- App js
	  <script src="<c:url value='/js/app.js' />"></script> -->
	  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

        <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAtJHXa9mv08ooUymhgm5Ty3h960s-_gQ0&sensor=false">
        </script>

        <script>

            var tabLongitude = new Array();
            var tabLatitude = new Array();
            var tabLibelle = new Array();
            var tabIdpan = new Array();
            var tabIdpanHold = new Array();
            var i=0;

            $( document ).ready(function() {
                //alert("OK");
                getsupports();
            });


            // To get all supports :
            function getsupports(){
                //$.get("http://34.68.214.253/gestpann/getsupportschoix?choix="+choix+"&id="+valeur+"&typesupport="+typesupport,
                $.get("http://www.gestdp.com/gestion/getadministres",
                    function (data){
                        $(data).find('item').each(function(){

                            //alert("nom :"+$(this).find('libelle').text());

                        	var latit = $(this).find('latitude').text();
                        	var longi = $(this).find('longitude').text();
                        	var nom = $(this).find('libelle').text();
                        	var numag = $(this).find('numag').text();
                        	var idcord = $(this).find('idcord').text();

                        	// set :
                        	tabLatitude[i] = parseFloat(latit);
                        	tabLongitude[i] = parseFloat(longi);
                        	tabLibelle[i] = nom;
                        	tabIdpan[i] = parseInt(numag);
                        	tabIdpanHold[i] = parseInt(idcord);
                        	i = i +1;
                        });

                        //alert("Taille tabLongitude :"+tabLongitude.length);

                        if(tabLongitude.length == 0){
                            alert("Il n'y a pas de données pour ce lieu!!!");
                            return;
                        }

                        //
                        //alert("On commence l'affichage !!!");

                        var map = new google.maps.Map(document.getElementById('map'), {
                          zoom: 10,
                          center: new google.maps.LatLng(tabLatitude[0], tabLongitude[0]),
                          mapTypeId: google.maps.MapTypeId.ROADMAP
                        });

                        var infowindow = new google.maps.InfoWindow();

                        // Display all points :
                        var marker;
                        for (var j = 0; j < tabLongitude.length; j++) {
                            marker = new google.maps.Marker({
                                position: new google.maps.LatLng(tabLatitude[j], tabLongitude[j]),
                                map: map,
                                icon: {
                                  url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png"
                                }
                            });

                            google.maps.event.addListener(marker, 'click', (function(marker, j) {
                              return function() {
                                infowindow.setContent(tabIdpan[j]+" - "+tabIdpanHold[j]);
                                infowindow.open(map, marker);
                                //
                                //$('#ultraModal-1').modal('toggle');
                                // Afficher :
                                document.getElementById("modifinfo").innerHTML =
                                    "<p>Veuillez patienter ...</p>";
                                displayImage(tabIdpanHold[j]);
                              }
                            })(marker, j));
                        }
                    }
                );
            }





            function displayImage(idcord){

                var imageprop = "";
                var imagemag = "";
                var dateheure = "";
                var secteurs = "";
                var quartiers = "";
                var villes = "";
                var users = "";

                //$.get("http://34.68.214.253/gestpann/getsupportbyid?idpan="+idpan,
                $.get("http://www.gestdp.com/gestion/getcoordid?idcord="+idcord,
                    function (data){
                        $(data).find('item').each(function(){
                            imageprop = $(this).find('imageprop').text();
                            imagemag = $(this).find('imagemag').text();
                        });

                        // Afficher :
                        document.getElementById("modifinfo").innerHTML =
                            "<div><img src='data:image/jpeg;base64,"+imageprop+"' /></div>"+
                            "<div><img src='data:image/jpeg;base64,"+imagemag+"' /></div>";
                    }
                );
            }




        </script>
    </body>

</html>
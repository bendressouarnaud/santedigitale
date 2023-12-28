<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 24/07/2020
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>



<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,minimal-ui">
    <title>GestPAT - Consultation</title>
    <meta content="Admin Dashboard" name="description">
    <meta content="Themesbrand" name="author">
    <link rel="shortcut icon" href="/images/favicon.ico">
    <!--Chartist Chart CSS -->
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/metismenu.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/icons.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css">

    <!-- DataTables -->
    <link href="<c:url value='/css/datatables.bootstrap4.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/buttons.bootstrap4.css' />" rel="stylesheet" type="text/css">
    <!-- Responsive datatable examples -->
    <link href="<c:url value='/css/responsive.bootstrap4.css' />" rel="stylesheet" type="text/css">

    <!-- Plugins css -->
    <link href="<c:url value='/css/select2.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap-datepicker.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap-colorpicker.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap-touchspin.min.css' />" rel="stylesheet" type="text/css">

    <script>

        //
        var myBoolean = false;
        var activeRechHopital = false;

        function modifiermenu(){


            var idService = parseInt($("#servicelib").val());
            alert("idService : "+idService);

            /*
            if(!myBoolean){
                document.getElementById("limotif").style.display = "none";
                //document.getElementById("divmotif").style.display = "none";
                myBoolean = true;
                performAction();
            }
            else{
                document.getElementById("limotif").style.display = "list-item";
                //document.getElementById("divmotif").style.display = "block";
                myBoolean = false;
                performAction();
            }
            */
        }

        // Perform an accurate check to activate or deactivate elements :
        function performAction(){
            if($('#limotif').css('display') != 'none'){
                //alert("limotif EXISTS");
                document.getElementById('amotif').click();
                return;
            }
            if($('#liantecedent').css('display') != 'none'){
                document.getElementById('aantecedent').click();
                return;
            }
            if($('#limodevie').css('display') != 'none'){
                document.getElementById('amodedevie').click();
                return;
            }
            if($('#liexamenclin').css('display') != 'none'){
                document.getElementById('aexamenclinique').click();
                return;
            }
            if($('#liconclusion').css('display') != 'none'){
                document.getElementById('aconclusion').click();
                return;
            }
            if($('#lidiagnostic').css('display') != 'none'){
                document.getElementById('adiagnostic').click();
                return;
            }
            if($('#litraitement').css('display') != 'none'){
                document.getElementById('atraitement').click();
                return;
            }
        }

        // For HOPITAL
        function onchangeHopital(){
            if(activeRechHopital) onchangeService();
        }

        /* Change Service : */
        function onchangeService(){

            //
            var idService = parseInt($("#servicelib").val());
            var hopitallib = parseInt($("#hopitallib").val());

            //$.get("http://localhost:8080/gestion/getserviceid/"+idService+"/"+hopitallib,
            $.get("http://www.gestdp.com/gestion/getserviceid/"+idService+"/"+hopitallib,
                function(data) {
                    //
                    var donnees = {};

                    $(data).find('item').each(function(){

                        donnees.motif = parseInt($(this).find('motif').text());
                        donnees.antecedent = parseInt($(this).find('antecedent').text());
                        donnees.modevie = parseInt($(this).find('modevie').text());
                        donnees.examenclinique = parseInt($(this).find('examenclinique').text());
                        donnees.conclusion = parseInt($(this).find('conclusion').text());
                        donnees.diagnostic = parseInt($(this).find('diagnostic').text());
                        donnees.traitement = parseInt($(this).find('traitement').text());

                    });

                    // Now, perform :
                    if(donnees.motif ==0) document.getElementById("limotif").style.display = "none";
                    else document.getElementById("limotif").style.display = "list-item";
                    if(donnees.antecedent ==0) document.getElementById("liantecedent").style.display = "none";
                    else document.getElementById("liantecedent").style.display = "list-item";
                    if(donnees.modevie ==0) document.getElementById("limodevie").style.display = "none";
                    else document.getElementById("limodevie").style.display = "list-item";
                    if(donnees.examenclinique ==0) document.getElementById("liexamenclin").style.display = "none";
                    else document.getElementById("liexamenclin").style.display = "list-item";
                    if(donnees.conclusion ==0) document.getElementById("liconclusion").style.display = "none";
                    else document.getElementById("liconclusion").style.display = "list-item";
                    if(donnees.diagnostic ==0) document.getElementById("lidiagnostic").style.display = "none";
                    else document.getElementById("lidiagnostic").style.display = "list-item";
                    if(donnees.traitement ==0) document.getElementById("litraitement").style.display = "none";
                    else document.getElementById("litraitement").style.display = "list-item";

                    //
                    performAction();
                    if(!activeRechHopital) activeRechHopital = true;
                }
            );
        }


        function selectconstante(valeur){
            //alert("Valeur : "+valeur);
            document.getElementById("idconstante").style.display = "block";
            $('#idconstante').val(valeur);
            $('.bs-example-modal-lg').modal('hide');
            document.getElementById("idconstante").style.display = "none";
        }

        //   --  DATE
        function checkHour(){
            var retour = true;

            var heurenaiss = $("#heurenaiss").val();
            if (!/^[0-9]{1,2}:[0-5]{1}[0-9]{1}$/.test(heurenaiss)){
                // Alerter infos
                var info = document.getElementById("infos");
                info.innerHTML = "<h3 style='color:red;'>Le format de l'heure de naissance est incorrect !!!</h3>";
                window.location = "#infos";
                retour = false;
            }

            return retour;
        }

        // age
        function checkAge(){
            var retour = true;
            var age = $("#age").val();
            if (!/^[0-9]{1,3}$/.test(age)){
                // Alerter infos
                var info = document.getElementById("infos");
                info.innerHTML = "<h3 style='color:red;'>Veuillez renseigner un age correct. Exemple : 20 !!!</h3>";
                window.location = "#infos";
                retour = false;
            }

            return retour;
        }

        function performCheck(){

            // If a patient has been choosen, go out and stop :
            //var idpatient = parseInt($("#idpatient").val());
            //if(idpatient>0) return true;

            if(!checkAge()) return false;

            // Check if files have been added :
            var nbre = document.getElementsByClassName("form-control fichier");
            for(var i=1; i < nbre.length; i++){
                if(nbre[i].files.length === 0){
                    document.getElementById("infos").innerHTML = "<h2>Veuillez uplaoder 1 fichier &agrave; la ligne "+(i)
                        +"</h2>";
                    window.location = "#infos";
                    return false;
                }
            }

            //
            var nom = $("#nom").val();
            if(nom.length == 0){
                var info = document.getElementById("infos");
                info.innerHTML = "<h3 style='color:red;'>Veuillez renseigner un nom correct !!!</h3>";
                window.location = "#infos";
                return false;
            }

            // Prenom :
            var prenom = $("#prenom").val();
            if(prenom.length == 0){
                var info = document.getElementById("infos");
                info.innerHTML = "<h3 style='color:red;'>Veuillez renseigner un pr&eacute;nom correct !!!</h3>";
                window.location = "#infos";
                return false;
            }

            // Check if the checkBOx exists :
            if($("#commentautre").length){
                // Get the TEXT :
                var contenu_comment = $("#commentautre").val();
                if(contenu_comment.length > 0){
                    if(!$('#autres').is(':checked')){
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>Veuillez cocher la case 'Autres, pr&eacute;cisez', car du texte a &eacute;t&eacute; saisi !!!</h3>";
                        window.location = "#infos";
                        return false;
                    }
                }
            }


            // Check if the checkBOx exists for MODE DE VIE:
            if($("#autresmodecomment").length){
                // Get the TEXT :
                var contenu_commentM = $("#autresmodecomment").val();
                if(contenu_commentM.length > 0){
                    if(!$('#autresmode').is(':checked')){
                        var info = document.getElementById("infos");
                        info.innerHTML = "<h3 style='color:red;'>Veuillez cocher la case 'Autres, pr&eacute;cisez (mode de vie)', car du texte a &eacute;t&eacute; saisi !!!</h3>";
                        window.location = "#infos";
                        return false;
                    }
                }
            }


            // --->
            if($("#listeconstantes").length) {
                document.getElementById("idconstante").style.display = "block";
            }
            return true;
        }

        function onchangePatient(){
            var idpatient = parseInt($("#idpatient").val());
            // Get PATIENT INFO :
            if(idpatient > 0){

                //$.get("http://localhost:8080/gestion/getpatientinfo/"+idpatient,
                $.get("http://www.gestdp.com/gestion/getpatientinfo/"+idpatient,
                    function(data) {
                        //
                        //alert("On a des donnees");
                        $(data).find('item').each(function(){
                            var nom = $(this).find('nom').text();
                            var prenom = $(this).find('prenom').text();
                            var cni = $(this).find('cni').text();
                            var telephone = $(this).find('telephone').text();
                            var email = $(this).find('email').text();
                            var sexe = $(this).find('sexe').text();
                            var age = $(this).find('age').text();
                            var profession = $(this).find('profession').text();
                            var adresse = $(this).find('adresse').text();

                            //
                            $('#nom').val(nom);
                            $('#prenom').val(prenom);
                            $('#age').val(age);
                            $('#telephone').val(telephone);
                            $('#email').val(email);
                            // Profession :
                            //document.getElementById('profession').selectedIndex = parseInt(profession);
                            if(parseInt(profession) > 0) {
                                $("#profession").val(parseInt(profession));
                                //$('#profession').select2('refresh');
                                $('#profession').trigger('change');
                            }

                            $('#adresse').val(adresse);
                            // Sexe
                            if(sexe == "F") $("#sexe").val("F"); //document.getElementById('sexe').selectedIndex = 0;
                            else if(sexe == "M") $("#sexe").val("M"); // document.getElementById('sexe').selectedIndex = 1;
                            //$('#sexe').select2('refresh');
                            $('#sexe').trigger('change');

                            $('#cni').val(cni);
                        });
                    }
                );
            }
            else{
                //
                $('#nom').val("");
                $('#prenom').val("");
                $('#age').val("");
                $('#telephone').val("");
                $('#email').val("");
                $('#adresse').val("");
                $('#cni').val("");
            }
        }
    </script>
</head>
<body>
<!-- Begin page -->
<div id="wrapper">
    <!-- Top Bar Start -->
    <div class="topbar">
        <!-- LOGO -->
        <div class="topbar-left"><a href="#" class="logo"><span><img src="<c:url value='/images/logo-light.png' />" alt="" height="18"> </span>
            <i><img src="<c:url value='/images/logo-sm.png' />" alt="" height="22"></i></a></div>
        <nav class="navbar-custom">
            <ul class="navbar-right d-flex list-inline float-right mb-0">
                <li class="dropdown notification-list d-none d-md-block">
                    <form role="search" class="app-search">
                        <div class="form-group mb-0"><input type="text" class="form-control" placeholder="Search.."> <button type="submit"><i class="fa fa-search"></i></button></div>
                    </form>
                </li>
            </ul>

        </nav>
    </div>
    <!-- Top Bar End --><!-- ========== Left Sidebar Start ========== -->
    <div class="left side-menu">
        <div class="slimscroll-menu" id="remove-scroll">
            <!--- Sidemenu -->
            <div id="sidebar-menu">
                <!-- Left Menu Start -->
                <ul class="metismenu" id="side-menu">
                    <li class="menu-title">Menu</li>
                    <li>
                        <a href="javascript:void(0);" class="waves-effect"><i class="ti-email"></i><span> Menu <span class="float-right menu-arrow"><i class="mdi mdi-chevron-right"></i></span></span></a>
                        <ul class="submenu">
                            <li><a href="/gestion/connexpatient">Accueil</a></li>
                            <c:if test="${!empty lienretour}">
                                <li><a href="${lienretour}">Retour</a></li>
                            </c:if>
                            <li><a href="/gestion/deconnexion">Se d&eacute;connecter</a></li>
                        </ul>
                    </li>

                </ul>
            </div>
            <!-- Sidebar -->
            <div class="clearfix"></div>
        </div>
        <!-- Sidebar -left -->
    </div>
    <!-- Left Sidebar End --><!-- ============================================================== --><!-- Start right Content here --><!-- ============================================================== -->
    <div class="content-page">
        <!-- Start content -->
        <div class="content">
            <div class="container-fluid">
                <div class="page-title-box">
                    <div class="row align-items-center">
                        <div class="col-sm-12" style="text-align:center;">
                            <!--<h4 style="float:left; margin-top: 10px">
                            Informations de l'ordonnance</h4>-->
                            <img src="<c:url value='/images/images3.jpg'/>"
                                 Style="width:90px; height:70px"/>
                        </div>
                    </div>
                </div>
                <!-- end row -->



                <c:if test="${!empty listeconstantes}">
                    <div class="row">
                        <div class="col-sm-6 col-md-3 m-t-30">
                            <div class="modal fade bs-example-modal-lg" tabindex="-1"
                                 role="dialog" aria-labelledby="myLargeModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title mt-0">Liste des patients avec leurs constantes</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="col-12">
                                                <div class="card">
                                                    <div class="card-body" id="listeconstantes">
                                                        <table id="datatable" class="table table-bordered dt-responsive nowrap"
                                                               style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                                            <thead>
                                                            <tr>
                                                                <th>Nom </th>
                                                                <th>Heure</th>
                                                                <th>Poids</th>
                                                                <th>Taille</th>
                                                                <th>Tension</th>
                                                                <th>Temp&eacute;rature</th>
                                                                <th>S&eacute;lectionner</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>

                                                            <c:forEach var="patient" items="${listeconstantes}">
                                                                <tr>
                                                                    <td>${patient[0]}</td>
                                                                    <td>${patient[1]}</td>
                                                                    <td>${patient[2]}</td>
                                                                    <td>${patient[3]}</td>
                                                                    <td>${patient[4]}</td>
                                                                    <td>${patient[6]}</td>
                                                                    <td>
                                                                        <a title="Afficher l'historique" href="javascript:selectconstante('${patient[5]}')" style="color: #78CBF5">
                                                                            <i class="far fa-address-card"></i>
                                                                        </a>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->
                        </div>
                    </div>
                </c:if>




                <div class="row">
                    <div class="col-sm-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 header-title">Informations</h4>
                                <p class="text-muted m-b-30 font-14">Veuillez renseigner les informations relatives &agrave; la consultation</p>

                                <form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                                      action="/gestion/traiterfichier"
                                      method="POST" enctype="multipart/form-data" >
                                    <!--<h3>Seller Details</h3>-->
                                    <fieldset>


                                        <!--  Service & Date de consultation   -->
                                        <div class="row">

                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Fichier</label>
                                                    <input name="fichiersajoute"
                                                           type="file" class="form-control fichier">
                                                </div>
                                            </div>
                                        </div>



                                        <div class="row">
                                            <div class="col-sm-6 text-right" style="float: right; margin-bottom: 20px">
                                                <button class="btn btn-primary w-md waves-effect waves-light"
                                                        type="submit" name="submit-enr">
                                                    Enregistrer
                                                </button>
                                            </div>
                                        </div>

                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>



                <!-- end row -->
            </div>
            <!-- container-fluid -->
        </div>
        <!-- content -->
        <footer class="footer">
            <!--
            © 2019 Veltrix
            <span class="d-none d-sm-inline-block">- Crafted with <i class="mdi mdi-heart text-danger"></i> by Themesbrand</span>.
            -->
        </footer>
    </div>
    <!-- ============================================================== --><!-- End Right content here --><!-- ============================================================== -->
</div>
<!-- END wrapper --><!-- jQuery  -->
<script src="<c:url value='/js/jquery.min.js' />"></script>
<script src="<c:url value='/js/bootstrap.bundle.min.js' />"></script>
<script src="<c:url value='/js/metisMenu.min.js' />"></script>
<script src="<c:url value='/js/jquery.slimscroll.js' />"></script>
<script src="<c:url value='/js/waves.min.js' />"></script>

<!-- Required datatable js -->
<script src="<c:url value='/js/jquery.datatables.min.js' />"></script>
<script src="<c:url value='/js/datatables.bootstrap4.min.js' />"></script>
<script src="<c:url value='/js/datatables.buttons.min.js' />"></script>

<script src="<c:url value='/js/select2.min.js' />"></script>
<script src="<c:url value='/js/bootstrap-datepicker.js' />"></script>
<script src="<c:url value='/js/bootstrap-touchspin.min.js' />"></script>
<script src="<c:url value='/js/bootstrap-filestyle.min.js' />"></script>
<script src="<c:url value='/js/bootstrap-maxlength.min.js' />"></script>

<!-- Datatable init js -->
<script src="<c:url value='/pages/datatables.init.js' />"></script>

<!-- App js -->
<script src="<c:url value='/js/app.js' />"></script>

<script>


</script>
</body>
</html>




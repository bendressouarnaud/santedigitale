<%--
  Created by IntelliJ IDEA.
  User: Ngbandama
  Date: 15/09/2020
  Time: 09:25
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
    <title>GestPAT - Vente m&eacute;dicament</title>
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

    <link href="<c:url value='/css/select2.min.css' />" rel="stylesheet" type="text/css">

    <script>

        function sommeTotal(){

            var montants = document.getElementsByClassName("form-control price");
            var quantites = document.getElementsByClassName("form-control select2 quantite");
            if (montants.length > 0) {
                var somme = 0;
                //alert("Quantite 1 : "+quantites[0].value);
                for(var k =0; k<montants.length;k++){
                    somme += (parseInt(montants[k].value)*parseInt(quantites[k].value));
                }
                //
                var info = document.getElementById("information");
                info.innerHTML = "Total : "+somme;
            }
            //
        }

        function onchangeDrogTp(drogId, prididfield){
            var drogid = $("#"+drogId+"").val();

            // Call This to get DATA :
            $.get(monurl+"getmedicamentprice/"+drogid,
                function(data) {
                    var tampon = "";
                    $(data).find('item').each(function(){
                        tampon = $(this).find('prix').text();
                    });

                    //
                    $("#"+prididfield+"").val(tampon);
                    //
                    sommeTotal();
                }
            );
        }

        function onchangeDrog(){
            var drogid = $(".drogid").val();

            // Call This to get DATA :
            $.get(monurl+"getmedicamentprice/"+drogid,
                function(data) {
                    var tampon = "";
                    $(data).find('item').each(function(){
                        tampon = $(this).find('prix').text();
                    });

                    //
                    $("#priceid").val(tampon);
                    //
                    sommeTotal();
                }
            );
        }

        function performCheck(){

            // If a patient has been choosen, go out and stop :
            var idcus = parseInt($("#idcus").val());
            var customer = $("#customer").val();

            var info = document.getElementById("information");
            info.innerHTML = "";

            //
            if((idcus==0) && (customer.trim().length==0)){
                info.innerHTML = "Veuillez s&eacute;lectionner un client ou renseigner son nom !";
                return false;
            }

            //
            return true;
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
                        <a href="javascript:void(0);"
                           class="waves-effect"><i class="fas fa-align-justify"></i>
                            <span> Actions <span class="float-right menu-arrow">
                            <i class="fas fa-angle-left"></i></span></span></a>
                        <ul class="submenu">
                            <li><a href="/gestion/connexpatient">Accueil</a></li>
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
                            <img src="<c:url value='/images/medicament.jpg'/>"
                                 Style="width:90px; height:70px"/>
                        </div>
                    </div>
                </div>
                <!-- end row -->

                <div class="row">
                    <br>
                </div>



                <div class="row">
                    <div class="col-sm-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="mt-0 header-title">Informations</h4>
                                <!--<p class="text-muted m-b-30 font-14">Veuillez renseigner les informations relatives &agrave; la consultation</p>-->

                                <br>
                                <br>

                                <form id="form-horizontal" class="form-horizontal form-wizard-wrapper"
                                      action="/gestion/enregventedrog"
                                      method="POST" onsubmit="return performCheck();">
                                    <fieldset>

                                        <!--  Liste des patients  -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Rechercher Client</label>
                                                    <div class="col-lg-9">
                                                        <select id="idcus" name="idcus" class="form-control select2">
                                                            <c:if test="${!empty creation}">
                                                                <option value="0">Ne pas choisir</option>
                                                            </c:if>
                                                            <c:forEach items="${listeClient}" var="donnee" >
                                                                <option value="${donnee.getIdcus()}">
                                                                        ${ donnee.getNom() }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Nouveau</label>
                                                    <div class="col-lg-9">
                                                        <input id="customer" name="customer" type="text"
                                                               value="" class="form-control" maxlength="50"
                                                               placeholder="Nouveau client">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>



                                        <!--  Liste des Medecins  -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">M&eacute;decin</label>
                                                    <div class="col-lg-9">
                                                        <select id="idmed" name="idmed" class="form-control select2">
                                                            <option value="0">Ne pas choisir</option>
                                                            <c:forEach items="${listemedecin}" var="donnee" >
                                                                <option value="${donnee.getIdmed()}">
                                                                        ${ donnee.getNom() }&nbsp;${ donnee.getPrenom() }
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="row">
                                            &nbsp;
                                            <br>
                                        </div>


                                        <!--  Liste Medicaments   -->
                                        <div class="row">
                                            <div class="col-md-12">
                                                <table id="tabinfo" width="100%">
                                                    <tr>
                                                        <th width="20%">M&eacute;dicament</th>
                                                        <th width="10%">Prix</th>
                                                        <th width="16%">Quantit&eacute;</th>
                                                        <th width="16%">Action</th>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <div id="addmedicament"></div>
                                                        </td>
                                                        <td>
                                                            <input id="priceid" name="prixdrog[]" type="text"
                                                                   onfocusout="sommeTotal()"
                                                                   class="form-control price">
                                                        </td>
                                                        <td>
                                                            <select name="quantite[]" class="form-control select2 quantite"
                                                                    onchange="onchangeDrog();">
                                                                <option value="1">1</option>
                                                                <option value="2">2</option>
                                                                <option value="3">3</option>
                                                                <option value="4">4</option>
                                                                <option value="5">5</option>
                                                                <option value="6">6</option>
                                                                <option value="7">7</option>
                                                                <option value="8">8</option>
                                                                <option value="9">9</option>
                                                                <option value="10">10</option>
                                                            </select>
                                                        </td>
                                                        <td>
                                                            <input class="btn btn-info w-md waves-effect waves-light"
                                                                   type="button" value="Ajouter" id="ajouter">
                                                        </td>
                                                    </tr>


                                                    <!--   Display ORDONNANCE   -->
                                                    <c:if test="${!empty taille}">
                                                        <c:forEach var="i" begin="1" end="${taille}" step="1">

                                                            <tr>
                                                                <td>
                                                                    <input name="medicament[]"
                                                                           value="${(!empty listOrdonnance) ? listOrdonnance.get(i).getPrescription() : ''}"
                                                                           type="text" class="form-control">
                                                                </td>
                                                                <td>
                                                                    <select name="quantite[]" class="form-control select2">
                                                                        <c:forEach items="${listeQuantite}" var="quantite" >
                                                                            <option value="${quantite.getIdqte()}" ${ (!empty listOrdonnance) ? (listOrdonnance.get(i).getQuantite()==quantite.getIdqte()) ? "selected" : "" : ""}>
                                                                                    ${ quantite.getLibelle() }
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>
                                                                <td>
                                                                    <select name="posologie[]" class="form-control select2">
                                                                        <c:forEach items="${listePosologie}" var="posologie" >
                                                                            <option value="${posologie.getIdpos()}" ${(!empty listOrdonnance) ? (listOrdonnance.get(i).getPosologie()==posologie.getIdpos()) ? "selected" : "" : ""}>
                                                                                    ${ posologie.getLibelle() }
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>

                                                                <td>
                                                                    <select name="dosage[]" class="form-control select2">
                                                                        <c:forEach items="${listeDosage}" var="dosage" >
                                                                            <option value="${dosage.getIddos()}" ${(!empty listOrdonnance) ? (listOrdonnance.get(i).getDosage()==dosage.getIddos()) ? "selected" : "" : ""}>
                                                                                    ${ dosage.getLibelle() }
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>

                                                                <td>
                                                                    <select name="type[]" class="form-control select2">
                                                                        <c:forEach items="${listeTypes}" var="types" >
                                                                            <option value="${types.getIdtyp()}" ${(!empty listOrdonnance) ? (listOrdonnance.get(i).getTypemedic()==types.getIdtyp()) ? "selected" : "" : ""}>
                                                                                    ${ types.getLibelle() }
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>

                                                                <td>
                                                                    <button type="button" class="ibtnDel btn btn-danger" >Supprimer</button>
                                                                </td>
                                                            </tr>

                                                        </c:forEach>
                                                    </c:if>

                                                </table>
                                            </div>
                                        </div>


                                        <div class="row">
                                            <div id="infos" style="color:red;"></div>
                                        </div>


                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <hr/>
                                                </div>
                                            </div>
                                        </div>


                                        <%--
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group row">
                                                    <label class="col-lg-3 col-form-label">Choisir une pharmacie</label>
                                                    <div class="col-lg-6">
                                                        <select id="pharmacie" name="pharmacie"
                                                                class="form-control select2">
                                                            <c:forEach items="${listePharmacie}" var="valeur" >
                                                                <option value="${valeur.getIdpharm()}"
                                                                    ${ (!empty facturation) ? (facturation.getAssurance()==valeur.getIdpharm()) ? "selected" : "" : ""}>
                                                                        ${valeur.getLibelle()}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        --%>

                                        <!--   HIDE THINGS   -->
                                        <input type="hidden" id="monurl" value="${monurl}" />


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


                <div class="row">
                    <div class="col-sm-12">
                        <div id="information"
                             style="color:red; text-align: center; font-size: 20px;">
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

<!-- Datatable init js -->
<script src="<c:url value='/pages/datatables.init.js' />"></script>

<!-- App js -->
<script src="<c:url value='/js/app.js' />"></script>

<script>
    var cptr = 0;
    var monurl = $("#monurl").val();
    //
    var listeDrogId = new Array();
    var listeDrogLib = new Array();
    var listeQuantite = new Array();
    for (var j = 1; j <= 10; j++) {
        listeQuantite.push(j);
    }

    $(document).ready(function() {
        $().ready(function () {
            $(".form-control.select2").select2();

            // Call This to get DATA :
            $.get(monurl+"getmedicamentpharm",
                function(data) {

                    cptr++;
                    var tp = "abc"+cptr;
                    //alert("Ca marche !");

                    $(data).find('item').each(function(){
                        listeDrogId.push(parseInt($(this).find('idmd').text()));
                        listeDrogLib.push($(this).find('libelle').text());
                    });

                    //
                    var tpn = "<select id='"+tp+"' name='medicamentid[]' class='form-control select2 drogid' onchange='onchangeDrog();'>";
                    for (var i = 0; i < listeDrogId.length; i++) {
                        tpn += "<option value="+listeDrogId[i]+">"+listeDrogLib[i]+
                            "</option>";
                    }
                    tpn += "</select>";

                    //
                    var info = document.getElementById("addmedicament");
                    info.innerHTML = tpn;

                    // Action :
                    $("#"+tp).select2();

                    // Call to initialize :
                    onchangeDrog();
                }
            );

        });

        if($(".ibtnDel").length ) {
            //alert("OK existe !");
            $("#tabinfo").on("click", ".ibtnDel", function (event) {
                // Supprimer la ligne correspondante :
                $(this).closest("tr").remove();
            });
        }
    });

    //
    $('#ajouter').click(function() {
        cptr++;

        var tp = "abc"+cptr;

        // tabinfo
        var idTab = "#tabinfo";

        var $tableBody = $(idTab);
        var $trLast = $tableBody.find("tr:last");
        var $trNew = $trLast.clone(true);

        $trNew.find("td:last").html(
            '<button type="button" class="ibtnficDel btn btn-danger" >Supprimer</button>');

        // Change the PRICE field :
        var tpchamp = tp+"aa";
        $trNew.find("td:eq(1)").html(
            "<input id='"+tpchamp+"' name='prixdrog[]' onfocusout='sommeTotal()' value='' type='text' class='form-control price'>"
        );

        // add the DROG colums :
        var tpn = "<select id='"+tp+"' name='medicamentid[]' class='form-control select2 "+tp+"' "+
            "onchange=onchangeDrogTp('"+tp+"','"+tpchamp+"')>";
        for (var i = 0; i < listeDrogId.length; i++) {
            tpn += "<option value="+listeDrogId[i]+">"+listeDrogLib[i]+
                "</option>";
        }
        tpn += "</select>";
        $trNew.find("td:first").html(tpn);

        // add Quantite :
        var tpq = "<select name='quantite[]' class='form-control select2 quantite "+tp+"' onchange=onchangeDrogTp('"+tp+"','"+tpchamp+"')>";
        for (var j = 0; j < 10; j++) {
            tpq += "<option value="+listeQuantite[j]+">"+listeQuantite[j]+
                "</option>";
        }
        tpq += "</select>";
        $trNew.find("td:eq(2)").html(tpq);

        // Delete
        $("#tabinfo").on("click", ".ibtnficDel", function (event) {
            // Supprimer la ligne correspondante :
            $(this).closest("tr").remove();
            sommeTotal();
        });

        $trLast.after($trNew);
        $("."+tp).select2();

        // Call to initialize :
        onchangeDrogTp(tp,tpchamp);
    });


</script>
</body>
</html>

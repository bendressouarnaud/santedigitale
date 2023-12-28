var tampon_1 = 0;
var m_x = setInterval(tester, 2000);

var tabMedecin = new Array();
var tabPatient = new Array();

!function(d){
	"use strict";
	var r=function(){};

	//$.get("http://localhost:8080/gestion/getpatientmedecin",
	$.get("http://www.gestdp.com/gestion/getpatientmedecin",
		function(data) {
		    var j=0;
			$(data).find('item').each(function(){
                var medecin = $(this).find('jour').text();
                var patient = parseInt($(this).find('nombre').text());

       		    // Set :
       		    //alert("Nom : "+medecin);
       		    tabMedecin[j] = medecin;
       		    tabPatient[j] = patient;
       		    j = j +1;
			});

			// Stopper :
            tampon_1 = 1;

			r.prototype.respChart=function(r,o,e,a){
				var t=r.get(0).getContext("2d"),n=d(r).parent();
				function i(){
					r.attr("width",d(n).width());
					switch(o){
						case"Line":
							new Chart(t,{type:"line",data:e,options:a});
							break;
						case"Doughnut":
							new Chart(t,{type:"doughnut",data:e,options:a});
							break;
						case"Pie":
							new Chart(t,{type:"pie",data:e,options:a});
							break;
						case"Bar":
							new Chart(t,{type:"bar",data:e,options:a});
							break;
						case"Radar":
							new Chart(t,{type:"radar",data:e,options:a});
							break;
						case"PolarArea":
							new Chart(t,{data:e,type:"polarArea",options:a})
					}
				}
				d(window).resize(i),i()
			},
			r.prototype.init=function(){
				this.respChart(d("#bar"),"Bar",{
					labels:tabMedecin,
					datasets:[
						{label:"Patients",backgroundColor:"#02a499",borderColor:"#02a499",borderWidth:1,hoverBackgroundColor:"#02a499",hoverBorderColor:"#02a499",data:tabPatient}
						]
					}
				)
			},
			d.ChartJs=new r,d.ChartJs.Constructor=r
        }
    );


}
(window.jQuery)
/*,
function(r){
	"use strict";
	window.jQuery.ChartJs.init()
}()*/
;

function tester(){
    if(tampon_1 == 1){
        clearInterval(m_x);
        window.jQuery.ChartJs.init();
    }
}
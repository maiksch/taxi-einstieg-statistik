// Java Script zum Abfangen von fehlerhaften Anfragen, Definition der Map und aufruf des XML-Service

$(document).ready(function(){
	//Funktion beim Laden der index.html
function starttest() {
		var query;
		//Überprüfung, ob ab und bis Zeit gesetzt wurde
		if($(location).attr('search') == "") {
			//$(location).attr({search: '?ab=00:00&bis=23:59'})
			$('#fehler').addClass('alert-success');
			$('#fehler').html("<strong>Angezeigte Daten:</strong> von 00:00 bis 23:59");	
			query = "ab=00:00&bis=23:59";
		} 
		else {
		//Daten wurden zeitlich eingegrenzt 
		//Überprüfen ob ab und bis Zeit 5-stellig
		if((decodeURI(getUrlVars().ab).replace("%3A", ":").length == 5) && (decodeURI(getUrlVars().bis).replace("%3A", ":").length == 5)) {
		
			dates_ab = document.getElementById("ab").value.split(":");
			dates_bis = document.getElementById("bis").value.split(":");
			//Überprüfen ob Zahlen eingegeben wurden
			if(isNaN(dates_ab[0]) || isNaN(dates_ab[1]) || isNaN(dates_bis[0]) || isNaN(dates_bis[1])){
				$('#fehler').addClass('alert-danger');
				$('#fehler').html("<strong>Fehler:</strong> Falsches Zeit Format (hh:mm): <a href='?ab=00:00&bis=23:59'>Neu laden</a>");
				query = "ab=00:00&bis=00:00";
			} 
			else {
				
				//Erstellung eines Datums, um zeitlichen Abstand vergleichen zu können			
				var date1 = new Date (2014,10,7,dates_ab[0],dates_ab[1],00);
				document.getElementById("ab").value = ('0' + date1.getHours()).slice(-2) + ":" + ('0' + date1.getMinutes()).slice(-2);
				var date2 = new Date (2014,10,7,dates_bis[0],dates_bis[1],00);
				document.getElementById("bis").value = ('0' + date2.getHours()).slice(-2) + ":" + ('0' + date2.getMinutes()).slice(-2);
				//AbZeit liegt nach BisZeit
				if(date1.getTime() > date2.getTime()) {
					$('#fehler').addClass('alert-danger');
					$('#fehler').html("<strong>Fehler:</strong> AbZeit größer als BisZeit: <a href='?ab=00:00&bis=23:59'>Neu laden</a>");
					query = "ab=00:00&bis=23:59";
				}
				//Daten Korrekt eingegeben
				else {
				$('#fehler').addClass('alert-success');
				$('#fehler').html("<strong>Angezeigte Daten:</strong> von " + ('0' + date1.getHours()).slice(-2) + ":" + ('0' + date1.getMinutes()).slice(-2) +" bis " + ('0' + date2.getHours()).slice(-2) + ":" + ('0' + date2.getMinutes()).slice(-2));	
				query = "ab=" + ('0' + date1.getHours()).slice(-2) + ":" + ('0' + date1.getMinutes()).slice(-2) +"&bis=" + ('0' + date2.getHours()).slice(-2) + ":" + ('0' + date2.getMinutes()).slice(-2);
				
				}
			}
		}
		//Daten im falschen Format eingegeben
		else {
			$('#fehler').addClass('alert-danger');
				$('#fehler').html("<strong>Fehler:</strong> Falsches Zeit Format (hh:mm): <a href='?ab=00:00&bis=23:59'>Neu laden</a>");
			query = "ab=00:00&bis=00:00";
			}
		 }
		 //Rückgabe der Variablen, um XML-Service einzugrenzen
		return query;
	}
	
		var QueryString = function () {
	  // This function is anonymous, is executed immediately and 
	  // the return value is assigned to QueryString!
	  //Ausgabe der Url-Search-Variabelen (Anwendung hier ab/bis Zeit)
	  var query_string = {};
	  var query = window.location.search.substring(1);
	  var vars = query.split("&");
	  for (var i=0;i<vars.length;i++) {
		var pair = vars[i].split("=");
			// If first entry with this name
		if (typeof query_string[pair[0]] === "undefined") {
		  query_string[pair[0]] = decodeURIComponent(pair[1]);
			// If second entry with this name
		} else if (typeof query_string[pair[0]] === "string") {
		  var arr = [ query_string[pair[0]],decodeURIComponent(pair[1]) ];
		  query_string[pair[0]] = arr;
			// If third or later entry with this name
		} else {
		  query_string[pair[0]].push(decodeURIComponent(pair[1]));
		}
	  } 
			$('#ab').attr({value: query_string.ab})
			$('#bis').attr({value: query_string.bis})
		  
	  return 0;
	}();
		//Eintrübungsgröße auf der Map
	  var blur = document.getElementById('blur');
		//Radius der Punkte auf der Map
      var radius = document.getElementById('radius');
      var vector = new ol.layer.Heatmap({
        source: new ol.source.Vector({
			//Aufruf des XML-Service mit eingrenzenden Variablen
			 url: "/rest/TaxiEinstiegStatistik?" + starttest(),
			 format: new ol.format.KML({
            extractStyles: false
          })
        }),
        blur: parseInt(blur.value, 10),
        radius: parseInt(radius.value, 10)
      });
	  
      vector.getSource().on('addfeature', function(event) {
        // /rest/TaxiEinstiegStatistik stores the magnitude of each entry in a
        // standards-violating <magnitude> tag in each Placemark.  We extract it from
        // the Placemark's name instead.
        var name = event.feature.get('name');
        var magnitude = parseFloat(name.substr(2));
        event.feature.set('weight', magnitude - 5);
      });
      var raster = new ol.layer.Tile({
        source: new ol.source.OSM({
          layer: 'toner'
        })
      });
      var map = new ol.Map({
        layers: [raster, vector],
        target: 'map',
        view: new ol.View({
          center: ol.proj.fromLonLat([10.521111,52.269167]),
          zoom: 12
        })
      });
      blur.addEventListener('input', function() {
        vector.setBlur(parseInt(blur.value, 10));
      });
      radius.addEventListener('input', function() {
        vector.setRadius(parseInt(radius.value, 10));
      });
	  
	  //Funktion, die die Uhrzeit auf der Url ausließt 
	  function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}
});
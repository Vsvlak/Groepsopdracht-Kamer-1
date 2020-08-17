function maakDropDowns(){
    maakKlassenDropDown();
    maakVakkenDropdown();
}

function toonVakken(){
    //TODO: Error oplossen wanneer vakken lijst nog leeg is
    var klasid = document.getElementById("kiesklas").value;
    klasid = klasid.split(".")[0];
    document.getElementById("tabel").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            if (this.responseText.length > 0){
                var info = JSON.parse(this.responseText);
                document.getElementById("tabel").innerHTML += "<tr><td><b>Vak</b></td><td><b>Docent</b></td></tr>"
                for (var x = 0; x < info.length; x++) {
                    //Updaten naar verwerken van DocentVakDto
                    document.getElementById("tabel").innerHTML += 
                    "<tr><td>" + info[x].vaknaam + "</td>" +
                    "<td>" + info[x].docentAchternaam + "</td></tr>";
                }
            } else{
                document.getElementById("tabel").innerHTML += "<tr><td> Deze klas volgt nog geen vakken </td></tr>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/vakkenVanKlas/"+klasid, true);
    xhr.send();
}

function voegVakToe(){
    var vakid = document.getElementById("kiesvak").value;
    vakid = vakid.split(".")[0];
    var klasid = document.getElementById("kiesklas").value;
    klasid = klasid.split(".")[0];
    var docentid = document.getElementById("kiesdocent").value;
    docentid = docentid.split(".")[0];

    var docentvak = {"vakid":vakid, "docentid":docentid};
    var json = JSON.stringify(docentvak);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {    
        if (xhttp.readyState == 4) {
            toonVakken();
        }
    };
    xhttp.open("POST", "http://localhost:8082/api/voegDocentVakToe/" + klasid, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(json);
    //TODO: Auto refresh van leerlingen lijst toevoegen
}
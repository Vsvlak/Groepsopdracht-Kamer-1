function maakDropDowns(){
    maakKlassenDropDown();
    maakVakkenDropdown();
    //TODO: Toon leerlingenlijst van geselecteerde klas
}

function toonVakken(klasid){
    //TODO: Error oplossen wanneer vakken lijst nog leeg is
    klasid = klasid.split(".")[0];
    document.getElementById("tabel").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            if (this.responseText.length > 0){
                var info = JSON.parse(this.responseText);
                for (var x = 0; x < info.length; x++) {
                    document.getElementById("tabel").innerHTML += 
                    "<tr><td>" + info[x].naam + "</td>";
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
    var tekst = document.getElementById("kiesvak").value;
    var vakid = tekst.split(".")[0];
    var tekst2 = document.getElementById("kiesklas").value;
    var klasid = tekst2.split(".")[0];
    var vakklas = {"klasid":klasid, "vakid":vakid};
    var json = JSON.stringify(vakklas);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8082/api/voegVakToe", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(json);
    //TODO: Auto refresh van leerlingen lijst toevoegen
}
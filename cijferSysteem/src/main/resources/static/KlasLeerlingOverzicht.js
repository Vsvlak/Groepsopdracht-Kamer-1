function maakDropDowns(){
    maakKlassenDropDown();
    maakLeerlingenDropdown();
}

function maakKlassenDropDown(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("kiesklas").innerHTML += "<option>" + info[x].id + "</option>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/klassenOverzicht", true);
    xhr.send();
}

function maakLeerlingenDropdown(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("kiesleerling").innerHTML += "<option>" + info[x].id + "</option>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/leerlingOverzicht", true);
    xhr.send();
}

function toonLeerlingen(klasid){
    document.getElementById("tabel").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("tabel").innerHTML += 
                "<tr><td>" + info[x].naam + "</td>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/leerlingenInKlas/"+klasid, true);
    xhr.send();
}

function voegLeerlingToe(){
    var leerlingid = document.getElementById("kiesleerling").value;
    //value van dropdown is nog klasnaam, moet id worden...
    var klasid = document.getElementById("kiesklas").value;
    var leerlingklas = {"klasid":klasid, "leerlingid":leerlingid};
    var json = JSON.stringify(leerlingklas);
    console.log(leerlingid + " " + klasid);
    //var leerling = getLeerlingById(leerlingid);
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8082/api/voegLeerlingToe", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(json);
}

function getLeerlingById(id){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            return this.responseText;
        }
    }
    xhr.open("GET", "http://localhost:8082/leerling/"+id);
    xhr.send();
}
function paginaLaden(){  
    maakDocentenDropdown();
    maakVakkenDropdownVoorDocent();
    laatToetsenZien();
}

function laatToetsenZien() {
    document.getElementById("tabel").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            try{
                var info = JSON.parse(this.responseText);
                document.getElementById("tabel").innerHTML += "<tr><td><b>Datum</b></td><td><b>Tijd</b></td><td><b>Vak</b></td></tr>"

                for (var x = 0; x < info.length; x++) {
                    document.getElementById("tabel").innerHTML += "<tr>" +
                        "<td>" + info[x].datum + "</td>" +
                        "<td>" + info[x].tijd + "</td>" +
                        "<td>" + info[x].vak + "</td>" +
                        "<td><img src='EditButton.png' class='editB' id=editButton" + x + " style='height:20px;width20px;'></td>" +
                        "</tr>";
                }
            } catch(err){
                document.getElementById("tabel").innerHTML += "<tr><td> Er staan nog geen toetsen in het toetsenoverzicht </td></tr>";
            }
            document.getElementById("tabel").innerHTML += "<button onclick = openModal(document.getElementById('modal'))>+ Toets</button>";
        }
    }
    xhr.open("GET", "http://localhost:8082/toetsOverzicht", true);
    xhr.send();
}

    function maakToetsAan(){
        var docentId = document.getElementById("kiesdocent").value;
        docentId = docentId.split(".")[0];
        var datum = document.getElementById("datumInput").value;
        var vak = document.getElementById("kiesvak").value;
        vak = vak.split(".")[0];
        var tijd = document.getElementById("tijdInput").value;
        var toets = '{"docentId":"'+docentId+'","datum":"'+datum+'","tijd":"'+tijd+'","vakId":"'+vak+'"}';
        postData(toets);
    }

function postData(toets) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8082/api/maakToets", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(toets);
}


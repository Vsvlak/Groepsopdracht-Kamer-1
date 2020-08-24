function paginaLaden() {
    maakDocentenDropdown();
    laatToetsenZien();
}

function laatToetsenZien() {
    document.getElementById("tabel").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            try {
                var info = JSON.parse(this.responseText);
                document.getElementById("tabel").innerHTML += "<tr><td><b>Datum</b></td><td><b>Tijd</b></td><td><b>Vak</b></td><td><b>Docent</b></td><td><b>Klas</b></td></tr>"

                for (var x = 0; x < info.length; x++) {
                    document.getElementById("tabel").innerHTML += "<tr>" +
                        "<td>" + info[x].datum + "</td>" +
                        "<td>" + info[x].tijd + "</td>" +
                        "<td>" + info[x].vak + "</td>" +
                        "<td>" + info[x].docent + "</td>" +
                        "<td>" + info[x].klas + "</td>" +
                        "<td><img src='EditButton.png' class='editB' id=editButton" + x + " style='height:20px;width20px;'></td>" +
                        "</tr>";
                }
            } catch (err) {
                document.getElementById("tabel").innerHTML += "<tr><td> Er staan nog geen toetsen in het toetsenoverzicht </td></tr>";
            }
            document.getElementById("tabel").innerHTML += "<button onclick = openModal(document.getElementById('modal'))>+ Toets</button>";
        }
    }
    xhr.open("GET", "http://localhost:8082/toetsOverzicht", true);
    xhr.send();
}

function maakToetsAan() {
    var docentId = document.getElementById("kiesdocent").value;
    docentId = docentId.split(".")[0];
    var datum = document.getElementById("datumInput").value;
    var vak = document.getElementById("kiesvak").value;
    vak = vak.split(".")[0];
    var tijd = document.getElementById("tijdInput").value;
    var klas =  document.getElementById("kiesklas").value.split(".")[0];
    var toets = '{"docentId":"' + docentId + '","datum":"' + datum + '","tijd":"' + tijd + '","vakId":"' + vak + '","klasId":"' + klas +'"}';
    postData(toets);
    modal.classList.remove('active')
    overlay.classList.remove('active')
}

function postData(toets) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {    
        if (xhttp.readyState == 4) {
            laatToetsenZien();
        }
    };
    xhttp.open("POST", "http://localhost:8082/api/maakToets", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(toets);
}


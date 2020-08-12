function laatLeerlingenTabelZien() {
    document.getElementById("leerlingOproep").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var alleinfo = JSON.parse(this.responseText);

            document.getElementById("tabel").innerHTML += "<tr><td><b>Id.</b></td><td><b>Voornaam</b></td><td><b>Achternaam</b></td><td><b>Geboortedatum</b></td><td><b>Studentnr.</b></td><td></td></tr>"

            for (var x = 0; x < alleinfo.length; x++) {
                document.getElementById("tabel").innerHTML += "<tr>" +

                    "<td>" + alleinfo[x].id + "</td>" + //IK DENK DAT WE DEZE REGEL WEG MOETEN LATEN. ID IS NUTTELOOS OM TE LATEN ZIEN AAN EEN DOCENT
                    "<td>" + alleinfo[x].voornaam + "</td>" +
                    "<td>" + alleinfo[x].achternaam + "</td>" +
                    "<td>" + moment(alleinfo[x].geboorteDatum).format('DD-MM-YYYY') + "</td>" +
                    "<td>" + alleinfo[x].leerlingNummer + "</td>" +
                    "<td><img src='EditButton.png' class='editB' id=editButton" + x + " style='height:20px;width20px;'></td>" +
                    "</tr>";
            }
            document.getElementById("tabel").innerHTML += "<button data-modal-target='#modal'>+ Leerling</button>";
        }
    }
    xhr.open("GET", "http://localhost:8082/leerlingOverzicht", true);
    xhr.send();
}

function maakLeerlingAan() {
    var voornaamInput = document.getElementById("voornaamInput").value;
    var achternaamInput = document.getElementById("achternaamInput").value;
    var geboortedatumInput = document.getElementById("geboortedatumInput").value;
    var leerling = '{"voornaam":"' + voornaamInput + '","achternaam":"' + achternaamInput + '","geboortedatum":"' + geboortedatumInput + '"}';
    postData(leerling);
}

function postData(leerling) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8082/api/maakLeerling", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(leerling);
}
function laatKlassenZien() {
    document.getElementById("tabel").innerHTML = "";

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            document.getElementById("tabel").innerHTML += "<tr><td><b>Naam</b></td><td><b>Niveau</b></td></tr>"

            for (var x = 0; x < info.length; x++) {
                document.getElementById("tabel").innerHTML += "<tr>" +
                    "<td>" + info[x].naam + "</td>" +
                    "<td>" + info[x].niveau + "</td>" +
                    "<td><img src='EditButton.png' class='editB' id=editButton" + x + " style='height:20px;width20px;'></td>" +
                    "</tr>";
            }
        }

    }
    xhr.open("GET", "http://localhost:8082/klassenOverzicht", true);
    xhr.send();
}

function maakKlasAan() {
    var naam = document.getElementById("naamInput").value;
    var niveau = document.getElementById("niveauInput").value;
    var klas = '{"naam":"' + naam + '","niveau":"' + niveau + '"}';
    postData(klas);
}

function postData(klas) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8082/api/maakKlas", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(klas);
}


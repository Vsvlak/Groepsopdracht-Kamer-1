function laatVakkenZien() {
    document.getElementById("tabel").innerHTML = "";

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            document.getElementById("tabel").innerHTML += "<tr><td><b>Naam</b></td></tr>"

            for (var x = 0; x < info.length; x++) {
                document.getElementById("tabel").innerHTML += "<tr>" +
                    "<td>" + info[x].naam + "</td>" +
                    "<td><img src='EditButton.png' class='editB' id=editButton" + x + " style='height:20px;width20px;'></td>" +
                    "</tr>";
            }
            document.getElementById("tabel").innerHTML += "<button onclick = openModal(document.getElementById('modal'))>+ Vak</button>";
        }

    }
    xhr.open("GET", "http://localhost:8082/vakkenOverzicht", true);
    xhr.send();
}

    function maakVakAan(){
        var naam = document.getElementById("naamInput").value;
        var vak = '{"naam":"'+naam+'"}';
        postData(vak);
    }

    function postData(vak){
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "http://localhost:8082/api/maakVak", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(vak);
    }


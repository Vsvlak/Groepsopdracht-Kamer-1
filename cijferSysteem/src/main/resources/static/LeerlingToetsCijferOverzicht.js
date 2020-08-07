function toonCijfers(leerlingid){
    //TODO: Error oplossen wanneer leerlingen lijst nog leeg is
    leerlingid = leerlingid.split(".")[0];
    document.getElementById("tabel").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            console.log(this.responseText);
            var info = JSON.parse(this.responseText);
            //TODO: cijfer_id vervangen door Toets gegevens
            for (var x = 0; x < info.length; x++) {
                document.getElementById("tabel").innerHTML += 
                "<tr><td>cijfer_id</td><td>Cijfer</td><tr>" +
                "<tr><td>" + info[x].id + "</td>" +
                "<td>" + info[x].cijfer + "</td>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/cijfersVanLeerling/"+leerlingid, true);
    xhr.send();
}

function maakLeerlingenDropdown(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("kiesleerling").innerHTML += "<option>" + info[x].id + ". " + info[x].voornaam + " " + info[x].achternaam + "</option>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/leerlingOverzicht", true);
    xhr.send();
}
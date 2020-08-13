function maakDropdowns(){
    maakDocentenDropdown();
    maakVakkenDropdown();
    maakKlassenDropDown();
}

function maakCijferOverzicht(){
    var docentid = document.getElementById("kiesdocent").value.split(".")[0];
    var vakid = document.getElementById("kiesvak").value.split(".")[0];
    var klasid = document.getElementById("kiesklas").value.split(".")[0];
    document.getElementById("tabel").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        console.log(xhr.readyState);
        if (xhr.readyState == 3) {
            console.log(this.responseText);
            var info = JSON.parse(this.responseText);
            console.log(info);
            console.log("length " + info.length);
            document.getElementById("tabel").innerHTML += "<tr><td>Naam leerling</td><td>Cijfer</td><td>Cijfer 2</td><tr>";
            for (var x = 0; x < info.length; x++) {
                console.log(info[x]);
                console.log(info[x].leerlingnaam);
                document.getElementById("tabel").innerHTML +=
                "<tr><td>" + info[x].leerlingnaam + "</td>" +
                "<td>" + info[x].cijfers[0] + "</td>" +
                "<td>" + info[x].cijfers[1] + "</td>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/toonToetsenVan/" + docentid + "/" + vakid + "/" + klasid, true);
    xhr.send();
}
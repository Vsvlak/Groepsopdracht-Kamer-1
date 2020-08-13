function maakDropdowns(){
    maakDocentenDropdown();
    maakVakkenDropdown();
    maakKlassenDropDown();
}

function maakCijferOverzicht(docentid, vakid, klasid){
    document.getElementById("tabel").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            console.log(this.responseText);
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                // document.getElementById("tabel").innerHTML += 
                // "<tr><td>cijfer_id</td><td>Cijfer</td><tr>" +
                // "<tr><td>" + info[x].id + "</td>" +
                // "<td>" + info[x].cijfer + "</td>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/cijfersDocentVakKlas/"+leerlingid, true);
    xhr.send();
}
}
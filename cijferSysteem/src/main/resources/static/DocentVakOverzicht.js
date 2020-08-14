function laatDocentVakTabelZien() {
    document.getElementById("tabel").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            try{
                var alleinfo = JSON.parse(this.responseText);

            document.getElementById("tabel").innerHTML += "<tr><td><b>Id.</b></td><td><b>DocentId</b></td><td><b>VakId</b></td><td><b>KlasId</b></td></tr>"

                for (var x = 0; x < alleinfo.length; x++) {
                    document.getElementById("tabel").innerHTML += "<tr>" +
                    "<td>" + alleinfo[x].id + "</td>" + 
                    "<td>" + alleinfo[x].docentid + "</td>" +
                    "<td>" + alleinfo[x].vakid + "</td>" +
                    "<td>" + alleinfo[x].klasid + "</td>" +
                    "<td><img src='EditButton.png' class='editB' id=editButton" + x + " style='height:20px;width20px;'></td>" +
                    "</tr>";
                }
            } catch(err){
                document.getElementById("tabel").innerHTML += "<tr><td> Er staan nog geen DocentVakken in het DocentVakkenOverzicht </td></tr>";
            }
            document.getElementById("tabel").innerHTML += "<button onclick = openModal(document.getElementById('modal'))>+ DocentVak</button>";
        }
    }
    xhr.open("GET", "http://localhost:8082/docentVakOverzicht", true);
    xhr.send();
}

function maakDocentVakAan() {

}
function getToetsById(id){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            return this.responseText;
        }
    }
    xhr.open("GET", "http://localhost:8082/toets/"+id);
    xhr.send();
}


function toonToetsen(vakid){
    vakid = vakid.split(".")[0];
    document.getElementById("tabel").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("tabel").innerHTML += 
                "<tr><td>" + info[x].datum + "</td>" +
                "<td>" + info[x].tijd + "</td></tr>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/toetsenVanVak/"+vakid, true);
    xhr.send();
}
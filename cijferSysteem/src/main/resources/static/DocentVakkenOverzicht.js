function maakDropDowns(){
    maakDocentenDropdown();
    maakVakkenDropdown();
}

function toonVakken(docentid){
    docentid = docentid.split(" ")[0];
    document.getElementById("tabel").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("tabel").innerHTML += 
                "<tr><td>" + info[x].naam + "</td>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/vakkenVanDocent/"+docentid, true);
    xhr.send();
}

function voegVakkenToe(){
    var vakid = document.getElementById("kiesvak").value;
    var docentid = document.getElementById("kiesdocent").value;
    docentid = docentid.split(" ")[0];
    vakid = vakid.split(" ")[0];
    alert(docentid + "   " +  vakid);

    var vakdocent = {"docentid":docentid, "vakid":vakid};
    var json = JSON.stringify(vakdocent);
    console.log(vakid + " " + docentid);
   
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8082/api/maakDocentAanVak" , true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(json);
}

function getVakById(id){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            return this.responseText;
        }
    }

    xhr.open("GET", "http://localhost:8082/vakkenVanDocent/"+id); // /hier stond vaken
    xhr.send();
}
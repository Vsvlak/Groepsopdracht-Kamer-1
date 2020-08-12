function maakDropDowns(){
    maakLeerlingenDropdown();
}

function toonLeerlingen(leerlingid){
    leerlingid = id.split(".")[0];
    document.getElementById("tabelA").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("tabelA").innerHTML += 
                "<tr><td>" + info[x].voornaam + "</td>" +
                "<td>" + info[x].achternaam + "</td>" +
                "<td>" + info[x].geboorteDatum + "</td>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/leerlingOverzicht/", true);
    xhr.send();
}

function laatVakCijferTabelZien() {

    document.getElementById("tabel").innerHTML = "";

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var alleinfo = JSON.parse(this.responseText);

            document.getElementById("tabel").innerHTML += "<tr><td><b> VAKid </b></td><td><b>VAK</b></td><td><b>Toets1</b></td><td><b>Toets2</b></td></tr>"

            for (var x = 0; x < alleinfo.length; x++) {
                document.getElementById("tabel").innerHTML += "<tr>" +
                "<td id=vakPass"+[x]+">" + alleinfo[x].id + "</td>" +
                    "<td id=vakPass"+[x]+">" + alleinfo[x].naam + "</td>" +
                     "<td id=cijferVanVakInDezeRow></td>" +
                    //  "<td id=idPass"+x+">"+alleinfo[x].id+"</td>"+ 
                   
                    "</tr>";
                    doorgeefId = alleinfo[x].id;
                    toonToetsen(doorgeefId);
            }
            
        }
        
    }

   
    xhr.open("GET", "http://localhost:8082/vakkenOverzicht", true);
    xhr.send();

}
function toonToetsen(doorgeefId){
    console.log("dit is id van vak " + doorgeefId);
    document.getElementById("cijferVanVakInDezeRow").innerHTML = "DATA ToetsWis1";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("hier").innerHTML += 
                "<tr><td>" + info[x].datum + "</td>" +
                "<td>" + info[x].tijd + "</td></tr>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/toetsenVanVak/"+doorgeefId, true);
    xhr.send();
}


// get cijfers per vak
//get vak id bepaald de Row van het cijfer
//get toets id bepaald de colom

// function toonCijfers(leerlingid){
//     //TODO: Error oplossen wanneer leerlingen lijst nog leeg is
//     leerlingid = leerlingid.split(".")[0];
//     document.getElementById("tabel").innerHTML = "";
//     let xhr = new XMLHttpRequest();
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState == 3) {
//             console.log(this.responseText);
//             var info = JSON.parse(this.responseText);
//             //TODO: cijfer_id vervangen door Toets gegevens
//             for (var x = 0; x < info.length; x++) {
//                 document.getElementById("tabel").innerHTML += 
//                 "<tr><td>cijfer_id</td><td>Cijfer</td><tr>" +
//                 "<tr><td>" + info[x].id + "</td>" +
//                 "<td>" + info[x].cijfer + "</td>";
//             }
//         }
//     }
//     xhr.open("GET", "http://localhost:8082/cijfersVanLeerling/"+leerlingid, true);
//     xhr.send();
// }

// function maakLeerlingenDropdown(){
//     let xhr = new XMLHttpRequest();
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState == 3) {
//             var info = JSON.parse(this.responseText);
//             for (var x = 0; x < info.length; x++) {
//                 document.getElementById("kiesleerling").innerHTML += "<option>" + info[x].id + ". " + info[x].voornaam + " " + info[x].achternaam + "</option>";
//             }
//         }
//     }
//     xhr.open("GET", "http://localhost:8082/leerlingOverzicht", true);
//     xhr.send();
// }
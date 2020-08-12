function maakDropDowns(){
    maakLeerlingenDropdown();
    maakVakkenDropdown();
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

                    
            }
            
        }
        
    }

   
    xhr.open("GET", "http://localhost:8082/vakkenOverzicht", true);
    xhr.send();

}

// get cijfers per vak
//get vak id bepaald de Row van het cijfer
//get toets id bepaald de colom

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
    document.getElementById("cijferVanVakInDezeRow").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("cijferVanVakInDezeRow").innerHTML += 
                "<tr><td>" + info[x].datum + "</td>" +
                "<td>" + info[x].tijd + "</td></tr>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/toetsenVanVak/"+vakid, true);
    xhr.send();
}
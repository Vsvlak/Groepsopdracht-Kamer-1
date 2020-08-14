function laatDocentenTabelZien() {

    document.getElementById("tabel").innerHTML = "";

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            try{
                var alleinfo = JSON.parse(this.responseText);
            document.getElementById("tabel").innerHTML += "<tr><td><b>Id.<b></td><td><b>Voornaam</b></td><td><b>Achternaam</b></td></tr>"
            for (var x = 0; x < alleinfo.length; x++) {
                document.getElementById("tabel").innerHTML += "<tr>" +
                    "<td id=idPass"+[x]+">"+alleinfo[x].id+"</td>"+ 
                    "<td id=voornaamPass"+[x]+">" + alleinfo[x].voornaam + "</td>" +
                    "<td id=achternaamPass"+[x]+">" + alleinfo[x].achternaam + "</td>" +
                    "<td><img src='EditButton.png' class='editB' id=editButton"+ x + " style='height:20px;width20px;' Onclick= editDocent("+ x + ")></td>" +
                    "</tr>";                
                }
            } catch(err){
                document.getElementById("tabel").innerHTML += "<tr><td> Er staan nog geen docenten in het docentenoverzicht </td></tr>";
            }
            document.getElementById("tabel").innerHTML += "<button onclick = openModal(document.getElementById('modal'))>+ Docent</button>";
            document.getElementById("tabel").innerHTML += "<button onclick = laatDocentenTabelZien()> Refresh </button>";
        }
        
    }

   
    xhr.open("GET", "http://localhost:8082/docentOverzicht", true);
    xhr.send();

}
function editDocent(x){ 
    openModal(document.getElementById("editModal"));

   console.log("tijd om te editten in rij : " + x);

    var y = document.getElementById("tabel").rows[x+1].innerHTML; // plus 1, zodat hij de tabeltitels skipt
    console.log("DOCENT SELECTED:   " + y);


   var w = document.getElementById("voornaamPass"+x).innerHTML;
   var w2 = document.getElementById("idPass"+x).innerHTML;
   var w3 = document.getElementById("achternaamPass"+x).innerHTML;



    console.log("SELECTEREN DOCENT BIJ VOORNAAM: " + w + " ---- bij waarde x = " + x);
    console.log("SELECTEREN VAN DOCENT BIJ ID:  " + w2);
    console.log("SELECTEREN VAN DOCENT BIJ ACHTERNAAM:  " + w3);

   // var x = document.getElementById("voornaamPass"+ x).innerHTML = "APPEL"; // zo editten!! met de hand

    //w4 = document.getElementById("idEdit").innerHTML;
    document.getElementById("idEdit").value = w2;
    document.getElementById("voornaamEdit").value = w;
    document.getElementById("achternaamEdit").value = w3;

    console.log(" HIERRR " + w2);


}

function postEditData() {
   // console.log("HIER MOET DOCENT ID STAAN: " + idDocent);
   var idEdit =  document.getElementById("idEdit").value;
   var voornaamEdit =  document.getElementById("voornaamEdit").value;
   var achternaamEdit =  document.getElementById("achternaamEdit").value;

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8082/api/editDocent/"+  idEdit, true);
    xhttp.setRequestHeader("Content-type", "application/json");

    var docentEdit = '{"voornaam":"' + voornaamEdit + '","achternaam":"' + achternaamEdit +'","id":"' + idEdit +'"}';
    xhttp.send(docentEdit);

    editModal.classList.remove('active');
    overlay.classList.remove('active');
    

}


function maakDocentAan() {
    var voornaamInput = document.getElementById("voornaamInput").value;
    var achternaamInput = document.getElementById("achternaamInput").value;
    var docent = '{"voornaam":"' + voornaamInput + '","achternaam":"' + achternaamInput + '"}';
    postData(docent);
    modal.classList.remove('active')
    overlay.classList.remove('active')
}

function postData(docent) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "http://localhost:8082/api/maakDocent", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(docent);
}


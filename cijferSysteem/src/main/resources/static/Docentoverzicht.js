function laatDocentenTabelZien(vakid) {

    document.getElementById("tabel").innerHTML = "";

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var alleinfo = JSON.parse(this.responseText);

            document.getElementById("tabel").innerHTML += "<tr><td><b>voornaam</b></td><td><b>achternaam</b></td><td><b>docentid</b></td></tr>"


            for (var x = 0; x < alleinfo.length; x++) {
                document.getElementById("tabel").innerHTML += "<tr>" +
                    "<td id=voornaamPass"+[x]+">" + alleinfo[x].voornaam + "</td>" +
                    "<td>" + alleinfo[x].achternaam + "</td>" +
                     "<td id=idPass"+x+">"+alleinfo[x].id+"</td>"+ 
                    "<td><img src='EditButton.png' class='editB' id=editButton"+ x + " style='height:20px;width20px;' Onclick= editDocent("+ x + ")></td>" +
                    "</tr>";

                    
            }
            
        }
        
    }

   
    xhr.open("GET", "http://localhost:8082/docentOverzicht", true);
    xhr.send();

}
function editDocent(x){ 

   console.log("tijd om te editten in rij : " + x);
    
    //var rowSelected = document.getElementById.rows[x].innerHTML;

    var y = document.getElementById("tabel").rows[x+1].innerHTML; // plus 1, zodat hij de tabeltitels skipt
    console.log("DOCENT SELECTED:   " + y);
   // console.log("HOE ALLEEN VOORNAAM? " + y.getElementById("voornaamPass"));



   var w = document.getElementById("voornaamPass"+x).innerHTML;
   var w2 = document.getElementById("idPass"+x).innerHTML;


    console.log("SELECTEREN DOCENT BIJ VOORNAAM: " + w + " ---- bij waarde x = " + x);
    console.log("SELECTEREN VAN DOCENT BIJ ID:  " + w2);


    var docent = document.getElementById("voornaamPass1").innerHTML = "Karel"; // zo editten!! maar dan input getten en naar database posten!
   // postData(docent); hiervoor een nieuw endpoint maken!

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


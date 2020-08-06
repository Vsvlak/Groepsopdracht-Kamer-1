

function laatDocentenTabelZien() {

    document.getElementById("tabel").innerHTML = "";

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var alleinfo = JSON.parse(this.responseText);
            document.getElementById("tabel").innerHTML += "<tr><td><b>voornaam</b></td><td><b>achternaam</b></td></tr>"

            for (var x = 0; x < alleinfo.length; x++) {
                document.getElementById("tabel").innerHTML += "<tr>" +
                    "<td>" + alleinfo[x].voornaam + "</td>" +
                    "<td>" + alleinfo[x].achternaam + "</td>" +
                    // "<td>"+alleinfo[x].vak+"</td>"+ 
                    "<td><img src='EditButton.png' class='editB' id=editButton" + x + " style='height:20px;width20px;'></td>" +
                    "</tr>";
            }


        }

    }

    xhr.open("GET", "http://localhost:8082/docentOverzicht", true);
    xhr.send();


}

function maakDocentAan() {

    var voornaamInput = document.getElementById("voornaamInput").value;
    var achternaamInput = document.getElementById("achternaamInput").value;
   //  var vakkenInput = document.getElementById("vakkenInput").value;
    //  var docentvak = { docentid:":docentid", "vakid": vakid};
    // var json = JSON.stringify(docentvak);

    var docent = '{ "voornaam":" ' + voornaamInput + '", "achternaam":"' + achternaamInput+ '}';
    postData(docent);
    modal.classList.remove('active')
    overlay.classList.remove('active')


}


function postData(docent) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
    
        if (xhttp.readyState == 4) {
        laatDocentenTabelZien();
        }
    };
    xhttp.open("POST", "http://localhost:8082/api/maakDocent", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(docent);
 


}

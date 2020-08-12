function maakKlassenDropDown(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("kiesklas").innerHTML += "<option>" + info[x].id + ". " + info[x].naam + "</option>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/klassenOverzicht", true);
    xhr.send();
}

function maakLeerlingenDropdown(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("kiesleerling").innerHTML += "<option>" + info[x].id + ". " + info[x].voornaam + " " + info[x].achternaam + "</option>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/leerlingOverzicht", true);
    xhr.send();
}

function maakVakkenDropdown(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("kiesvak").innerHTML += "<option>" + info[x].id + ". " + info[x].naam + "</option>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/vakkenOverzicht", true);
    xhr.send();
}

function maakDocentenDropdown(){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 3) {
            var info = JSON.parse(this.responseText);
            for (var x = 0; x < info.length; x++) {
                document.getElementById("kiesdocent").innerHTML += "<option>" + info[x].id + ". " + info[x].achternaam + "</option>";
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/docentOverzicht", true);
    xhr.send();
}

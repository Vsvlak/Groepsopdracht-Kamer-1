let tabelLatenZien = false;

function laatLeerlingenTabelZien() {

    if (tabelLatenZien == false) {
        document.getElementById("leerlingOproep").innerHTML = "";


        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 3) {
                var alleinfo = JSON.parse(this.responseText);

                document.getElementById("tabel").innerHTML += "<tr><td><b>Id.</b></td><td><b>Naam</b></td><td><b>Geboortedatum</b></td><td><b>Studentnr.</b></td><td></td></tr>"

                for (var x = 0; x < alleinfo.length; x++) {
                    document.getElementById("tabel").innerHTML += "<tr>" +

                        "<td>" + alleinfo[x].id + "</td>" + //IK DENK DAT WE DEZE REGEL WEG MOETEN LATEN. ID IS NUTTELOOS OM TE LATEN ZIEN AAN EEN DOCENT
                        "<td>" + alleinfo[x].naam + "</td>" +
                        "<td>" + moment(alleinfo[x].geboorteDatum).format('DD-MM-YYYY') + "</td>" +
                        "<td>" + alleinfo[x].leerlingnummer + "</td>" +
                        "<td><img src='EditButton.png' class='editB' id=editButton" + x + " style='height:20px;width20px;'></td>" +
                        "</tr>";
                }

                document.getElementById("tabel").innerHTML += "<tr>" +
                    "<td><input type=button value='+Leerling' onclick= ></td></tr>";

                tabelLatenZien = true;

            }
        }


        xhr.open("GET", "http://localhost:8082/leerlingOverzicht", true);
        xhr.send();
    }
}



function adapt() {
    document.getElementById("leerlingOproep").innerHTML = "";

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        console.log(xhr.readyState);

        if (xhr.readyState == 3) {
            var alleinfo = JSON.parse(this.responseText);
            for (var x = 0; x < alleinfo.length; x++) {
                //document.getElementById("leerlingOproep").innerHTML += "<div class=mooi>"+alleinfo[x].naam+"</div>";
                document.getElementById("leerlingOproep").innerHTML += "<button>" + alleinfo[x].naam + "</button><br><br>";

                // var butt = document.createElement("button");
                // butt.innerHTML=alleinfo[x].naam;
                // allinfo.appendChild(butt);
            }
        }
    }
    xhr.open("GET", "http://localhost:8082/leerlingOverzicht", true);
    xhr.send();
}




const openModalButtons = document.querySelectorAll('[data-modal-target]')
const closeModalButtons = document.querySelectorAll('[data-close-button]')
const overlay = document.getElementById('overlay')

openModalButtons.forEach(button => {
    button.addEventListener('click', () => {
        const modal = document.querySelector(button.dataset.modalTarget)
        openModal(modal)
    })
})

overlay.addEventListener('click', () => {
    const modals = document.querySelectorAll('.modal.active')
    modals.forEach(modal => {
        closeModal(modal)
    })
})

closeModalButtons.forEach(button => {
    button.addEventListener('click', () => {
        const modal = button.closest('.modal')
        closeModal(modal)
    })
})

function openModal(modal) {
    if (modal == null) return
    modal.classList.add('active')
    overlay.classList.add('active')
}

function closeModal(modal) {
    if (modal == null) return
    modal.classList.remove('active')
    overlay.classList.remove('active')
}
function surligne(champ, erreur)
{
    if(erreur)
        champ.style.backgroundColor = "#fba";
    else
        champ.style.backgroundColor = "";
}

function verifCivilite(champ1,champ2){
    if(champ1.checked || champ2.checked){
        return true;
    }

    else{
        return false;
    }
}


function verifNom(champ)
{
    if(champ.value.length < 2 || champ.value.length > 25)
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}

function verifPrenom(champ) {
    if (champ.value.length < 2 || champ.value.length > 25) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}

function verifDate(champ){
    if(champ===""){
        surligne(champ, true);
        return false;
    }
    else{
        surligne(champ, false);
        return true;
    }
}


function verifTel(champ) {
    if (champ.value.length < 10 || champ.value.length > 10) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}


function verifCodePostal(champ)
{
    let codePostal = parseInt(champ.value);
    if(isNaN(codePostal) || codePostal < 1000 || codePostal > 98999 )
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}


function verifVille(champ) {
    if (champ.value.length < 2|| champ.value.length > 25) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}

function verifAdresse(champ) {
    if (champ.value.length < 2|| champ.value.length > 40) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}


function verifMail(champ)
{
    let regex = /^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$/;
    if(!regex.test(champ.value))
    {
        surligne(champ, true);
        return false;
    }
    else
    {
        surligne(champ, false);
        return true;
    }
}


function verifMdp(champ) {
    if (champ.value.length < 2 || champ.value.length > 25) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}

function verifForm() {
    let civiliteOk = verifCivilite(document.getElementById("civility0"),document.getElementById("civility1"));
    let nomOk = verifNom(document.getElementById("name"));
    let prenomOk = verifPrenom(document.getElementById("firstname"));
    let dateOk = verifDate(document.getElementById("birthdate"));
    let telOk = verifTel(document.getElementById("GSM"));
    let codePostalOk = verifCodePostal(document.getElementById("CP"));
    let villeOk = verifVille(document.getElementById("town"));
    let adresseOk = verifAdresse(document.getElementById("address"));
    let mailOk = verifMail(document.getElementById("courriel"));
    let mdpOk = verifMdp(document.getElementById("mdp"));


    if (civiliteOk && nomOk && prenomOk && dateOk && telOk && codePostalOk && villeOk && adresseOk && mailOk && mdpOk) {
        return true;
    }

    else {
        alert("Veuillez renseigner correctement tous les champs !");
        return false;
    }
}



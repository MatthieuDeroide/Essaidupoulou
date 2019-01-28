function surligne(champ, erreur)
{
    if(erreur)
        champ.style.backgroundColor = "#fba";
    else
        champ.style.backgroundColor = "";
}

function verifOldPassword(champ){
    if (champ.value.length < 2 || champ.value.length > 25) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}

function verifNewPassword (champ){
    if (champ.value.length < 2 || champ.value.length > 25) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}

function verifRetype (champ){
    if (champ.value.length < 2 || champ.value.length > 25) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}

function matchingPassword (champ1,champ2){
    if (champ1.value===champ2.value && champ1.value.length>2 && champ2.value.length>2){
        surligne(champ1, false);
        surligne(champ2, false);
        return true;
    }

    else{
        surligne(champ1, true);
        surligne(champ2, true);
        return false;
    }
}

function verifForm() {
    let oldPasswordOk = verifOldPassword(document.getElementById("oldpassword"));
    let newPasswordOk = verifNewPassword(document.getElementById("newpassword"));
    let retypeOk = verifRetype(document.getElementById("verifynewpassword"));
    let matchingOk =  matchingPassword(document.getElementById("newpassword"), document.getElementById("verifynewpassword"));

    if (oldPasswordOk && newPasswordOk && retypeOk && matchingOk) {
        return true;
    }
    else {
        alert("Veuillez renseigner correctement tous les champs !");
        return false;
    }
}





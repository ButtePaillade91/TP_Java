var addButton = document.getElementById('adButton');
var form = document.querySelector('form');

function validationFormulaire() {
    const nomValue = document.getElementById('last_name').value;
    const prenomValue = document.getElementById('first_name').value;
    const mailValue = document.getElementById('email').value;
    const naissanceValue = document.getElementById('dateNaissance').value;

    let nom = document.getElementById('last_name');
    let prenom = document.getElementById('first_name');
    let mail = document.getElementById('email');
    let naissance = document.getElementById('dateNaissance');

    var nomError = document.getElementById('nomError');
    var prenomError = document.getElementById('prenomError');
    var mailError = document.getElementById('mailError');
    var naissanceError = document.getElementById('naissanceError');

    var nomValid = true;
    var prenomValid = true;
    var mailValid = true;
    var naissanceValid = true;

    if (!nomValue) {
        nomValid = false;
        nom.classList.add('invalid');
        nomError.textContent = 'Le nom est requis !';
        nomError.classList.add('error-message');
    }
    else if (!/^[A-Za-zÀ-ÖØ-öø-ÿ -]+$/.test(nomValue)) {
        nomValid = false;
        nom.classList.add('invalid');
        nomError.textContent = 'Le nom doit contenir uniquement des lettres (tirets ou espaces autorisés) !';
        nomError.classList.add('error-message');
    }
    else if (nomValue.length<=2) {
        nomValid = false;
        nom.classList.add('invalid');
        nomError.textContent = 'Le nom doit comporter minimum 3 caractères pour être recevable !';
        nomError.classList.add('error-message');
    }
    else {
        nom.classList.remove('invalid');
        nomError.textContent = '';
    }

    if (!prenomValue) {
        prenomValid = false;
        prenom.classList.add('invalid')
        prenomError.textContent = 'Le prénom est requis !';
        prenomError.classList.add('error-message');
    }
    else if (!/^[A-Za-zÀ-ÖØ-öø-ÿ -]+$/.test(prenomValue)) {
        prenomValid = false;
        prenom.classList.add('invalid');
        prenomError.textContent = 'Le prénom doit contenir uniquement des lettres (tirets ou espaces autorisés) !';
        prenomError.classList.add('error-message');
    }
    else if (prenomValue.length<=2) {
        prenomValid = false;
        prenom.classList.add('invalid');
        prenomError.textContent = 'Le prénom doit comporter minimum 3 caractères pour être recevable !';
        prenomError.classList.add('error-message');
    }
    else {
        prenom.classList.remove('invalid');
        prenomError.textContent = '';
    }

    if (!mailValue) {
        mailValid = false;
        mail.classList.add('invalid')
        mailError.textContent = "L'email est requis !";
        mailError.classList.add('error-message');
    }
    else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(mailValue)) {
        mailValid = false;
        mail.classList.add('invalid');
        mailError.textContent = "L'email doit être une adresse valide !";
        mailError.classList.add('error-message');
    }

    else {
        mail.classList.remove('invalid');
        mailError.textContent = '';
    }

    var dateActuelle = new Date();
    var jour = dateActuelle.getDate();
    var mois = dateActuelle.getMonth() + 1;
    var annee = dateActuelle.getFullYear()-18;
    var dateMajeur = new Date(annee, mois, jour);
    var slash = naissanceValue.split('/');
    var Jour = parseInt(slash[0], 10);
    var Mois = parseInt(slash[1], 10) - 1;
    var Annee = parseInt(slash[2], 10);
    var dateNaissance = new Date(Annee, Mois, Jour);
    if (!naissanceValue) {
        naissanceValid = false;
        naissance.classList.add('invalid');
        naissanceError.textContent = 'La date de naissance est requise !';
        naissanceError.classList.add('error-message');
    }
    else if (!/^\d{2}\/\d{2}\/\d{4}$/.test(naissanceValue)) {
        naissanceValid = false;
        naissance.classList.add('invalid');
        naissanceError.textContent = 'La date de naissance doit être une date valide !';
        naissanceError.classList.add('error-message');
    }
    else if (dateNaissance > dateMajeur) {
        naissanceValid = false;
        naissance.classList.add('invalid');
        naissanceError.textContent = "Il faut être majeur pour pouvoir s'inscrire !";
        naissanceError.classList.add('error-message');
    }
    else {
        naissance.classList.remove('invalid');
        naissanceError.textContent = '';
    }

    if (nomValid && prenomValid && mailValid && naissanceValid) {
        form.submit();
    }
}
addButton.addEventListener('click', function(event) {
    event.preventDefault();
    validationFormulaire();
});
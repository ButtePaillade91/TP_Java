var addButton = document.getElementById('adButton');
var form = document.querySelector('form');

function validationFormulaire() {
    const voitureValue = document.getElementById('car').value;
    const clientValue = document.getElementById('client').value;
    const debutValue = document.getElementById('begin').value;
    const finValue = document.getElementById('end').value;

    let voiture = document.getElementById('car');
    let client = document.getElementById('client');
    let debut = document.getElementById('begin');
    let fin = document.getElementById('end');

    var voitureError = document.getElementById('carError');
    var clientError = document.getElementById('clientError');
    var debutError = document.getElementById('beginError');
    var finError = document.getElementById('endError');

    var voitureValid = true;
    var clientValid = true;
    var debutValid = true;
    var finValid = true;

    if (!voitureValue) {
        voitureValid = false;
        voiture.classList.add('invalid');
        voitureError.textContent = 'La voiture est requise !';
        voitureError.classList.add('error-message');
    }
    else {
        voiture.classList.remove('invalid');
        voitureError.textContent = '';
    }
    if (!clientValue) {
        clientValid = false;
        client.classList.add('invalid')
        clientError.textContent = 'Le client est requis !';
        clientError.classList.add('error-message');
    }
    else {
        client.classList.remove('invalid');
        clientError.textContent = '';
    }
    if (!debutValue) {
        debutValid = false;
        debut.classList.add('invalid');
        debutError.textContent = "La date de début de réservation est requise !";
        debutError.classList.add('error-message');
    }
    else {
        debut.classList.remove('invalid');
        debutError.textContent = '';
    }
    if (!finValue) {
        finValid = false;
        fin.classList.add('invalid');
        finError.textContent = 'La date de fin de réservation est requise !';
        finError.classList.add('error-message');
    }
    else {
        fin.classList.remove('invalid');
        finError.textContent = '';
    }
    var slashDebut = debutValue.split('/');
    var JourDebut = parseInt(slashDebut[0], 10);
    var MoisDebut = parseInt(slashDebut[1], 10) - 1;
    var AnneeDebut = parseInt(slashDebut[2], 10);
    var debutValeur = new Date(AnneeDebut, MoisDebut, JourDebut);
    var slashFin = finValue.split('/');
    var JourFin = parseInt(slashFin[0], 10);
    var MoisFin = parseInt(slashFin[1], 10) - 1;
    var AnneeFin = parseInt(slashFin[2], 10);
    var finValeur = new Date(AnneeFin, MoisFin, JourFin);
    var difference = finValeur.getTime() - debutValeur.getTime(); // Différence en millisecondes
    var differenceEnJours = difference / (1000 * 3600 * 24); // Convertir la différence en jours
    if (differenceEnJours>=7) {
        debutValid = false;
        finValid = false;
        debut.classList.add('invalid');
        fin.classList.add('invalid');
        debutError.textContent = "Il n'est pas possible de réserver un véhicule plus de 7 jours d'affilée, veuillez " +
            "diminuer votre temps de réservation !";
        finError.textContent = "Il n'est pas possible de réserver un véhicule plus de 7 jours d'affilée, veuillez " +
            "diminuer votre temps de réservation !";
        debutError.classList.add('error-message');
        finError.classList.add('error-message');
    }
    else if (debutValeur.getTime()>finValeur.getTime()) {
        debutValid = false;
        finValid = false;
        debut.classList.add('invalid');
        fin.classList.add('invalid');
        debutError.textContent = "Il n'est pas possible d'avoir une date de fin se situant avant la date de début," +
            " revoyez vos dates !";
        finError.textContent = "Il n'est pas possible d'avoir une date de fin se situant avant la date de début," +
            " revoyez vos dates !";
        debutError.classList.add('error-message');
        finError.classList.add('error-message');
    }

    fetch(`/rents/create?voiture=${voitureValue}&debut=${debutValue}&fin=${finValue}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erreur lors de la récupération de la disponibilité de la voiture');
            }
            return response.json();
        })
        .then(data => {
            if (data.disponible) {
                form.submit();
            } else {
                alert("La voiture n'est pas disponible pour les dates sélectionnées.");
            }
        })
        .catch(error => {
            // Erreur lors de la récupération de la disponibilité de la voiture
            console.error('Erreur:', error);
            alert('Une erreur s\'est produite. Veuillez réessayer.');
        });

    if (voitureValid && clientValid && debutValid && finValid) {
        form.submit();
    }
}
addButton.addEventListener('click', function(event) {
    event.preventDefault();
    validationFormulaire();
});
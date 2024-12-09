const cardContainer = document.querySelector('.card-container');

function flipCard() {
    const card = document.querySelector('.card');
    card.classList.toggle('flipped');
}

cardContainer.addEventListener('click', (event) => {

    const isNonClickable = event.target.closest('a') || event.target.closest('button') || event.target.closest('img');

    if (isNonClickable) {
        flipCard();
    }
});


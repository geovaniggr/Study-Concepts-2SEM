let $toggleButton = document.getElementById('toggleHamburguer');
let $menu = document.querySelector('.menu');
console.log($menu.children)

$toggleButton.addEventListener('click', _ => {
    $menu.classList.toggle('menuWidth');
    
    for(let element of $menu.children) {
        let atual = element.style.display;
        if(element.style.display != 'initial') {
            element.style.display = 'initial';
        } else {
            element.style.display = 'none';
        }
    }
});
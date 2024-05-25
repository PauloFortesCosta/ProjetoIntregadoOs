function abrirModal(e, modal) {
    e.preventDefault();

    const modalFundo = document.querySelector(`.modal_${modal}`);
    const modalConteudo = modalFundo.querySelector('.modal_conteudo');

    modalFundo.style.opacity = "1";
    modalFundo.style.visibility = "visible"; 
}


function fecharModal(modal) {
    const modalFundo = document.querySelector(`.modal_${modal}`);    

    modalFundo.style.opacity = "0";
    modalFundo.style.visibility = "hidden";           
}
   function formataMoedaReal(valor) {
        valor = String(valor)
        
        const valorPartes = valor.split('.');
        if(valorPartes[1]) return `R$${valorPartes[0]},${valorPartes[1]}`;
        else return `R$${valorPartes[0]},00`;        
    }
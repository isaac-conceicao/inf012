export function getDataAtualFormatada() {
    const data = new Date();
    const dataAtual = {
        dia: data.getDate(),
        mes: data.getMonth() + 1,
        ano: data.getFullYear()
    };
    const stringDataAtual = {
        dia: formataSeMenorQueDez(dataAtual.dia),
        mes: formataSeMenorQueDez(dataAtual.mes),
        ano: `${dataAtual.ano}`
    };
    return `${stringDataAtual.dia}/${stringDataAtual.mes}/${stringDataAtual.ano}`;
}

export function getDataFormatada(data) {
    let dia = data[2];
    let mes = data[1];
    let ano = data[0];

    return `${formataSeMenorQueDez(dia)}/${formataSeMenorQueDez(mes)}/${ano}`;
}

function formataSeMenorQueDez(data) {
    return data < 10 ? `0${data}` : `${data}`;
}

export function formataStatus(status) {
    switch(status) {
        case 'ABERTO':    return 'Em Aberto';
        case 'ANDAMENTO': return 'Em Andamento'; 
        case 'ATENDIDO':     return 'Atendido';
    }
}
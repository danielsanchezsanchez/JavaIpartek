'use strict';

//Nombre de la funcion
function cantidator(){
	
	//Cojo todas las filas
	var filas = document.getElementsByTagName('tr');
	
	//Bucle con el numero de filas
	for(var i = 1; i < filas.length; i++){
		var fila = filas[i];
		//Dentro de la fila los/el elementos de tipo input
		var input = fila.getElementsByTagName('input')[0];
		
		//Si no hay ya, un metodo asociado al evento de cambio del input, .....
		if(!input.onchange) {
			input.onchange = cantidator;
		}
		
		var valor = input.value;
		//Dentro de la fila los/el elementos de tipo a
		var a = fila.getElementsByTagName('a')[0];
		//Sustituyo el href por el href con 'donde hay cantidad=' + valor(input.value).
		a.href = a.href.replace(/cantidad=[0-9]*/, 'cantidad=' + valor);
	}
	
	//document.getElementsByTagName('tr')[1].getElementsByTagName('input')[0].value
	//document.getElementsByTagName('tr')[1].getElementsByTagName('a')[0].href
}
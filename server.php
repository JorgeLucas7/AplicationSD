<?php
	$nome = $_POST['etNome'];
	$sexo = $_POST['etSexo'];
	$idade = $_POST['etIdade'];
	
	if($sexo == "masculino" || $sexo == "Masculino")
		if($idade >= 18)
			echo 'É maior de idade';
		else
			echo 'Não é maior de idade';
	else
		if($idade >= 21)
			echo 'É maior de idade';
		else
			echo 'Não é maior de idade';

?>

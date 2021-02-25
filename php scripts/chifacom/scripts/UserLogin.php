<?php

require_once '../includes/DbOperations.php';
$response = array();

if($_SERVER['REQUEST_METHOD']=='POST'){
	
	if(isset($_POST['office_mail']) and isset($_POST['office_pass'])){
		       
	  	$db = new DbOperations();
		$user_response = array();
		
		if($db->userLogin($_POST['office_mail'],$_POST['office_pass'])){
			//phpinfo();
			if($db->isActive($_POST['office_mail'],$_POST['office_pass'])){
			$user = $db->getUserByUsername($_POST['office_mail']);
			$doctor = $db -> getDoctorByUsername($user['office_id']);
			$response['error'] = false ; 
			$response['office_id'] = $user['office_id'];
			$response['office_mail'] = $user['office_mail'];
			$response['office_type'] = $user['office_type'];
			$response['phone'] = $user['phone'];
			$response['office_createdAt'] = $user['office_createdAt'];
			$response['doctor_firstname'] = $doctor['doctor_firstname'];
			$response['doctor_lastname'] = $doctor['doctor_lastname'];
		    $response['doctor_address'] = $doctor['doctor_address'];
			$response['doctor_speciality'] = $doctor['doctor_speciality'];
			$response['birthdate'] = $doctor['birthdate'];
			
			array_push($user_response,$response);
			
			}else{
				$response['error'] = true ;	
	            $response['message'] = "Your Account is not activated !" ;
			}
			
		}else{
			
			$response['error'] = true ;	
	    $response['message'] = "Invalid username or You are not registred !" ;
			
		}
		
		
	}else{
		
		$response['error'] = true ;	
	    $response['message'] = "Erreur in login !, Required fields are missing" ;
		
	}
}

echo json_encode($user_response);
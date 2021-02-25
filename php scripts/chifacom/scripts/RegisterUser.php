<?php

  require_once '../includes/DbOperations.php';
  require_once '../includes/Constants.php';
  
  $upload_path = 'uploads/';
  
  $server_ip = gethostbyname(gethostname());
  
  $upload_url = 'http://'.$server_ip.'/android/v1/'.$upload_path;
  

$response = array();

if($_SERVER['REQUEST_METHOD']=='POST'){
	
	if(
	      isset($_POST['doctor_firstname']) and
		  isset($_POST['doctor_lastname']) and
		  isset($_POST['doctor_speciality']) and
		  isset($_POST['birthdate']) and
		  isset($_POST['birthplace']) and
		  isset($_POST['phone']) and
		  isset($_POST['office_mail']) and
		  isset($_POST['office_type']) and
		  isset($_POST['province']) and
		  isset($_POST['state']) and
		  isset($_POST['address_link']) and
		  isset($_POST['address_lat']) and
		  isset($_POST['address_long']) 
		 
	){
		// address -------------------------------------
		
		$user_response = array();
		
		$db = new DbOperations();
		if ($db->doctorRegisterAddress(
		       $_POST['province'],
			    $_POST['state'],
			   $_POST['address_link'],
			   $_POST['address_lat'],
			   $_POST['address_long']
		)){
			$addr = $db->getUserByAddress($_POST['address_link']);
		
		
		// address end --------------------------------
		
		
		//echo '///////// '.$_FILES['office_picture']['name'];
		if ( isset($_FILES['office_picture']['name'])){
		$con = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD,DB_NAME) or
		  die('Unable to connect to database');
		
		      $email= $_POST['office_mail'];
			  $type= $_POST['office_type'];
			  $phone= $_POST['phone'];
			  
			  $fileinfo = pathinfo($_FILES['office_picture']['name']);
		  
		  $extension = $fileinfo['extension'];
		  
		  $file_url = $upload_url . getFileName() . '.'.$extension;
		  
		  $file_path = $upload_path. getFileName(). '.'.$extension;
			   
			     try{
			  
			  move_uploaded_file($_FILES['office_picture']['tmp_name'],$file_path);
			  
			   $stmt  = $con->prepare("INSERT INTO doc_office (office_mail,office_type,phone,office_picture) 
			                             VALUES ('$email',
										         '$type',
												 '$phone',
												 '$file_url')");
			   if($stmt->execute()){
				   $response['error']= false ; 
				   $response['office_picture'] = $file_url;
				   $response['office_mail'] = $email;
				   $response['office_type'] = $type;
				   $response['phone'] = $phone;
				   
			   }
			   	$db = new DbOperations();
			$user = $db->getUserByUsername($_POST['office_mail']);
			if ($db->doctorRegister(
		       $_POST['doctor_firstname'],
			   $_POST['doctor_lastname'],
			    $addr['address_id'],
			   $_POST['doctor_speciality'],
			   $_POST['birthdate'],
			   $_POST['birthplace'],
			   $user['office_id']
		)){
			
			
			
			$response['error'] = false ;	
	        $response['message'] = "Client registered Successfully" ;
		}else{
			$response['error'] = true ;	
	        $response['message'] = "Some error occured 111 , please try again !" ;
		}
			  
		  }catch(Exception $e){
			  $response['error'] = true;
			  $response['message'] = $e->getMessage();
			  
		  }
			   mysqli_close($con);
			   
			   }else{
				   
				   //kkkkkkkkkkkkkkk
			$db = new DbOperations();
		if ($db->doctorRegisterOffice(
		       $_POST['office_mail'],
			   $_POST['office_type'],
			   $_POST['phone']
		)){
			$user = $db->getUserByUsername($_POST['office_mail']);
			if ($db->doctorRegister(
		       $_POST['doctor_firstname'],
			   $_POST['doctor_lastname'],
			   $addr['address_id'],
			   $_POST['doctor_speciality'],
			   $_POST['birthdate'],
			   $user['office_id']
		)){
			
			
			
			$response['error'] = false ;	
	        $response['message'] = "Client registered Successfully22" ;
		}else{
			$response['error'] = true ;	
	        $response['message'] = "Some error occured 111 , please try again !" ;
		}
			
			
			
			$response['error'] = false ;	
	        $response['message'] = "Operation avec succes" ;
		}else{
			$response['error'] = true ;	
	        $response['message'] = "Erreur !" ;
		}
		//kkkkkkkkkkkkkkkkkkkkk
				   
			   }
		}
	
	
	
	array_push($user_response,$response);
			
		
		
	}else{
		$response['error'] = true ;	
	    $response['message'] = "Required fields are missing" ;
	}
	
}else{
	
    $response['error'] = true ;	
	$response['message'] = "Invalid Request" ;
	
}

echo json_encode($user_response);


	  function getFileName(){
		  $con = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD,DB_NAME) or
		  die('Unable to connect to database');
		  $sql = "select max(office_id) as office_id from doc_office";
		  $result = mysqli_fetch_array(mysqli_query($con,$sql));
		  mysqli_close($con);
		  if($result['office_id']==null){
			  return 1 ;
		  }else{
			  
			  return $result['office_id']+1;
		  }
		  
		  
	  }
<?php

     
	 class DbOperations{
		 
		   private $con;
		   
		   function __construct(){
			   require_once dirname(__FILE__) . '/Constants.php';
			   require_once dirname(__FILE__).'/DbConnect.php';
			   
			   $db = new DbConnect();
			   
			   $this->con = $db->connect();
		   }
		   


			//Create account 
			
			//doctor : step1  --> register as an office
			function doctorRegisterOffice($username,$email,$pass,$type,$phone){
				$password = md5($pass);
				  $stmt  = $this->con->prepare("insert into doc_office (office_username,office_mail,office_pass,office_type,phone) values (?,?,?,?,?)");
				  $stmt->bind_param("sssss",$username,$email,$password,$type,$phone);
				  if($stmt->execute()){
				   return true ;
			   }else{
				   return false ;
			   }
			}
			//doctor : step2 ---> full registration
			function doctorRegister($firstname,$firstnameAR,$lastname,$lastnameAR,$address,$speciality,$birthdate,$office_id){
				 $stmt  = $this->con->prepare("insert into doctor (doctor_firstname,doctor_firstname_AR,doctor_lastname,doctor_lastname_AR,doctor_speciality,birthdate,doctor_address,office_id) values (?,?,?,?,?,?,?,?)");
				  $stmt->bind_param("ssssssss",$firstname,$firstnameAR,$lastname,$lastnameAR,$speciality,$birthdate,$address,$office_id);
				  if($stmt->execute()){
				   return true ;
			   }else{
				   return false ;
			   }
			}
			
			// get user informations
					public function getUserByUsername($email){
				
				$stmt = $this->con->prepare("select * from doc_office 
				                              where (office_mail = ? or office_username=?)");
				$stmt-> bind_param("ss",$email,$email);
				$stmt->execute();
				return $stmt->get_result()->fetch_assoc();
				
			
			}
			
			 // users login 
				public function userLogin($email,$pass){
				
				$password = md5($pass);
				echo $password;
				$stmt = $this->con->prepare("select office_id from doc_office 
				                              where office_mail = ? and office_pass = ? ");
				$stmt-> bind_param("ss",$email,$password);
                $stmt->execute();
                $stmt->store_result();
				if($stmt->num_rows < 1 ){
					$stmt = $this->con->prepare("select office_id from doc_office 
				                              where office_username = ? and office_pass = ? ");
				$stmt-> bind_param("ss",$email,$password);
                $stmt->execute();
                $stmt->store_result();
				}
                return $stmt->num_rows > 0 ;				
											  
				
			}
			//get Doctor
					public function getDoctorByUsername($id){
				
				$stmt = $this->con->prepare("select * from doctor 
				                              where office_id = ? ");
				$stmt-> bind_param("s",$id);
				$stmt->execute();
				return $stmt->get_result()->fetch_assoc();
				
			
			}
			
			//get address of doctor after login 
			//get Doctor
					public function getAddressByUsername($id){
				
				$stmt = $this->con->prepare("select * from address 
				                              where address_id = ? ");
				$stmt-> bind_param("s",$id);
				$stmt->execute();
				return $stmt->get_result()->fetch_assoc();
				
			
			}
			
			// check if the account is active or not 
			public function isActive($username,$pass){
				$status = 1;
				$password = md5($pass);
				$stmt = $this->con->prepare("select office_id from doc_office 
				                              where (office_mail = ? or office_username = ?)and  office_pass= ? and isActive= ?");
				$stmt-> bind_param("ssss",$username,$username,$password,$status);
                $stmt->execute();
                $stmt->store_result();
                return $stmt->num_rows > 0 ;				
											  
				
			}
			
			// for address
					function doctorRegisterAddress($location,$province,$state,$link,$lat,$long){
				//$password = md5($pass);
				  $stmt  = $this->con->prepare("insert into address (office_location,province,state,address_link,address_lat,address_long) values (?,?,?,?,?,?)");
				  $stmt->bind_param("ssssss",$location,$province,$state,$link,$lat,$long);
				  if($stmt->execute()){
				   return true ;
			   }else{
				   return false ;
			   }
			}
			
				public function getUserByAddress($link){
				
				$stmt = $this->con->prepare("select * from address 
				                              where address_link = ? ");
				$stmt-> bind_param("s",$link);
				$stmt->execute();
				return $stmt->get_result()->fetch_assoc();
				
			
			}
			
			
	 }
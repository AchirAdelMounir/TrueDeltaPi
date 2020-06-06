var myWidget = cloudinary.createUploadWidget({
	  cloudName: 'dwjg9q9r9', 
	  uploadPreset: 'assetmanager',sources: [ 'local', 'camera']},
	  
	  (error, result) => { 
	    if (!error && result && result.event === "success") { 
	      console.log('Done! Here is the image info: ', result.info); 
	      console.log(result.info.secure_url)
	      document.getElementById("image").src=result.info.secure_url
	      document.getElementById("createAMForm:fimage").value=result.info.secure_url
	      
	    }
	  }
	)
	
	
$("#upload_widget").on("click",function(event){
	  event.preventDefault();
	  console.log(123)
	  myWidget.open();
	});




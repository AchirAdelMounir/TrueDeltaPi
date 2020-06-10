var myWidgetCv = cloudinary.createUploadWidget({
	  cloudName: 'dwjg9q9r9', 
	  uploadPreset: 'assetCV',sources: [ 'local'],clientAllowedFormats:['pdf'],multiple:false},
	  
	  (error, result) => { 
	    if (!error && result && result.event === "success") { 
	      console.log('Done! Here is the image info: ', result.info); 
	      console.log(result.info.secure_url)
	     document.getElementById("createAMForm:cvname").value=result.info.original_filename
	      document.getElementById("createAMForm:cvfile").value=result.info.secure_url
	      
	    }
	  }
	)
	
	document.getElementById("createAMForm:upload_cv").addEventListener("click", function(){
		console.log(123)
	    myWidgetCv.open();
	  }, false);
console.log(myWidgetCv)
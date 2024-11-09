package com.emudhra.emra.subscriber.controller;




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.emudhra.emra.subscriber.enums.ErrorCodes;
import com.emudhra.emra.subscriber.exception.CommonException;



@RestController
public class DashBoardController {


@GetMapping("/dashboard")
public ModelAndView getDashBoardView() {
try {
	ModelAndView view = new ModelAndView("managecertificates");	
	
	return view;
    
} catch (Exception e) {
   
    e.printStackTrace(); 
    

    throw new CommonException(ErrorCodes.SUBLOGIN002.getCode(), ErrorCodes.SUBLOGIN002.getDescription(), e);
}
}	


	

	
	
}

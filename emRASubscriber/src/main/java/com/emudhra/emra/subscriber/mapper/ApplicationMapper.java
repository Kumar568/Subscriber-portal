package com.emudhra.emra.subscriber.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

import com.emudhra.emra.subscriber.dto.ApplicationDto;
import com.emudhra.emra.subscriber.entity.entity.Application;





@Mapper(uses = OrderDetailsMapper.class)
public interface ApplicationMapper {
	
	ApplicationMapper MAPPER = Mappers.getMapper(ApplicationMapper.class);

	List<ApplicationDto> mapApplicationToApplicationDto(List<Application> application);
	@Mapping(source = "status", target = "applicationStatus", qualifiedByName = "applicationstatusToText")
	@Mapping(source = "status", target = "certificateDetailsStatus", qualifiedByName = "certificatestatusToText")
	@Mapping(source = "status", target = "approvalrequeststatus", qualifiedByName = "approvalrequeststatus")
	@Mapping(source = "status",target = "validationDocumentsStatus", qualifiedByName  = "validationDocumentsStatusToText")

    //@Mapping(target = "uploadCsrStatus", qualifiedByName = "uploadcsrstatusToText")
    @Mapping(target = "uploadCsrStatus", expression = "java(uploadCsrStatus(application))")
	//@Mapping(source = "orderDetails", target = "orderDetailsDto")
	//@Mapping(source = "vettingprofile", target = "vettingprofile")
//	  @AfterMapping
//	    default void setUploadCsrStatus(Application application, @MappingTarget ApplicationDto applicationDto) {
//	        applicationDto.setUploadCsrStatus(uploadcsrstatusToText(application));
//	    }

    //@Mapping(target = "uploadCsrStatus", expression = "java(uploadcsrstatusToText(application))")

	ApplicationDto mapApplicationToApplicationDto(Application application);


	@Named("applicationstatusToText")
	static String applicationstatusToText(int status) {
	    if (status == 1 || status == 2 || status == 3 || status == 5| status == 6|| status == 7||
	    		status == 8|| status == 9|| status == 10|| status == 11|| status == 12|| status == 13
	    		|| status == 14|| status == 15|| status == 16|| status == 17) {
	        return "Completed";
	    } else {
	        return "Pending";
	    }
	}
	
	@Named("certificatestatusToText")
	static String certificatestatusToText(int status) {
	    if (status == 2 || status == 3 || status == 5| status == 6|| status == 7||
	    		status == 8|| status == 9|| status == 10|| status == 11|| status == 12|| status == 13
	    		|| status == 14|| status == 15|| status == 16|| status == 17) {
	        return "Completed";
	    } 
	 else {
	        return "Pending";
	    }
	}
	
	@Named("validationDocumentsStatusToText")
	static String validationDocumentsStatusToText(int status) {
	    if (status == 3 || status == 5| status == 6|| status == 7||
	    		status == 8|| status == 9|| status == 10|| status == 11|| status == 12|| status == 13
	    		|| status == 14|| status == 15|| status == 16|| status == 17) {
	        return "Completed";
	    } else {
	        return "Pending";
	    }
	}
	
	@Named("approvalrequeststatus")
	static String approvalrequeststatus(int status) {
		if(status==6) {
			//return "Sub RA Operator Pending";
			return "<span id=\"subRAOperatorPending\" class=\"label label-warning\" style=\"background-color: orange; color: black; font-size: 12px;\">Sub RA Operator Pending</span>";

		}
		else if(status==7) {
			//return "Sub RA Pending";
			return "<span id=\"uubRAPending\" class=\"label label-warning\" style=\"background-color: orange; color: black; font-size: 12px;\">Sub RA Pending </span>";

		}
		else if(status==8) {
		//	return "RA Operator Pending";
			return "<span id=\"rAOperatorPending\" class=\"label label-warning\" style=\"background-color: orange; color: black; font-size: 12px;\">RA Operator Pending</span>";

		}
		else if(status==9) {
			//return "RA Admin Pending";
			return "<span id=\"rAAdminPending\" class=\"label label-warning\" style=\"background-color: orange; color: black; font-size: 12px;\">RA Admin Pending </span>";

		}
	
		else {
			return "-";
		}
		
	}
	
	default String uploadCsrStatus(Application application) {
        boolean csr = application.getCsr() != null;
        return (csr && (application.getStatus() == 2 || application.getStatus() == 3 || application.getStatus() == 4
        		|| application.getStatus() == 4|| application.getStatus() == 4|| application.getStatus() == 4|| application.getStatus() == 4
|| application.getStatus() == 5|| application.getStatus() == 6|| application.getStatus() == 7|| application.getStatus() == 8
|| application.getStatus() == 9|| application.getStatus() == 10|| application.getStatus() == 11|| application.getStatus() == 12
|| application.getStatus() == 13 || application.getStatus() == 14 || application.getStatus() == 15 || application.getStatus() == 17)) ? "Completed" : "Pending";
    }
	
	

	
}

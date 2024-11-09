package com.emudhra.emra.subscriber.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.emudhra.emra.subscriber.dto.OrderDetailsDto;
import com.emudhra.emra.subscriber.entity.entity.OrderDetails;


@Mapper
public interface OrderDetailsMapper {
	
	OrderDetailsMapper MAPPER = Mappers.getMapper(OrderDetailsMapper.class);

	List<OrderDetailsDto> mapOrderDetailsToOrderDetailsDto(List<OrderDetails> orderDetails);

	@Mapping(source = "customers.phoneNumber", target = "customerphoneNumber")
	@Mapping(source = "customers.emailId", target = "customeremailId")
	@Mapping(source = "customers.userName", target = "customeruserName")
	@Mapping(source = "mapCertificateValidity.validityMaster.validityFrequencyId", target = "validityFrequencyId",qualifiedByName = "validityFrequencyIdToText")

	@Mapping(source = "mapCertificateValidity.validityMaster.validityUnit", target = "validityUnit")
	@Mapping(source = "mapCertificateValidity.validityMaster.description", target = "description")
	@Mapping(source = "mapCertificateValidity.productMaster.productname", target = "productname")
	@Mapping(source = "mapCertificateValidity.productMaster", target = "productMaster")

	@Mapping(source = "mapCertificateValidity.productMaster.certificatefor", target = "certificatefor",qualifiedByName = "certificateforToText")
	@Mapping(source = "status", target = "orderDetailsStatus", qualifiedByName = "statusToText")
	@Mapping(source = "status", target = "applicationStatus", qualifiedByName = "setupcertificatestatusToText")

	@Mapping(source = "status", target = "status")
	OrderDetailsDto mapOrderDetailsToOrderDetailsDto(OrderDetails orderDetails);

	@Named("statusToText")
	static String statusToText(int status) {
		if (status == 1) {
			return "<span id=\"setupstatus\" class=\"label label-warning\" style=\"background-color: orange; color: white; font-size: 12px;\">Setup Pending</span>\r\n" + 
					"";			//return "<span id="setupstatus" class="label label-warning">Setup Pending</span>";
			//return "<span id=\"setupstatus\" class=\"label label-warning\">Setup Pending</span>";
		} else {
			return "<span id=\"setupstatus1\" class=\"label label-warning\" style=\"background-color: orange; color: white; font-size: 12px;\">Pending RA </span>";
		}
	}
	
	@Named("setupcertificatestatusToText")
	static String setupcertificatestatusToText(int status) {
		if (status == 1) {
			return "Pending";
		} else {
			return "Completed";
		}
	}
	@Named("certificateforToText")
	static String certificateforToText(int certificatefor) {
		if (certificatefor == 1) {
			return "Individual";
		}
		else if (certificatefor == 2) {
			return "Organization";
		}else {
			return "-";
		}
	}
	
	@Named("validityFrequencyIdToText")
	static String validityFrequencyIdToText(int validityFrequencyId) {
		if (validityFrequencyId == 1) {
			return "Year";
		}
		else if (validityFrequencyId == 2) {
			return "Month";
		}
		else if (validityFrequencyId == 3) {
			return "Day";
		}
		else {
			return "Minutes";
		}
	}
	
	
}


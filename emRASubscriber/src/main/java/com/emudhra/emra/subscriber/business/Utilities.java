package com.emudhra.emra.subscriber.business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emudhra.emra.subscriber.dto.EmailBodyDto;
import com.emudhra.emra.subscriber.entity.entity.EmailContent;
import com.emudhra.emra.subscriber.enums.PaymentMode;
import com.emudhra.emra.subscriber.repository.EmailContentRepository;



@Component
public class Utilities {
@Autowired
private EmailContentRepository emailContentRepository;
	public String byteArrayToBase64(byte[] byteArray) {

		return java.util.Base64.getEncoder().encodeToString(byteArray);

	}

//	public List<Integer> getMasPermissionsId(List<MasPermissions> permissionslist) {
//
//		List<Integer> mPermissionIdList = permissionslist.stream().map(MasPermissions::getId)
//				.collect(Collectors.toList());
//
//		return mPermissionIdList;
//	}

//	public List<UserMapPermission> setActiveNonActivePermissions(List<Integer> list, UserMaster user, int activeStatus) {
//
//		return list.stream().map(value -> {
//			UserMapPermission permission = new UserMapPermission();
//			permission.setIsActive(activeStatus);
//			MasPermissions permissions = new MasPermissions();
//			permissions.setId(value);
//			permission.setMasPermissions(permissions);
//
//			permission.setUserMaster(user);
//			return permission;
//		}).collect(Collectors.toList());
//
//	}
//
//	public List<UserMapPermission> getUserPermission(List<Integer> selectedPermissions,
//			List<MasPermissions> permissionslist, UserMaster user) {
//
//		List<UserMapPermission> finalPermissionList = null;
//		try {
//
//			List<Integer> mPermissionIdList = getMasPermissionsId(permissionslist);
//
//			List<Integer> containsList = mPermissionIdList.stream().filter(selectedPermissions::contains)
//					.collect(Collectors.toList());
//
//			List<Integer> notContainsList = mPermissionIdList.stream()
//					.filter(element -> !selectedPermissions.contains(element)).collect(Collectors.toList());
//
//			List<UserMapPermission> permissionContainsList = setActiveNonActivePermissions(containsList, user, 1);
//
//			List<UserMapPermission> permissionNotContainsList = setActiveNonActivePermissions(notContainsList, user, 0);
//
//			finalPermissionList = new ArrayList<>();
//			finalPermissionList.addAll(permissionContainsList);
//			finalPermissionList.addAll(permissionNotContainsList);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			return finalPermissionList;
//		}
//
//		return finalPermissionList;
//	}

	public LocalDateTime getCurrentDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		localDateTime = formatDate(localDateTime);
		return localDateTime;
	}

	public LocalDateTime formatDate(LocalDateTime dateTime) {
		String pattern = "yyyy-MM-dd HH:mm:ss";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

		// Format the LocalDateTime object
		String formattedDateTime = dateTime.format(formatter);
		LocalDateTime localDateTime = LocalDateTime.parse(formattedDateTime.replace("T", " "), formatter);

		return localDateTime;
	}

	public boolean compareTwoDates(LocalDateTime FromorToDate, LocalDateTime CurrentDate) {

		if (FromorToDate.isBefore(CurrentDate)) {
			return true; // certificate expired
		} else {
			return false;
		}

	}

	public LocalDateTime convertStringtoLocalDateTime(String dateTime) {
		String format = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime convertedDate = LocalDateTime.parse(dateTime, formatter);

		return convertedDate;

	}

//	public RAtype[] getRATypes() {
//		return RAtype.values();
//	}
//	
//	public KeyGenerationType[] getKeyGenerationType() {
//		return KeyGenerationType.values();
//	}
//	public CertificateDownloadMethods[] getDownloadMethods() {
//		return CertificateDownloadMethods.values();
//	}
//	public CertificateDownloadMethodType[] getDownloadMethodType() {
//		return CertificateDownloadMethodType.values();
//	}
//	public ApprovalLevels[] getApprovalLevels() {
//		return ApprovalLevels.values();
//	}
//	
//	public IssuanceModes[] getIssuanceModes() {
//		return IssuanceModes.values();
//	}
//
//	public CertificateFor[] getCertificateFor() {
//		return CertificateFor.values();
//	}
//
//	public Map<Integer, String> getOrganizationTypes() {
//		Map<Integer, String> organizationTypes = new HashMap();
//
//		for (OrganizationType types : OrganizationType.values()) {
//			organizationTypes.put(types.getValue(), types.getDisplayName());
//		}
//
//		return organizationTypes;
//	}
//
//	public List<Integer> getMasFieldListIds(List<MasFieldList> fieldList) {
//		return fieldList.stream().map(MasFieldList::getId).collect(Collectors.toList());
//	}
//
//	public List<MapFieldGroup> getFieldlist(List<Integer> isenable, List<Integer> ismandtory,
//			List<MasFieldList> fieldList, FieldGroup fieldGroup) {
//		List<Integer> mPermissionIdList = getMasFieldListIds(fieldList);
//
//		List<Integer> containsList = mPermissionIdList.stream().filter(isenable::contains).collect(Collectors.toList());
//
//		List<Integer> notContainsList = mPermissionIdList.stream().filter(element -> !isenable.contains(element))
//				.collect(Collectors.toList());
//
//		List<MapFieldGroup> fieldGroupsContaining = setFieldGroupsActiveStatus(containsList, ismandtory, fieldGroup, 1);
//		List<MapFieldGroup> fieldGroupsNotContaining = setFieldGroupsActiveStatus(notContainsList, ismandtory,
//				fieldGroup, 0);
//
//		List<MapFieldGroup> finalFieldGroupList = new ArrayList<>();
//		finalFieldGroupList.addAll(fieldGroupsContaining);
//		finalFieldGroupList.addAll(fieldGroupsNotContaining);
//
//		return finalFieldGroupList;
//	}
//
//	public List<MapFieldGroup> setFieldGroupsActiveStatus(List<Integer> fieldIds, List<Integer> ismandtory,
//			FieldGroup fieldGroup, int activeStatus) {
//		return fieldIds.stream().map(fieldId -> {
//			// Set the correct value for masfieldgroupId
//
//			MapFieldGroup mapFieldGroup = new MapFieldGroup();
//			// mapFieldGroup.setId(id);
//			mapFieldGroup.setIsEnabled(activeStatus);
//			mapFieldGroup.setFieldGroup(fieldGroup);
//			mapFieldGroup.setIsActive(1);
//			if (ismandtory.contains(fieldId)) {
//				mapFieldGroup.setIsMandatory(1);
//			} else {
//				mapFieldGroup.setIsMandatory(0);
//			}
//			// mapFieldGroup.setMasFieldList();
//			// Set other properties of mapFieldGroup as needed
//			// mapFieldGroup.setSomeProperty(someValue);
//
//			MasFieldList masFieldList = new MasFieldList(); // Replace with actual MasFieldList retrieval logic
//			masFieldList.setId(fieldId); // Set the ID for the MasFieldList
//			mapFieldGroup.setMasFieldList(masFieldList);
//
//			return mapFieldGroup;
//		}).collect(Collectors.toList());
//	}
	
	
	  public Map<Integer, String> getPaymentMode() {

	       Map<Integer, String> paymentMode = new HashMap();

	    

	       for (PaymentMode types : PaymentMode.values()) {

	    	   paymentMode.put(types.getValue(), types.getDisplayName());

	       }



	       return paymentMode;

	   }
	  
	  
	  
	  public EmailBodyDto getMailBody(int emailType ) {
			EmailContent emailContent = emailContentRepository.
					findByMasEmailTypeSlNo(emailType);
			
			EmailBodyDto dto =new EmailBodyDto();
			dto.setEmailBody(emailContent.getEmailBody());
			
			return dto;
		}
		

}

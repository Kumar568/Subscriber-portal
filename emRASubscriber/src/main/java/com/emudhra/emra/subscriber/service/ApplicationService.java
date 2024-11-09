package com.emudhra.emra.subscriber.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;
import java.security.interfaces.DSAKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequest;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emudhra.emra.subscriber.config.FileUploadConfig;
import com.emudhra.emra.subscriber.dto.ApplicationDto;
import com.emudhra.emra.subscriber.dto.OrderDetailsDto;
import com.emudhra.emra.subscriber.dto.StateDto;
import com.emudhra.emra.subscriber.entity.entity.Application;
import com.emudhra.emra.subscriber.entity.entity.Customers;
import com.emudhra.emra.subscriber.entity.entity.OrderDetails;
import com.emudhra.emra.subscriber.entity.entity.ProductMaster;
import com.emudhra.emra.subscriber.entity.entity.VettingProfile;
import com.emudhra.emra.subscriber.entity.master.MasCountry;
import com.emudhra.emra.subscriber.entity.master.MasState;
import com.emudhra.emra.subscriber.mapper.ApplicationMapper;
import com.emudhra.emra.subscriber.mapper.StatesMapper;
import com.emudhra.emra.subscriber.repository.ApplicationRepository;
import com.emudhra.emra.subscriber.repository.CreateOrderRepository;
import com.emudhra.emra.subscriber.repository.MasCountryRepository;
import com.emudhra.emra.subscriber.repository.MasStateRepository;
import com.emudhra.emra.subscriber.repository.OrderDetailsRepository;
import com.emudhra.emra.subscriber.repository.VettingProfileRepository;

@Service
public class ApplicationService {
	@Autowired
	private FileUploadConfig fileUploadConfig;
	@Autowired
	private CreateOrderRepository createOrderRepository;
	// private final Path uploadDirectory;
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	@Autowired
	private ApplicationRepository applicationRepository;
	@Autowired
	private MasStateRepository masStateRepository;
	@Autowired
	private MasCountryRepository masCountryRepository;
	@Autowired
	private StatesMapper statesMapper;
	@Autowired
	private ApplicationMapper applicationMapper;
	
	@Autowired
	private VettingProfileRepository vettingProfileRepository;
	 public Long fetchLatestApplicationNumber() {
	        Optional<Application> latestApplication = applicationRepository.findTopByOrderByApplicationNumberDesc();
	        return latestApplication.map(Application::getApplicationNumber).orElse(null);
	    }
	// @Transactional
	 public Application saveApplication(Application application) {
			return applicationRepository.save(application);
		}
	 public Application saveCertificateDetails(Application application) {
			return applicationRepository.save(application);
		}
	 public Application getApplicationbyId(Long id) {
		 return applicationRepository.findById(id).orElse(null);
	 }
	 
		public VettingProfile getVettingProfileDetails(ProductMaster certificateTemplate) {
		    try {
		    	VettingProfile vettingProfile = vettingProfileRepository.findBycertificateTemplate(certificateTemplate);
				return vettingProfile;
		    } catch (Exception e) {

		        e.printStackTrace(); 
		        return null; 
		    }
		}
		
		public OrderDetails getOrderDetailsDetails(Long id) {
		    try {
		    	OrderDetails orderDetails = orderDetailsRepository.findById(id).get();
				return orderDetails;
		    } catch (Exception e) {

		        e.printStackTrace(); 
		        return null; 
		    }
		}
		
		
		public boolean isexistingorderId(Long orderId) {
		    try {
		    	OrderDetails orderDetails=new OrderDetails ();
				orderDetails.setId(orderId);
		    	Application existingorderId = applicationRepository.findByOrderDetails(orderDetails);
		        return existingorderId != null;
		    } catch (Exception e) {
		        e.printStackTrace(); 
		        return false; 
		    }
		}
		
		public ApplicationDto getApplicationdetails(Long orderId) {
			OrderDetails orderDetails=new OrderDetails ();
			orderDetails.setId(orderId);
		    Application applicationDetails = applicationRepository.findByOrderDetails(orderDetails);

		    if (applicationDetails != null) {
		        ApplicationDto applicationDto = applicationMapper.mapApplicationToApplicationDto(applicationDetails);
		        return applicationDto;
		    } else {
		        // Handle the case where the OrderDetails with the given ID is not found
		        // For example, you can throw an exception or return a default DTO
		        //throw new NotFoundException("Application not found for order ID: " + orderId);
		        // Or return a default DTO
		    	return null;
		        // return new ApplicationDto(); 
		    }
			
		}
		
		public Application getApplicationdetailsById(Long orderId) {
			OrderDetails orderDetails=new OrderDetails ();
			orderDetails.setId(orderId);
		    Application applicationDetails = applicationRepository.findByOrderDetails(orderDetails);

		    if (applicationDetails != null) {
//		        ApplicationDto applicationDto = applicationMapper.mapApplicationToApplicationDto(applicationDetails);
		        return applicationDetails;
		    } else {
		        // Handle the case where the OrderDetails with the given ID is not found
		        // For example, you can throw an exception or return a default DTO
		        //throw new NotFoundException("Application not found for order ID: " + orderId);
		        // Or return a default DTO
		    	return null;
		        // return new ApplicationDto(); 
		    }
			
		}
		
		
		
		
		
		
		
		public List<StateDto> getStateDetails(int id) {
	        List<MasState> states = masStateRepository.findBycountryId(id);

	 

	        return statesMapper.mapMasStateToStateDto(states);

	 

	    }
		public List<StateDto> getStateallDetails(){
		 List<MasState> states = masStateRepository.findAll();
		 return statesMapper.mapMasStateToStateDto(states);
		}
		
		public List<MasCountry> getCountryDetails() {
			return masCountryRepository.findAll();

		}

		
	

		
		public boolean hasCsrExtension(MultipartFile file) {
		    // Check if the file has a CSR extension
		    String fileName = file.getOriginalFilename();
		    return fileName != null && fileName.toLowerCase().endsWith(".csr");
		}

		public void saveUploadedFile(MultipartFile file) throws IOException {
		    // Save the file to the server
		    Path filePath = Paths.get(fileUploadConfig.getUploadDir(), file.getOriginalFilename());
		    file.transferTo(filePath.toFile());
		}
		
		
		 public boolean validateCsrFormat(String csrContent) {
		        // Define a regular expression pattern for a simple CSR format check
		        String csrPattern = "-----BEGIN CERTIFICATE REQUEST-----[\\s\\S]*-----END CERTIFICATE REQUEST-----";

		        // Compile the pattern
		        Pattern pattern = Pattern.compile(csrPattern);

		        // Match the pattern against the CSR content
		        Matcher matcher = pattern.matcher(csrContent);

		        // Check if the pattern is found
		        return matcher.find();
		    }
		 
		 static {
		        Security.addProvider(new BouncyCastleProvider());
		    }

		    public ResponseEntity<?> validateAndProcessCsr(MultipartFile file) {
		        try {
		            HashMap<String, Object> csrDetails = getCsrDetails(file);
		            // Process csrDetails as needed

		            return ResponseEntity.ok(csrDetails);
		        } catch (IOException e) {
		            e.printStackTrace();
		            return ResponseEntity.status(500).body("Error processing CSR file");
		        }
		    }
		    
		    public PKCS10CertificationRequest parseCsr(MultipartFile file) throws IOException {
		   
		        byte[] csrBytes = file.getBytes();
		        String csrContent = new String(csrBytes);

		        // Convert the PEM-encoded CSR content to PKCS10CertificationRequest
		        return convertPemToPKCS10CertificationRequest(csrContent);
		    }

		    private PKCS10CertificationRequest convertPemToPKCS10CertificationRequest(String pemCsr) throws IOException {
		 
		        return parsePemCsr(pemCsr);
		    }

		    
		    public static PKCS10CertificationRequest parsePemCsr(String pemCsr) throws IOException {
		        try (StringReader stringReader = new StringReader(pemCsr);
		             PemReader pemReader = new PemReader(stringReader)) {

		            PemObject pemObject = pemReader.readPemObject();
		            byte[] csrBytes = pemObject.getContent();

		            return new PKCS10CertificationRequest(csrBytes);
		        }
		    }

		    private HashMap<String, Object> getCsrDetails(MultipartFile file) throws IOException {
		        HashMap<String, Object> details = new HashMap<>();
		        
		        int keySize = 0;
		        String algo = "";
		        String keyWarningMessage = "";
		        Provider bcProvider = new BouncyCastleProvider();
		        PKCS10CertificationRequest csr = parseCsr(file);
		        if (csr == null) {
		            details.put("status", "Invalid CSR file");
		            return details;
		        }
		        String subject = csr.getSubject().toString();
		        
	            String[] values = subject.split(",");
	            
	            for (String value : values) {
	                String[] keyValue = value.trim().split("=");
	                if (keyValue.length == 2) {
	                    details.put(keyValue[0].trim(), keyValue[1].trim());
	                }
	            }
	            
	            /* Publickey */
	            JcaPKCS10CertificationRequest jcaReq = new JcaPKCS10CertificationRequest(csr).setProvider(bcProvider);
	            PublicKey pubkey = null;
				try {
					pubkey = jcaReq.getPublicKey();
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	         // Verifies Signature
	            SubjectPublicKeyInfo publicKeyInfo = csr.getSubjectPublicKeyInfo();
	          
	            boolean isSign = false;
				try {
					isSign = csr.isSignatureValid(new JcaContentVerifierProviderBuilder()
					        .setProvider(bcProvider).build(publicKeyInfo));
				} catch (OperatorCreationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PKCSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	            String status;
	            String responseMessage;
	            if (isSign) {
	                status = "2";
	                responseMessage = "Valid Signature";
	            } else {
	                status = "2";
	                responseMessage = "Invalid Signature";
	            }

	            details.put("status", status);
	            details.put("responseMessage", responseMessage);
	            
	            
	            if (pubkey instanceof RSAPublicKey) {
	                keySize = ((RSAKey) pubkey).getModulus().bitLength();
	                if (keySize < 2048) {
	                    keyWarningMessage = "Keysize is too small";
	                }
	                algo = pubkey.getAlgorithm();
	            } else if (pubkey instanceof DSAPublicKey) {
	                keySize = ((DSAKey) pubkey).getParams().getP().bitLength();
	                if (keySize < 2048) {
	                    keyWarningMessage = "Keysize is too small";
	                }
	                algo = pubkey.getAlgorithm();
	            } else if (pubkey instanceof ECPublicKey) {
	                keySize = ((ECKey) pubkey).getParams().getOrder().bitLength();
	                if (keySize < 256) {
	                    keyWarningMessage = "Keysize is too small";
	                }
	                algo = pubkey.getAlgorithm();
	            }

	            String invalidKeyMessage = keyWarningMessage;
	            if (!keyWarningMessage.isEmpty()) {
	                invalidKeyMessage = "( " + keyWarningMessage + " )";
	            }
	            details.put("keySize", keySize);
	            details.put("invalidKeyMessage", invalidKeyMessage);
		        // Example: Extract country from CSR
		        String country = extractCsrField(csr, BCStyle.C, "SampleCountry");
		        details.put("country", country);

		        // Example: Extract key size from CSR
//		        int keySize = extractKeySize(csr);
//		        details.put("keySize", keySize);
		        
		        String keyAlgorithm = getPublicKeyAlgorithm(csr.getSubjectPublicKeyInfo().getAlgorithm());
		        details.put("keyAlgorithm", keyAlgorithm);

		        // Example: Extract algorithm from CSR
		        String algorithm = extractCsrField(csr, X509ObjectIdentifiers.id_ea_rsa, "SampleAlgorithm");
		        details.put("algorithm", algorithm);

		        // Example: Extract signature algorithm from CSR
		        String signatureAlgorithm = getSignatureAlgorithm(csr.getSignatureAlgorithm());
		        details.put("signatureAlgorithm", signatureAlgorithm);
		        
		        
		        String publicKeyPEM = convertToPEM(csr);
		        details.put("publicKeyPEM", publicKeyPEM);
		        
		        

		        // Example: Extract fingerprint details
		        String fingerPrint_MD5 = generateFingerPrintMD5(csr);
		        String fingerPrint_SHA1 = generateFingerPrintSHA1(csr);
		        String fingerPrint_SHA256 = generateFingerPrintSHA256(csr);
		        details.put("fingerPrint_MD5", fingerPrint_MD5);
		        details.put("fingerPrint_SHA1", fingerPrint_SHA1);
		        details.put("fingerPrint_SHA256", fingerPrint_SHA256);

		        // Add other details to the map

		        return details;
		    }
		    
		    public String convertToPEM(PKCS10CertificationRequest csr) throws IOException {
	            StringWriter output = new StringWriter();
	            try (JcaPEMWriter pemWriter = new JcaPEMWriter(output)) {
	                pemWriter.writeObject(csr.getSubjectPublicKeyInfo());
	            }
	            return output.toString();
	        }
		    
		    private String getSignatureAlgorithm(AlgorithmIdentifier signatureAlgorithmIdentifier) {
		        String algorithmName = signatureAlgorithmIdentifier.getAlgorithm().getId();
		        return algorithmName.replaceAll("-", "WITH");
		    }
		    
		 // Method to get the key algorithm from the public key algorithm identifier
		    private String getPublicKeyAlgorithm(AlgorithmIdentifier algorithmIdentifier) {
		        ASN1ObjectIdentifier keyAlgorithmId = algorithmIdentifier.getAlgorithm();
		        
		        // You may need to map ASN.1 Object Identifiers to human-readable key algorithm names
		        // Here's an example for RSA
		        if (keyAlgorithmId.equals(PKCSObjectIdentifiers.rsaEncryption)) {
		            return "RSA";
		        }

		        // Add more mappings for other key algorithms as needed

		        return keyAlgorithmId.getId(); // Default to returning the OID as a string
		    }


		    private <T> T extractCsrField(PKCS10CertificationRequest csr, ASN1ObjectIdentifier field, T defaultValue) {
		        X500Name subject = csr.getSubject();
		        RDN[] rdns = subject.getRDNs(field);

		        if (rdns != null && rdns.length > 0) {
		            return (T) rdns[0].getFirst().getValue().toString();
		        }

		        return defaultValue;
		    }

		    private int extractKeySize(PKCS10CertificationRequest csr) {
		        SubjectPublicKeyInfo keyInfo = csr.getSubjectPublicKeyInfo();
		        try {
		            JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
		            PublicKey publicKey = converter.getPublicKey(keyInfo);
		            // Implement logic to extract key size from PublicKey
		            // For example, if RSA key
		            if ("RSA".equals(publicKey.getAlgorithm())) {
		                return ((java.security.interfaces.RSAPublicKey) publicKey).getModulus().bitLength();
		            }
		            // For other key types, you need to implement similar logic
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return 0; // Sample key size
		    }
		    

		    private String generateFingerPrintSHA256(PKCS10CertificationRequest csr) {
		        try {
		            MessageDigest md = MessageDigest.getInstance("SHA-256");
		            byte[] digest = md.digest(csr.getEncoded());

		            // Convert the byte array to a hexadecimal string
		            StringBuilder hexString = new StringBuilder();
		            for (byte b : digest) {
		                hexString.append(String.format("%02x", b));
		            }

		            return hexString.toString();
		        } catch (Exception e) {
		            e.printStackTrace();
		            return "ErrorGeneratingFingerprint";
		        }
		    }
		    private String generateFingerPrintSHA1(PKCS10CertificationRequest csr) {
		        try {
		            MessageDigest md = MessageDigest.getInstance("SHA-1");
		            byte[] digest = md.digest(csr.getEncoded());

		            // Convert the byte array to a hexadecimal string
		            StringBuilder hexString = new StringBuilder();
		            for (byte b : digest) {
		                hexString.append(String.format("%02x", b));
		            }

		            return hexString.toString();
		        } catch (Exception e) {
		            e.printStackTrace();
		            return "ErrorGeneratingFingerprint";
		        }
		    }
		    
		    private String generateFingerPrintMD5(PKCS10CertificationRequest csr) {
		        try {
		            MessageDigest md = MessageDigest.getInstance("MD5");
		            byte[] digest = md.digest(csr.getEncoded());

		            // Convert the byte array to a hexadecimal string
		            StringBuilder hexString = new StringBuilder();
		            for (byte b : digest) {
		                hexString.append(String.format("%02x", b));
		            }

		            return hexString.toString();
		        } catch (NoSuchAlgorithmException nsae) {
		            nsae.printStackTrace();
		            return "NoSuchAlgorithmException";
		        } catch (Exception e) {
		            e.printStackTrace();
		            return "ErrorGeneratingFingerprint";
		        }
		    }

		 
	    }



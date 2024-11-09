package com.emudhra.emra.subscriber.business;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Principal;
import java.security.Provider;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class CryptoUtilities {

	private static final String key = "emHelper";
	Provider bc = new BouncyCastleProvider();
	private final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

	public String AESencrypt(String value) throws Exception {
		try {

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray(),
					new byte[] { 0x49, 0x76, 0x61, 0x6e, 0x20, 0x4d, 0x65, 0x64, 0x76, 0x65, 0x64, 0x65, 0x76 }, 1000,
					384);
			Key secretKey = factory.generateSecret(pbeKeySpec);

			byte[] key1 = new byte[32];
			byte[] iv = new byte[16];

			System.arraycopy(secretKey.getEncoded(), 0, key1, 0, 32);
			System.arraycopy(secretKey.getEncoded(), 32, iv, 0, 16);

			IvParameterSpec iv1 = new IvParameterSpec(iv);
			SecretKeySpec skeySpec = new SecretKeySpec(key1, "AES");
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);

			byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
			String encrypteddata = new String(Base64.encode(encrypted));
			return encrypteddata;
		} catch (Exception ex) {

			return null;
		}
	}

	public Map<String, String> AESdecrypt(String encrypted) throws Exception {
		Map<String,String> result=new HashMap<String, String>();
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray(),
					new byte[] { 0x49, 0x76, 0x61, 0x6e, 0x20, 0x4d, 0x65, 0x64, 0x76, 0x65, 0x64, 0x65, 0x76 }, 1000,
					384);

			Key secretKey = factory.generateSecret(pbeKeySpec);
			byte[] key1 = new byte[32];
			byte[] iv = new byte[16];
			System.arraycopy(secretKey.getEncoded(), 0, key1, 0, 32);
			System.arraycopy(secretKey.getEncoded(), 32, iv, 0, 16);

			IvParameterSpec iv1 = new IvParameterSpec(iv);
			SecretKeySpec skeySpec = new SecretKeySpec(key1, "AES");
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
			byte[] data = Base64.decode(encrypted);
			byte[] original = cipher.doFinal(data);
			String base = new String(Base64.encode(original));
			String val = String.valueOf(original);
			String converted = new String(original);
			result.put("status", "1");
			result.put("response", converted);
			return result;
		} catch (Exception ex) {
			return null;
		}
	}


	public Map<String, String> getValidFromToSlnumber(String x509certificate) throws CertificateException {
		Map<String, String> certificateDetails = new HashMap<>();
		X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509")
				.generateCertificate(new ByteArrayInputStream(Base64.decode(x509certificate)));

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String validFrom = formatter.format(x509Certificate.getNotBefore());
		String validTo = formatter.format(x509Certificate.getNotAfter());

		// BigInteger serialnumber = x509Certificate.getSerialNumber();
		String slNumber = x509Certificate.getSerialNumber().toString();
		certificateDetails.put("validFrom", validFrom);
		certificateDetails.put("validTo", validTo);
		certificateDetails.put("slNumber", slNumber);
		return certificateDetails;
	}

	public HashMap<String, String> verifyCRL(String CertData) throws Exception {
		HashMap<String, String> crlverification = new HashMap<>();
		try {
			String pemFormatCert = pemFormat(CertData);
			X509Certificate Cert = getX509CertificateFromPublicKey(pemFormatCert);
			// CRL checking
			URL url = getCrlDistributionPoint(Cert);
			if (url != null && url.toString().equals("InValid")) {
				crlverification.put("status", "0");
				crlverification.put("erromessage", "Invalid CRL URL");
				return crlverification;
			}
			if (null != url) {
				String urlnew = "";
				urlnew = url.toString();
				crlverification = downloadCrl(urlnew, Cert);
			} else {
				crlverification.put("status", "1");
			}
		} catch (Exception ex) {

		}
		return crlverification;
	}

	public URL getCrlDistributionPoint(final Certificate certificate) throws Exception {
		List<URL> urlList = new ArrayList<URL>();
		try {
			if (certificate instanceof X509Certificate) {
				final X509Certificate x509cert = (X509Certificate) certificate;
				final Collection<URL> cdps = getCrlDistributionPoints(x509cert, false);
//	                if (!cdps.isEmpty()) {
//	                    return cdps.iterator().next();
//	                }
				if (!cdps.isEmpty()) {
					for (URL u : cdps) {
						urlList.add(u);

					}
				}
//	                while (cdps.iterator().hasNext()) {
//	                    if (!cdps.isEmpty()) {
//	                        urlList.add(cdps.iterator().next());
//	                        Logging.printOutput("\nDate:" + getCurrentDateTime() + " | URL :" + cdps.iterator().next().toString() + "\n");
//	                    }
//	                }
			}
			if (urlList.isEmpty()) {
				return null;
			} else {
				for (int i = 0; i < urlList.size(); i++) {
					boolean pingResult = pingURL(urlList.get(i).toString(), 1000);
					if (pingResult) {

						return urlList.get(i);
					}
				}
			}
		} catch (Exception ex) {

		}
		return null;
	}

	public Collection<URL> getCrlDistributionPoints(final X509Certificate x509cert, final boolean onlyfirst)
			throws Exception {
		ArrayList<URL> cdps = new ArrayList<URL>();
		final ASN1Primitive obj = getExtensionValue(x509cert, "2.5.29.31");
		if (obj == null) {
			return cdps;
		}
		final ASN1Sequence crlDistributionPoints = (ASN1Sequence) obj;
		for (int i = 0; i < crlDistributionPoints.size(); i++) {
			ASN1Sequence distributionPoint = (ASN1Sequence) crlDistributionPoints.getObjectAt(i);
			for (int j = 0; j < distributionPoint.size(); j++) {
				ASN1TaggedObject tagged = (ASN1TaggedObject) distributionPoint.getObjectAt(j);
				if (tagged.getTagNo() == 0) {
					String url = getStringFromGeneralNames(tagged.getObject());
					if (url != null) {
						try {
							cdps.add(new URL(url));
						} catch (Exception e) {

						}
					}
					if (onlyfirst) {
						return cdps;
					}
				}
			}
		}
		return cdps;
	}

	public static String getStringFromGeneralNames(ASN1Primitive names) {
		ASN1Sequence namesSequence = ASN1Sequence.getInstance((ASN1TaggedObject) names, false);
		if (namesSequence.size() == 0) {
			return null;
		}
		DERTaggedObject taggedObject = (DERTaggedObject) namesSequence.getObjectAt(0);
		if (taggedObject.getTagNo() != GeneralName.uniformResourceIdentifier) { // uniformResourceIdentifier [6]
																				// IA5String,
			return null;
		}
		return new String(ASN1OctetString.getInstance(taggedObject, false).getOctets());
	}

	public static ASN1Primitive getExtensionValue(X509Certificate x509Certificate, String id) throws Exception {
		if (x509Certificate == null) {
			return null;
		}
		return getDerObjectFromByteArray(x509Certificate.getExtensionValue(id));
	}

	public static ASN1Primitive getDerObjectFromByteArray(byte[] bytes) throws Exception {
		if (bytes == null) {
			return null;
		}
		ASN1Primitive extensionvalue = null;
		try {
			extensionvalue = ASN1Primitive.fromByteArray(ASN1OctetString.getInstance(bytes).getOctets());
		} catch (Exception ex) {

		}
		return extensionvalue;
	}

	public static boolean pingURL(String url, int timeout) {
		url = url.replaceFirst("^https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			connection.setRequestMethod("HEAD");
			int responseCode = connection.getResponseCode();
			return (200 <= responseCode && responseCode <= 399);
		} catch (Exception exception) {
			return false;
		}
	}

	public HashMap<String, String> downloadCrl(String urlString, X509Certificate cert) throws Exception {
		URL url = new URL(urlString);
		URLConnection con = url.openConnection();
		InputStream is = null;
		X509CRL crl;
		X509CRLEntry revokedCertificate = null;
		HashMap<String, String> crlverification = new HashMap<>();
		String status = "2";
		String erromessage = "";

		try {
			is = con.getInputStream();
			crl = (X509CRL) CertificateFactory.getInstance("X.509").generateCRL(is);
			revokedCertificate = crl.getRevokedCertificate(((X509Certificate) cert).getSerialNumber());
			if (revokedCertificate != null) {
				status = "2";
				erromessage = "Certificate is revoked";
			} else {
				status = "1";
			}
		} catch (Exception ex) {
			status = "2";
			erromessage = "CRL URL Cannot be Found";

		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
					status = "2";
					erromessage = "Unknown error occurred. Please contact administrator";

				}
			}
		}
		crlverification.put("status", status);
		crlverification.put("erromessage", erromessage);
		return crlverification;
	}

	public JSONObject isPKCS7Attached(String signed_data) {
		boolean isAttached = false;
		JSONObject resp = new JSONObject();
		resp.put("status", "0");
		resp.put("status", "0");

		try {
			byte[] decodedBytes = Base64.decode(signed_data);
			ASN1InputStream asn1InputStream = new ASN1InputStream(decodedBytes);
			ContentInfo contentInfo = ContentInfo.getInstance(asn1InputStream.readObject());

			if (contentInfo.getContentType().equals(PKCSObjectIdentifiers.signedData)) {
				CMSSignedData signedData = new CMSSignedData(contentInfo);

				X509Certificate[] certificates = (X509Certificate[]) signedData.getCertificates().getMatches(null)
						.toArray(new X509Certificate[0]);
				System.out.println(certificates[0].getSerialNumber());

				byte[] originalData = (byte[]) signedData.getSignedContent().getContent();
				resp.put("tbs", new String(originalData));
				if (originalData.length > 0) {
					isAttached = true;
					resp.put("status", "1");
				}
			}
		} catch (Exception ex) {
			// Handle the exception
		}
		return resp;
	}

	public static String pemFormat(String base64Cert) {
		String returnString = "";
		for (int i = 0; i < base64Cert.length(); i++) {
			if (i % 64 == 0) {
				returnString = returnString + "\n" + base64Cert.charAt(i);
			} else {
				returnString = returnString + base64Cert.charAt(i);
			}
		}
		returnString = "-----BEGIN CERTIFICATE-----" + returnString + "\n-----END CERTIFICATE-----";
		return returnString;
	}

	public X509Certificate getX509CertificateFromPublicKey(String data)
			throws GeneralSecurityException, IOException, Exception {
		InputStream fis = null;
		try {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			fis = new ByteArrayInputStream(data.getBytes());
			return (X509Certificate) certFactory.generateCertificate(fis);
		} catch (Exception ex) {

			return null;
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}

	public boolean VerifyPKCS7SignedData(String Data, String SignedData, String CertData) throws Exception {
		boolean verifyResult = false;
		JSONObject resp = isPKCS7Attached(SignedData);
		if (resp.get("status").equals("1")) {
			try {
				byte[] signedByte = Base64.decode(SignedData);
				Security.addProvider(bc);
				CMSSignedData s = new CMSSignedData(signedByte);
				SignerInformationStore signers = s.getSignerInfos();
				SignerInformation signerInfo = (SignerInformation) signers.getSigners().iterator().next();
				String pemFormatCert = pemFormat(CertData);
				X509Certificate Cert = getX509CertificateFromPublicKey(pemFormatCert);
				verifyResult = signerInfo
						.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider(bc).build(Cert.getPublicKey()));
				if (verifyResult) {
					verifyResult = Data.equals(resp.get("tbs").toString());
				}
			} catch (Exception ex) {

			}
		} else {
			try {
				byte[] signedByte = Base64.decode(SignedData);
				Security.addProvider(bc);
				CMSSignedData s = new CMSSignedData(new CMSProcessableByteArray(Data.getBytes()), signedByte);
				SignerInformationStore signers = s.getSignerInfos();
				SignerInformation signerInfo = (SignerInformation) signers.getSigners().iterator().next();
				String pemFormatCert = pemFormat(CertData);
				X509Certificate Cert = getX509CertificateFromPublicKey(pemFormatCert);
				verifyResult = signerInfo
						.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider(bc).build(Cert.getPublicKey()));
			} catch (Exception ex) {

			}
		}
		return verifyResult;
	}

	public static boolean VerifyChain(String CertData) throws Exception {
		boolean result = false;
//        X509Certificate x = null;
//        List<X509Certificate> certList = new ArrayList<X509Certificate>();
		try {
//            String pemFormatCert = pemFormat(CertData);
//            X509Certificate Cert = getX509CertificateFromPublicKey(pemFormatCert);
//            certList.add(Cert);
//            result = IsSelfSignedCertificate(certList);
//            if (!result == true) {
//                x = getCertificateChainList(Cert);
//                do {
//                    if (x != null) {
//                        certList.add(x);
//                    }
//                    x = getCertificateChainList(x);
//
//                } while (x != null);
//                if (certList.size() > 1) {
			result = true;
//                }
//            }

		} catch (Exception ex) {

		}
		return result;
	}

	public  Map<String, String> getCertificateDetails(String certificatePem) {
		try {
			Map certInfo = new HashMap();
			certificatePem = certificatePem.replace("-----BEGIN CERTIFICATE-----", "");
			certificatePem = certificatePem.replace("-----END CERTIFICATE-----", "");
			certInfo.put("base64publickey", certificatePem);
			certificatePem = "-----BEGIN CERTIFICATE-----\n" + certificatePem + "\n-----END CERTIFICATE-----";
			X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509")
					.generateCertificate(new ByteArrayInputStream(certificatePem.getBytes()));
			X509CertificateHolder xch = new JcaX509CertificateHolder(x509Certificate);
			String issuedTo = IETFUtils.valueToString(xch.getSubject().getRDNs(BCStyle.CN)[0].getFirst().getValue());
			String issuedBy = IETFUtils.valueToString(xch.getIssuer().getRDNs(BCStyle.CN)[0].getFirst().getValue());
			certInfo.put("issuedto", issuedTo);
			certInfo.put("issuedby", issuedBy);
			String className = "Not Available";
			String isDocumentSigner = "No";
			byte[] UID = x509Certificate.getExtensionValue("2.5.29.32");

			if (UID != null) {
				ASN1Primitive derObject = toDERObject(UID);

				if (derObject instanceof DEROctetString) {
					DEROctetString derOctetString = (DEROctetString) derObject;
					derObject = toDERObject(derOctetString.getOctets());
					ASN1Sequence seq = (ASN1Sequence) derObject;
					Enumeration secEnum = seq.getObjects();
					while (secEnum.hasMoreElements()) {
						ASN1Primitive seqObj = (ASN1Primitive) secEnum.nextElement();
						ASN1Sequence seq2 = (ASN1Sequence) seqObj;
						String OID = seq2.getObjectAt(0).toString();
						switch (OID) {
						case "2.16.356.100.2.0":
							className = "Class 0";
							break;
						case "2.16.356.100.2.1":
							className = "Class 1";
							break;
						case "2.16.356.100.2.2":
							className = "Class 2";
							break;
						case "2.16.356.100.2.3":
							className = "Class 2";
							break;
						case "2.16.356.100.2.4.1":
							className = "Aadhaar eKYC-OTP";
							break;
						case "2.16.356.100.2.4.2":
							className = "Aadhaar eKYC-Biometric";
							break;
						case "2.16.356.100.10.1":
							isDocumentSigner = "Yes";
							break;
						}
					}
				}
			}
			certInfo.put("classname", className);
			certInfo.put("isdocumentsigner", isDocumentSigner);

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			String validFrom = formatter.format(x509Certificate.getNotBefore());
			String validTo = formatter.format(x509Certificate.getNotAfter());
			certInfo.put("validfrom", validFrom);
			certInfo.put("validto", validTo);

			String serialNumberHex = x509Certificate.getSerialNumber().toString(16);
			String serialNumberDec = x509Certificate.getSerialNumber().toString();
			certInfo.put("serialnumberhex", serialNumberHex);
			certInfo.put("serialnumberdec", serialNumberDec);

			// Get subject
			Principal principal = x509Certificate.getSubjectDN();
			String subjectDn = principal.getName();
			certInfo.put("subjectdn", subjectDn);

			String keyUsages = "Not Available";
			boolean[] keyUsage = x509Certificate.getKeyUsage();
			if (keyUsage != null) {
				List<String> keyUsagesList = new ArrayList<String>();
				if (keyUsage[0]) {
					keyUsagesList.add("digitalSignature");
				}
				if (keyUsage[1]) {
					keyUsagesList.add("nonRepudiation");
				}
				if (keyUsage[2]) {
					keyUsagesList.add("keyEncipherment");
				}
				if (keyUsage[3]) {
					keyUsagesList.add("dataEncipherment");
				}
				if (keyUsage[4]) {
					keyUsagesList.add("keyAgreement");
				}
				if (keyUsage[5]) {
					keyUsagesList.add("keyCertSign");
				}
				if (keyUsage[6]) {
					keyUsagesList.add("cRLSign");
				}
				if (keyUsage[7]) {
					keyUsagesList.add("encipherOnly");
				}
				if (keyUsage[8]) {
					keyUsagesList.add("decipherOnly");
				}

				keyUsages = convertToCommaDelimited(keyUsagesList);
			}
			certInfo.put("keyusages", keyUsages);
			
			return certInfo;
		} catch (Exception ex) {
			
			return null;
		}
	}
	
	private  String convertToCommaDelimited(List list) {
        String ret = "";
        for (int i = 0; i < list.size(); i++) {
            ret += list.get(i);
            if (i < list.size() - 1) {
                ret += ", ";
            }
        }
        return ret;
    }

    private  ASN1Primitive toDERObject(byte[] data) throws IOException {
        ByteArrayInputStream inStream = new ByteArrayInputStream(data);
        ASN1InputStream DIS = new ASN1InputStream(inStream);
        return DIS.readObject();
    }
    
    public static String AESencrypt(String value, String source) throws Exception {
        try {
        	String key = "";
            if (source.equals("VR")) {
                key = "VR#CliEnt$";
            } else if (source.equals("SLID")) {
                key = "shareLinkID";
            } else {
                key = "emHelper";
            }

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray(), new byte[]{0x49, 0x76, 0x61, 0x6e, 0x20, 0x4d, 0x65, 0x64, 0x76, 0x65, 0x64, 0x65, 0x76}, 1000, 384);
            Key secretKey = factory.generateSecret(pbeKeySpec);

            byte[] key1 = new byte[32];
            byte[] iv = new byte[16];

            System.arraycopy(secretKey.getEncoded(), 0, key1, 0, 32);
            System.arraycopy(secretKey.getEncoded(), 32, iv, 0, 16);

            IvParameterSpec iv1 = new IvParameterSpec(iv);
            SecretKeySpec skeySpec = new SecretKeySpec(key1, "AES");
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);

            byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
            String encrypteddata = new String(Base64.encode(encrypted));
            return encrypteddata;
        } catch (Exception ex) {

			return null;
		}
    }

    
    
    public static String AESdecrypt(String encrypted, String source) throws Exception {
     	String key = "";
        try {
            if (source.equals("VR")) {
                key = "VR#CliEnt$";
            } else {
                key = "emHelper";
            }
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray(), new byte[]{0x49, 0x76, 0x61, 0x6e, 0x20, 0x4d, 0x65, 0x64, 0x76, 0x65, 0x64, 0x65, 0x76}, 1000, 384);
 
            Key secretKey = factory.generateSecret(pbeKeySpec);
            byte[] key1 = new byte[32];
            byte[] iv = new byte[16];
            System.arraycopy(secretKey.getEncoded(), 0, key1, 0, 32);
            System.arraycopy(secretKey.getEncoded(), 32, iv, 0, 16);
 
            IvParameterSpec iv1 = new IvParameterSpec(iv);
            SecretKeySpec skeySpec = new SecretKeySpec(key1, "AES");
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
            byte[] data = Base64.decode(encrypted);
            byte[] original = cipher.doFinal(data);
            String base = new String(Base64.encode(original));
            String val = String.valueOf(original);
            String converted = new String(original);
 
            return converted;
        } catch (Exception ex) {

 
            return null;
        }
    }  
    
    
    
}

package com.banred.endpoint;

import javax.xml.XMLConstants;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.banred.config.IConstants;
import com.banred.model.ConsultarCEP;
import com.banred.model.ConsultarCEPResponse;
import com.banred.service.ConsultarCEPResultService;
import com.banred.service.ObjectFactory;

/**
 * The Class CardmarteEndpoint.
 */

@Endpoint
public class MunicipioGyeCepEndpoint implements IConstants {

	/** The Constant NAMESPACE. */
	public static final String NAMESPACE = "http://tempuri.org/";

	



	/** The operation tag data. */
	@Autowired
	ConsultarCEPResultService consultarCEPResultService;

	private static final Logger logger = LoggerFactory.getLogger(MunicipioGyeCepEndpoint.class);

	/**
	 * Prints the SOAP response.
	 *
	 * @param soapResponse the soap response
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unused")
	private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);
	}

	/**
	 * Creates the SOAP request.
	 *
	 * @param comercio the comercio
	 * @param tipo_trx the tipo trx
	 * @return the SOAP message
	 * @throws Exception the exception
	 */
	private static SOAPMessage createSOAPRequest(String comercio, String tipo_trx) throws Exception {

		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		// envelope.addNamespaceDeclaration("sam", "http://samples.axis2.techdive.in");
		envelope.addNamespaceDeclaration("web", "http://webservice/");
		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
	//	SOAPElement soapBodyElem = soapBody.addChildElement("comercio", "web");
	//	SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("codSimulador");
	//	SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("tipoTransacion");
	//	soapBodyElem1.addTextNode(comercio);
	//	soapBodyElem2.addTextNode(tipo_trx);
		soapMessage.saveChanges();
		soapMessage.writeTo(System.out);
		System.out.println();
		return soapMessage;
	}
	
	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_CONSULTAR_CEP)
	@ResponsePayload
	public ConsultarCEPResponse ConsultarCEP(@RequestPayload ConsultarCEP request) {
		System.out.println("Test");
		ObjectFactory obj = new ObjectFactory();
		ConsultarCEPResponse response = obj.createConsultarCEPResponse();
		logger.info("getMethod: " + request.getCodigoTransaccion() + "\n");
		try {
			response.setConsultarCEPResult(consultarCEPResultService.createConsultarCEPResultObject());
			return response;
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			return null;
		}
		
	}

	/**
	 * Void.
	 *
	 * @param request the request
	 * @return the void response
	 */
//	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_VOID)
//	@ResponsePayload
//	public VoidResponse Void(@RequestPayload Void request) {
//		ObjectFactory obj = new ObjectFactory();
//		VoidResponse response = obj.createVoidResponse();
//		logger.info("TN" + request.getPTransactionNumber() + "\n");
//
//		try {
//			VoidResult headerResponse = new VoidResult();
//			headerResponse.setAccountCurrencyCode("AAA");
//			headerResponse.setBalance("AAAA");
//			response.setVoidResult(headerResponse);
//
//		} catch (Exception ex) {
//			logger.debug(ex.getMessage());
//		}
//
//		return response;
//	}
//
//	/**
//	 * Void.
//	 *
//	 * @param request the request
//	 * @return the verify response
//	 */
//	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_VERIFY)
//	@ResponsePayload
//	public VerifyResponse Void(@RequestPayload Verify request) {
//		ObjectFactory obj = new ObjectFactory();
//		VerifyResponse response = obj.createVerifyResponse();
//		logger.info("getPAmount: " + request.getPAmount() + "\n");
//		try {
//			VerifyResult headerResponse = new VerifyResult();
//			headerResponse.setBalance("AAA111");
//			;
//			headerResponse.setCurrencyAccount("BBB11");
//			response.setVerifyResult(headerResponse);
//		} catch (Exception ex) {
//			logger.debug(ex.getMessage());
//		}
//		return response;
//	}
//
//	/**
//	 * Gets the transaction status.
//	 *
//	 * @param request the request
//	 * @return the gets the transaction status response
//	 */
//	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_TRANSACTION_STATUS)
//	@ResponsePayload
//	public GetTransactionStatusResponse GetTransactionStatus(@RequestPayload GetTransactionStatus request) {
//		ObjectFactory obj = new ObjectFactory();
//		GetTransactionStatusResponse response = obj.createGetTransactionStatusResponse();
//		logger.info("getPAmount: " + request.getPAmount() + "\n");
//		try {
//			GetTransactionStatusResult headerResponse = new GetTransactionStatusResult();
//			headerResponse.setBalance("AAA111");
//			;
//			headerResponse.setTransactionNumber("BBB11");
//			response.setGetTransactionStatusResult(headerResponse);
//		} catch (Exception ex) {
//			logger.debug(ex.getMessage());
//		}
//		return response;
//	}
//
//	/**
//	 * Gets the converted amount.
//	 *
//	 * @param request the request
//	 * @return the gets the converted amount response
//	 */
//	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_CONVERTED_AMOUNT)
//	@ResponsePayload
//	public GetConvertedAmountResponse GetConvertedAmount(@RequestPayload GetConvertedAmount request) {
//		ObjectFactory obj = new ObjectFactory();
//		GetConvertedAmountResponse response = obj.createGetConvertedAmountResponse();
//		logger.info("getPAmount: " + request.getPAmount() + "\n");
//		try {
//			GetConvertedAmountResult headerResponse = new GetConvertedAmountResult();
//			headerResponse.setAmount("AAA111");
//			OperationTagData bodyOperationData = new OperationTagData();
//			bodyOperationData.setExternalUserCode("AAA");
//			headerResponse.setOperationTag(bodyOperationData);
//			response.setGetConvertedAmountResult(headerResponse);
//		} catch (Exception ex) {
//			logger.debug(ex.getMessage());
//		}
//		return response;
//	}
//
//	/**
//	 * Gets the currency exchange rate.
//	 *
//	 * @param request the request
//	 * @return the gets the currency exchange rate response
//	 */
//
//	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_CURRENCY_EXCHANGE_RATE)
//	@ResponsePayload
//	public GetCurrencyExchangeRateResponse GetCurrencyExchangeRate(@RequestPayload GetCurrencyExchangeRate request) {
//		ObjectFactory obj = new ObjectFactory();
//		GetCurrencyExchangeRateResponse response = obj.createGetCurrencyExchangeRateResponse();
//		logger.info("getPAmount: " + request.getPOriginCurrencyCode() + "\n");
//		try {
//			GetCurrencyExchangeRateResult headerResponse = new GetCurrencyExchangeRateResult();
//			headerResponse.setSale("AAA111");
//			OperationTagData bodyOperationData = new OperationTagData();
//			bodyOperationData.setExternalUserCode("AAA");
//			headerResponse.setOperationTag(operationTagData.createOperationTagObject());
//			response.setGetCurrencyExchangeRateResult(headerResponse);
//		} catch (Exception ex) {
//			logger.debug(ex.getMessage());
//		}
//		return response;
//	}
//
//	/**
//	 * Relate existing account.
//	 *
//	 * @param request the request
//	 * @return the relate existing account response
//	 */
//	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_RELATE_EXISTING_ACCOUNT)
//	@ResponsePayload
//	public RelateExistingAccountResponse RelateExistingAccount(@RequestPayload RelateExistingAccount request) {
//		ObjectFactory obj = new ObjectFactory();
//		RelateExistingAccountResponse response = obj.createRelateExistingAccountResponse();
//		logger.info("getMethod: " + request.getPExternalCardNumber() + "\n");
//		try {
//			response.setRelateExistingAccountResult(relateExistingAccountService.creteData());
//		} catch (Exception ex) {
//			logger.debug(ex.getMessage());
//		}
//		return response;
//	}
//
//	/**
//	 * Update referred link.
//	 *
//	 * @param request the request
//	 * @return the update referred link response
//	 */
//	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_UPDATE_REFERRED_LINK)
//	@ResponsePayload
//	public UpdateReferredLinkResponse UpdateReferredLink(@RequestPayload UpdateReferredLink request) {
//		ObjectFactory obj = new ObjectFactory();
//		UpdateReferredLinkResponse response = obj.createUpdateReferredLinkResponse();
//		logger.info("getMethod: " + request.getPAddress() + "\n");
//		try {
//			response.setUpdateReferredLinkResult(updateReferredLinkService.createUpdateReferredLinkObject());
//		} catch (Exception ex) {
//			logger.debug(ex.getMessage());
//		}
//		return response;
//	}
//
//	/**
//	 * Echo.
//	 *
//	 * @param request the request
//	 * @return the echo response
//	 */
//	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_ECHO)
//	@ResponsePayload
//	public EchoResponse Echo(@RequestPayload Echo request) {
//		ObjectFactory obj = new ObjectFactory();
//		EchoResponse response = obj.createEchoResponse();
//		logger.info("getMethod: " + request.getPRequestID() + "\n");
//		try {
//			response.setEchoResult(echoResultService.creteEchoResultObject());
//		} catch (Exception ex) {
//			logger.debug(ex.getMessage());
//		}
//		return response;
//	}
//
//	/**
//	 * Enroll external account.
//	 *
//	 * @param request the request
//	 * @return the enroll external account response
//	 */
//	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_ENROLL_EXTERNAL_ACCOUNT)
//	@ResponsePayload
//	public EnrollExternalAccountResponse EnrollExternalAccount(@RequestPayload EnrollExternalAccount request) {
//		ObjectFactory obj = new ObjectFactory();
//		EnrollExternalAccountResponse response = obj.createEnrollExternalAccountResponse();
//		logger.info("getMethod: " + request.getPRequestID() + "\n");
//		try {
//			response.setEnrollExternalAccountResult(enrollExternalAccountService.createEnrollExternalAccountObject());
//			return response;
//		} catch (Exception ex) {
//			logger.debug(ex.getMessage());
//			return null;
//		}
//		
//	}
//	
//	
//	/**
//	 * Execute transaction.
//	 *
//	 * @param request the request
//	 * @return the execute transaction response
//	 */
//	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_EXECUTE_TRANSACTION)
//	@ResponsePayload
//	public ExecuteTransactionResponse ExecuteTransaction(@RequestPayload ExecuteTransaction request) {
//		ObjectFactory obj = new ObjectFactory();
//		ExecuteTransactionResponse response = obj.createExecuteTransactionResponse();
//		logger.info("getMethod: " + request.getPRequestID() + "\n");
//		try {
//			response.setExecuteTransactionResult(executeTransactionResultService.createExecuteTransactionObject());
//			return response;
//		} catch (Exception ex) {
//			logger.debug(ex.getMessage());
//			return null;
//		}
//		
//	}
//	
//	/**
//	 * Request external account.
//	 *
//	 * @param request the request
//	 * @return the request external account response
//	 */
//	@PayloadRoot(namespace = NAMESPACE, localPart = REQUEST_LOCAL_PART_REQUEST_EXTERNAL_ACCOUNT)
//	@ResponsePayload
//	public RequestExternalAccountResponse RequestExternalAccount(@RequestPayload RequestExternalAccount request) {
//		ObjectFactory obj = new ObjectFactory();
//		RequestExternalAccountResponse response = obj.createRequestExternalAccountResponse();
//		logger.info("getMethod: " + request.getPRequestID() + "\n");
//		try {
//			response.setRequestExternalAccountResult(requestExternalAccountResultService.createRequestExternalAccountResultObject());
//			return response;
//		} catch (Exception ex) {
//			logger.debug(ex.getMessage());
//			return null;
//		}
//		
//	}

	
}

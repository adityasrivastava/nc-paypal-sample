package com.sample.paypal.impl;

import paypal.payflow.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by adi on 28/07/17.
 */
public class PayFlowSDKTest extends Transaction {

    public static void main(String args[]) {
        System.out.println("------------------------------------------------------");
        System.out.println("Executing Sample from File: DOSetEC.java");
        System.out.println("------------------------------------------------------");


        SDKProperties.setHostAddress("pilot-payflowpro.paypal.com");
        SDKProperties.setHostPort(443);
        SDKProperties.setTimeOut(45);

        // Logging is by default off. To turn logging on uncomment the following lines:
        SDKProperties.setLogFileName("payflow_java.log");
        SDKProperties.setLoggingLevel(PayflowConstants.SEVERITY_DEBUG);
        SDKProperties.setMaxLogFileSize(1000000);


        UserInfo User = new UserInfo(USER, VENDOR, PARTNER, PASSWORD);

        PayflowConnectionData Connection = new PayflowConnectionData();

        // Create a new Invoice data object with the Amount, Billing Address etc. details.
        Invoice inv = new Invoice();

        // Set Amount.
        Currency amt = new Currency(new Double(0));
        inv.setAmt(amt);
        inv.setOrderDesc("This is my order description");

        ECSetRequest setRequest = new ECSetRequest("localhost", "localhost");

        PayPalTender paypalTender = new PayPalTender(setRequest);

        // Create the transaction object.
        AuthorizationTransaction Trans = new AuthorizationTransaction
                (User, Connection, inv, paypalTender, PayflowUtility.getRequestId());

        // Submit the Transaction
        Response Resp = Trans.submitTransaction();

        // Display the transaction response parameters.
        if (Resp != null) {
            // Get the Transaction Response parameters.
            TransactionResponse TrxnResponse = Resp.getTransactionResponse();

            if (TrxnResponse != null) {
                System.out.println("RESULT = " + TrxnResponse.getResult());
                System.out.println("RESPMSG = " + TrxnResponse.getRespMsg());
                System.out.println("TOKEN = " + Trans.getResponse().getEcSetResponse().getToken());
                System.out.println("CORRELATIONID = " + TrxnResponse.getCorrelationId());
                // If value is true, then the Request ID has not been changed and the original response
                // of the original transction is returned.
                System.out.println("DUPLICATE = " + TrxnResponse.getDuplicate());
            }

            if (TrxnResponse.getResult() == 0) {
                System.out.println("");
                System.out.println("Transaction was Approved.");
                System.out.println("");
                System.out.println("The next step would be to redirect to PayPal to allow customer to log");
                System.out.println("into their account to select payment.  For this demo, DO NOT CLOSE the browser");
                System.out.println("as you will need the TOKEN and/or PAYER ID from the URL for the GET and DO");
                System.out.println("samples.");
                System.out.println("");
                System.out.println("Make sure you are logged into Developer Central (https://developer.paypal.com) before continuing.");
                System.out.println("");
                System.out.println("Press <Enter> to redirect to PayPal.");
                try {
                    BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                    console.readLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String PayPalUrl = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";

                PayPalUrl += Trans.getResponse().getEcSetResponse().getToken();

                Runtime rt = Runtime.getRuntime();
                Process p = null;
                try {
                    //  p = rt.exec("C:\\Program Files\\Mozilla Firefox\\firefox.exe " + PayPalUrl);
                    p = rt.exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + PayPalUrl);
                } catch (Exception exc) {/*handle exception*/}
            }

            // Display the response.
            System.out.println();
            System.out.println(PayflowUtility.getStatus(Resp));

            // Get the Transaction Context and check for any contained SDK specific errors (optional code).
            Context TransCtx = Resp.getContext();
            if (TransCtx != null && TransCtx.getErrorCount() > 0) {
                System.out.println();
                System.out.println("Transaction Errors = " + TransCtx.toString());
            }
        }
    }

}

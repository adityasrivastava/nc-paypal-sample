package com.sample.payflow.impl;

import com.sample.paypal.impl.Transaction;
import paypal.payflow.*;

public class PayFlowAuthorizationTranscation {

    public static void main(String[] args){

        SDKProperties.setHostAddress("pilot-payflowpro.paypal.com");
        SDKProperties.setHostPort(443);
        SDKProperties.setTimeOut(45);


        UserInfo user = new UserInfo(Transaction.USER, Transaction.VENDOR, Transaction.PARTNER, Transaction.PASSWORD);

        PayflowConnectionData connection = new PayflowConnectionData();

        Invoice invoice = new Invoice();

        Currency currency = new Currency(new Double(10));

        invoice.setAmt(currency);

        // C --> means credit card
        BaseTender tender = new BaseTender("C", null);

        ReferenceTransaction referenceTransaction = new ReferenceTransaction("A", "A11AA5F3E587", user, connection, invoice,tender, PayflowUtility.getRequestId());

        Response response = referenceTransaction.submitTransaction();

        if (response != null) {
            TransactionResponse transactionResponse = response.getTransactionResponse();
            System.out.println(transactionResponse.getRespMsg());
            System.out.println(transactionResponse.getPnref());
            System.out.println(transactionResponse.getResult());
            System.out.println(transactionResponse.getOrigResult());
            System.out.println(transactionResponse.getCardType());
            System.out.println(transactionResponse.getCustRef());
            System.out.println(transactionResponse.getOrigPnref());
            System.out.println(transactionResponse.getAcct());
            System.out.println(transactionResponse.getExpDate());
            System.out.println(transactionResponse.getAddlMsgs());
            System.out.println(transactionResponse.getFirstName());
            System.out.println(transactionResponse.getLastName());
            System.out.println(transactionResponse.getPPRef());
        }

    }

}

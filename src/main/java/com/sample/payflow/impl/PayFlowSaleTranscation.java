package com.sample.payflow.impl;

import com.sample.paypal.impl.Transaction;
import paypal.payflow.*;

public class PayFlowSaleTranscation {

    public static void main(String args[]){

        SDKProperties.setHostAddress("pilot-payflowpro.paypal.com");
        SDKProperties.setHostPort(443);
        SDKProperties.setTimeOut(45);


        UserInfo user = new UserInfo(Transaction.USER, Transaction.VENDOR, Transaction.PARTNER, Transaction.PASSWORD);

        PayflowConnectionData connection = new PayflowConnectionData();

        Invoice invoice = new Invoice();

        Currency currency = new Currency(new Double(1.0));

        invoice.setAmt(currency);

        BaseTender tender = new BaseTender("C", null);

        ReferenceTransaction referenceTransaction = new ReferenceTransaction("S", "A11AA5F3E587", user, connection, invoice,tender, PayflowUtility.getRequestId());

        Response response = referenceTransaction.submitTransaction();

        if (response != null) {
            TransactionResponse transactionResponse = response.getTransactionResponse();
            System.out.println(transactionResponse.getRespMsg());
        }

    }
}

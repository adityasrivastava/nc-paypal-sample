package com.sample.payflow.impl;

import com.sample.paypal.impl.Transaction;
import paypal.payflow.*;

public class PayFlowInquiryTransaction {

    public static void main(String args[]){
        SDKProperties.setHostAddress("pilot-payflowpro.paypal.com");
        SDKProperties.setHostPort(443);
        SDKProperties.setTimeOut(45);


        UserInfo user = new UserInfo(Transaction.USER, Transaction.VENDOR, Transaction.PARTNER, Transaction.PASSWORD);

        PayflowConnectionData connection = new PayflowConnectionData();
        // C --> means credit card
        BaseTender tender = new BaseTender("C", null);

        InquiryTransaction referenceTransaction = new InquiryTransaction( "A71AA45A29CE", user, connection, PayflowUtility.getRequestId());
        referenceTransaction.setVerbosity("HIGH");
        Response response = referenceTransaction.submitTransaction();

        ClientInfo clientInfo = new ClientInfo();

        referenceTransaction.setClientInfo(clientInfo);

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
            System.out.println(transactionResponse.getPPRef());

            String clientType = clientInfo.getClientType();
            System.out.println(clientType);
        }
    }
}

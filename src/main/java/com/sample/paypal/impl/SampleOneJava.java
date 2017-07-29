//package com.sample.paypal.impl;
//
//import com.paypal.api.payments.CreateProfileResponse;
//import com.paypal.api.payments.CreditCard;
//import com.paypal.api.payments.WebProfile;
//import com.paypal.base.rest.APIContext;
//import com.paypal.base.rest.PayPalRESTException;
//
///**
// * Created by adi on 27/07/17.
// */
//public class SampleOneJava {
//
//    private final static String REQUEST_MODE = "sandbox";
//
//    public static void main(String[] args) throws PayPalRESTException {
//        // Replace these values with your clientId and secret. You can use these to get started right now.
//        String clientId = "AW1GGBAkaALcWXFslVQbx8K87vcs_TBHDSfdbGHVq701wxCW3u0cu5MxSMIc0NscPAHkVKiJUKo85wiF";
//        String clientSecret = "EF2JFY5rQZbi1IdL9A3kWAHaWZXn7dYPYsdmcdEtp94DSRsU8WKLhTlsKkdw9Z6oU5GcAtKWmK5Kgvqp";
//
//        APIContext context = new APIContext(clientId, clientSecret, REQUEST_MODE);
//
//
//        WebProfile webProfile = new WebProfile();
//        webProfile.setName("Test");
//
//        CreateProfileResponse createProfileResponse = webProfile.create(context);
//        System.out.println(createProfileResponse);
//    }
//
//    public static void sample(APIContext context){
//
//        // Create a Credit Card
//        CreditCard card = new CreditCard()
//                .setType("visa")
//                .setNumber("4417119669820331")
//                .setExpireMonth(11)
//                .setExpireYear(2019)
//                .setCvv2(012)
//                .setFirstName("Joe")
//                .setLastName("Shopper");
//
//        try {
//            CreditCard creditCard = card.create(context);
//            System.out.println("Test" + creditCard);
//        } catch (PayPalRESTException e) {
//            System.err.println(e.getDetails());
//        }
//
//    }
//
//
//}

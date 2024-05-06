package com.apromore.shoptoys.constants;

public class WebPageConstants {

    public static class StepName {
        public static final String CLICK_TOYS_STEP_NAME = "clickShop";
        public static final String ADD_ITEM_STEP_NAME = "addItemBear";
        public static final String CLICK_CART_STEP_NAME = "clickCart";
        public static final String VERIFY_QUANTITY_STEP_NAME = "verifyQuantity";
        public static final String VALIDATE_LOGIN_STEP_NAME = "validateLogin";
        public static final String CLICK_CONTACT_STEP_NAME = "clickContact";
        public static final String SUBMIT_FEEDBACK_FAIL_STEP_NAME = "submitFeedback_fail";
        public static final String VALIDATE_ERRORS_STEP_NAME = "validateErrors";
        public static final String ENTER_DETAILS_STEP_NAME = "enterDetails";
        public static final String VALIDATE_SUCCESS_RESPONSE_STEP_NAME = "validateSuccessResponse";

    }

    public static class StepDescription {
        public static final String CLICK_TOYS_STEP_DESC = "Successfully able to click Shop button and verified the image.";
        public static final String ADD_ITEMS_STEP_DESC = "Successfully able to select items and verified the image.";
        public static final String CLICK_CART_STEP_DESC = "Successfully able to click Cart button and verified the image.";
        public static final String VERIFY_QUANTITY_STEP_DESC = "Successfully able to verify quantity from the Cart and verified the image.";
        public static final String VALIDATE_LOGIN_STEP_DESC = "Successfully navigated to Home page and verified the image";
        public static final String CLICK_CONTACT_STEP_DESC = "Successfully able to click Contact button and verified the image";
        public static final String SUBMIT_FEEDBACK_FAIL_STEP_DESC = "Mandatory fields are required to submit a feedback and verify image failed against the copydeck image for successful submission";
        public static final String VALIDATE_ERRORS_STEP_DESC = "Validate the required field errors appear on the screen and verified the image";
        public static final String ENTER_DETAILS_STEP_DESC = "Successfully entered the mandatory field values required to submit a feedback and verified the image";
        public static final String VALIDATE_SUCCESS_RESPONSE_STEP_DESC = "Successfully able to submit feedback and verified the image";

    }

}

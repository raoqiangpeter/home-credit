package com.raoqiang.homecredit.calculate.constant;

public class BureauOneHotIndex {
    /*

before ["CREDIT_ACTIVE", "CREDIT_CURRENCY", "CREDIT_TYPE"]
after ["CREDIT_ACTIVE_Active", "CREDIT_ACTIVE_Bad debt", "CREDIT_ACTIVE_Closed", "CREDIT_ACTIVE_Sold", "CREDIT_CURRENCY_currency 1", "CREDIT_CURRENCY_currency 2", "CREDIT_CURRENCY_currency 3", "CREDIT_CURRENCY_currency 4", "CREDIT_TYPE_Another type of loan", "CREDIT_TYPE_Car loan", "CREDIT_TYPE_Cash loan (non-earmarked)", "CREDIT_TYPE_Consumer credit", "CREDIT_TYPE_Credit card", "CREDIT_TYPE_Interbank credit", "CREDIT_TYPE_Loan for business development", "CREDIT_TYPE_Loan for purchase of shares (margin lending)", "CREDIT_TYPE_Loan for the purchase of equipment", "CREDIT_TYPE_Loan for working capital replenishment", "CREDIT_TYPE_Microloan", "CREDIT_TYPE_Mobile operator loan", "CREDIT_TYPE_Mortgage", "CREDIT_TYPE_Real estate loan", "CREDIT_TYPE_Unknown type of loan"]
     */

    public static final String[] CREDIT_ACTIVE_ONE_HOT_INDEX = {"CREDIT_ACTIVE_Active", "CREDIT_ACTIVE_Bad debt",
            "CREDIT_ACTIVE_Closed", "CREDIT_ACTIVE_Sold"};

    public static final String[] CREDIT_CURRENCY_ONE_HOT_INDEX = {"CREDIT_CURRENCY_currency 1",
            "CREDIT_CURRENCY_currency 2", "CREDIT_CURRENCY_currency 3", "CREDIT_CURRENCY_currency 4"};

    public static final String[] CREDIT_TYPE_ONE_HOT_INDEX = {"CREDIT_TYPE_Another type of loan",
            "CREDIT_TYPE_Car loan", "CREDIT_TYPE_Cash loan (non-earmarked)", "CREDIT_TYPE_Consumer credit",
            "CREDIT_TYPE_Credit card", "CREDIT_TYPE_Interbank credit", "CREDIT_TYPE_Loan for business development",
            "CREDIT_TYPE_Loan for purchase of shares (margin lending)", "CREDIT_TYPE_Loan for the purchase of equipment",
            "CREDIT_TYPE_Loan for working capital replenishment", "CREDIT_TYPE_Microloan",
            "CREDIT_TYPE_Mobile operator loan", "CREDIT_TYPE_Mortgage",
            "CREDIT_TYPE_Real estate loan", "CREDIT_TYPE_Unknown type of loan"};
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

import java.util.ArrayList;

/**
 *
 * @author Royal
 */
public class TotalExpenditureReportFactory {

    static java.util.Vector collection;
    static ArrayList<TotalExpenditureBean> expenditureList = new ArrayList<TotalExpenditureBean>();
    static String total;
    static String dateLimit;
    static String title;
    static String cashier;

    public static java.util.Collection generateCollection() {
        collection = new java.util.Vector();

        for (int i = 0; i < expenditureList.size(); i++) {
            String amount = expenditureList.get(i).getAmount();
            String reason = expenditureList.get(i).getReason();
            String date = expenditureList.get(i).getDate();
            collection.add(new TotalExpenditureBean(amount, reason, date, cashier, total, dateLimit, title));
        }

        return collection;
    }
}

package com.liquidbol.addons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * java.util.date to java.sql.date
 *
 * @author Franco Montiel
 */
public class DateConverter {

    public java.sql.Date localDateToSql(String strDate) throws ParseException {
        DateFormat localDF = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = localDF.parse(strDate);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    public java.util.Date sqlDateToLocal(java.sql.Date strDate) throws ParseException {
        DateFormat sqlDF = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = sqlDF.parse(strDate.toString());
        return utilDate;
    }
}

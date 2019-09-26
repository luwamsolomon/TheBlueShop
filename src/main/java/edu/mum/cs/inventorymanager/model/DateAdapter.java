package edu.mum.cs.inventorymanager.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {


    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public synchronized Date unmarshal(String date) throws ParseException {
        return df.parse(date);
    }

    @Override
    public synchronized  String marshal(Date date) {
        return df.format(date);
    }


}

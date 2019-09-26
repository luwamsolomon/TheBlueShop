package edu.mum.cs.inventorymanager.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DecimalFormat;

public class AmountAdapter extends XmlAdapter<String, Double> {

    @Override
    public Double unmarshal(String v) throws Exception {
        return Double.valueOf(v);
    }

    @Override
    public String marshal(Double v) throws Exception {
        DecimalFormat n = new DecimalFormat("#################.00");
        return n.format(v);
    }

}

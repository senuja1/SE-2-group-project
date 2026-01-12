package com.inventory.dao;

import java.util.ArrayList;
import java.util.List;
import com.inventory.models.Report;

public class ReportDAO {

    public List<Report> getSystemReports() {
        List<Report> reports = new ArrayList<>();

        reports.add(new Report("Total Items", 120));
        reports.add(new Report("Low Stock Items", 15));
        reports.add(new Report("Out of Stock", 5));

        return reports;
    }
}
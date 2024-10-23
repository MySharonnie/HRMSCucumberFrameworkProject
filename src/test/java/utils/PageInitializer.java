package utils;

import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;

public class PageInitializer {

    public static LoginPage loginPage;
    public static DashboardPage dashboardPage;
    public static AddEmployeePage addEmployeePage;



    public static void initialPageObjects(){
        loginPage =new LoginPage();
        dashboardPage = new DashboardPage();
        addEmployeePage = new AddEmployeePage();

    }
}

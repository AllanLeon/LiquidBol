package com.liquidbol.addons;

import com.liquidbol.gui.LoginForm;
import com.liquidbol.model.Company;
import com.liquidbol.services.CXCServices;
import com.liquidbol.services.ClientServices;
import com.liquidbol.services.CompanyServices;
import com.liquidbol.services.DebtServices;
import com.liquidbol.services.ItemBillServices;
import com.liquidbol.services.ItemEstimateServices;
import com.liquidbol.services.ItemServices;
import com.liquidbol.services.PurchaseServices;
import com.liquidbol.services.ServiceBillServices;
import com.liquidbol.services.StoreServices;
import com.liquidbol.services.SupplierServices;
import java.awt.*;
import java.awt.SplashScreen;

/**
 *
 * @author Franco
 */
public final class MagikarpScreen {

    private final SplashScreen splash;
    public final String[] loadingText = {"Limpiando manómetros", "Esperando a que llegue el jefe",
                            "Recargando recargas recargables", "Alistando cascos para el trabajo",
                            "Clasificando electrodos", "Alistando pan con Pepsi"};
    private Company liquid;
    public static CXCServices cxcServ;
    public static ClientServices clientServ;
    public static CompanyServices compServ;
    public static DebtServices debtServ;
    public static ItemBillServices itemBillServ;
    public static ItemEstimateServices itemEstServ;
    public static ItemServices itemServ;
    public static PurchaseServices purchServ;
    public static ServiceBillServices servBillServ;
    public static StoreServices storeServ;
    public static SupplierServices suppServ;
    public static LoginForm LF;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MagikarpScreen magikarpScreen = new MagikarpScreen();
            }
        });
    }
    
    public MagikarpScreen() {
        splash = SplashScreen.getSplashScreen();
        animate();
        liquid = new Company();
        cxcServ = new CXCServices();
        clientServ = new ClientServices();
        compServ = new CompanyServices(liquid);
        debtServ = new DebtServices();
        itemBillServ = new ItemBillServices();
        itemEstServ = new ItemEstimateServices();
        itemServ = new ItemServices();
        purchServ = new PurchaseServices();
        servBillServ = new ServiceBillServices();
        storeServ = new StoreServices();
        suppServ = new SupplierServices();
    }

    public void animate() {
        if (splash != null) {
            Graphics2D g = splash.createGraphics();
            for (int i = 1; i < loadingText.length; i++) {
                g.setColor(new Color(4, 52, 101));//backgroundcolor
                g.fillRect(236, 365, 280, 12);//toCoverLastText
                g.setColor(Color.white);//textColor
                g.drawString(loadingText[i - 1] + "...", 236, 375);
                g.setColor(new Color(0, 105 + 30*i, 0)); //progressBarcolor
                g.fillRect(231, 346, (i * 407 / loadingText.length), 12); //progressBar
                splash.update();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
            splash.close();
        }
        try {
            LF = new LoginForm();
            LF.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
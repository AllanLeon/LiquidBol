package com.liquidbol.addons;

import com.liquidbol.db.DatabaseLoader;
import com.liquidbol.gui.LoginForm;
import com.liquidbol.services.BillServices;
import com.liquidbol.services.CXCServices;
import com.liquidbol.services.ClientServices;
import com.liquidbol.services.CompanyServices;
import com.liquidbol.services.DebtServices;
import com.liquidbol.services.ItemEstimateServices;
import com.liquidbol.services.ItemServices;
import com.liquidbol.services.PurchaseServices;
import com.liquidbol.services.StoreServices;
import com.liquidbol.services.SupplierServices;
import java.awt.*;
import java.awt.SplashScreen;
import java.util.Random;

/**
 *
 * @author Franco
 */
public final class MagikarpScreen {

    private SplashScreen splash;
    public final String[] loadingText = {"Limpiando manómetros", "Esperando a que llegue el jefe",
                            "Recargando recargas recargables", "Alistando cascos para el trabajo",
                            "Clasificando electrodos", "Alistando pan con Pepsi",
                            "Oxigenando la base de datos", "Pintando naranja"};
    //private Company liquid;
    public static BillServices billServ;
    public static CXCServices cxcServ;
    public static ClientServices clientServ;
    public static CompanyServices compServ;
    public static DebtServices debtServ;
    public static ItemEstimateServices itemEstServ;
    public static ItemServices itemServ;
    public static PurchaseServices purchServ;
    public static StoreServices storeServ;
    public static SupplierServices suppServ;
    public static LoginForm LF;
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MagikarpScreen magikarpScreen = new MagikarpScreen();
            }
        });
    }
    
    public MagikarpScreen() {
        thread1.start();
        thread2.start();
    }

    public void animate() {
        if (splash != null) {
            Graphics2D g = splash.createGraphics();
            shuffleArray(loadingText);
            for (int i = 1; i < 6; i++) {
                g.setColor(new Color(4, 52, 101));//backgroundcolor
                g.fillRect(236, 365, 280, 12);//toCoverLastText
                g.setColor(Color.white);//textColor
                g.drawString(loadingText[i - 1] + "...", 236, 375);
                g.setColor(new Color(0, 105 + 30*i, 0)); //progressBarcolor
                g.fillRect(231, 346, (i * 407 / 6), 12); //progressBar
                splash.update();
                try {
                    Thread.sleep(700);
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
    
    static void shuffleArray(String[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    
    Thread thread1 = new Thread () {
        public void run () {
            splash = SplashScreen.getSplashScreen();
            animate();
       }
    };
    
    Thread thread2 = new Thread () {
        public void run () {
            //liquid = new Company();
            new DatabaseLoader().loadCompanyInfo();
            billServ = new BillServices();
            cxcServ = new CXCServices();
            clientServ = new ClientServices();
            compServ = new CompanyServices(/*liquid*/);
            debtServ = new DebtServices();
            itemEstServ = new ItemEstimateServices();
            itemServ = new ItemServices();
            purchServ = new PurchaseServices();
            storeServ = new StoreServices();
            suppServ = new SupplierServices();       
        }
    };
}